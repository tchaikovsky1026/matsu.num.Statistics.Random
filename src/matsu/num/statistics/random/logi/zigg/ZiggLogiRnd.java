/**
 * 2023.3.22
 */
package matsu.num.statistics.random.logi.zigg;

import java.util.Objects;
import java.util.Random;

import matsu.num.commons.Exponentiation;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.LogisticRnd;

/**
 * 標準ロジスティック分布に従う乱数発生器を扱う. <br>
 * 標準ロジスティック分布の確率密度関数 P(<i>x</i>) は,  <br>
 * P(<i>x</i>) &prop; exp(-<i>x</i>)/(1 + exp(-<i>x</i>))<sup>2</sup> <br>
 * である. <br>
 * この乱数発生器はZiggurat法により実装されている.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public final class ZiggLogiRnd implements LogisticRnd {

    private static final LogisticRnd INSTANCE = new ZiggLogiRnd();

    private static final int N = 128;
    private static final double R_N = 7.69287439477735d;
    private static final double V_N = 0.0039611131302936d;
    private static final double EXP_NEGATIVE_R = Exponentiation.exp(-R_N);

    private final double[] xi;
    private final double[] fi;
    private final ExponentialRnd expRnd = ExponentialRnd.instance();

    private ZiggLogiRnd() {
        if (Objects.nonNull(INSTANCE)) {
            throw new AssertionError();
        }

        xi = new double[N + 1];
        fi = new double[N + 1]; //配列サイズが2^n個にならないよう、無駄スペースあり
        xi[N] = V_N / func(R_N);
        xi[N - 1] = R_N;
        fi[N - 1] = func(xi[N - 1]);
        for (int i = N - 2; i >= 1; i--) {
            xi[i] = funcInv(fi[i + 1] + V_N / xi[i + 1]);
            fi[i] = func(xi[i]);
        }
        xi[0] = 0.0;
        fi[0] = func(xi[0]);
    }

    @Override
    public double nextRandom(Random random) {
        while (true) {
            int int32 = random.nextInt();
            boolean bSign = (int32 & 1) == 1;
            int iArea = (int32 >>> 1) & (N - 1);
            double x = xi[iArea + 1] * random.nextDouble();
            if (x < xi[iArea]) {
                return bSign ? x : -x;
            }
            if (iArea == N - 1) {
                return bSign ? tail(random) : -tail(random);
            }
            if (random.nextDouble() * (fi[iArea] - fi[iArea + 1]) <= func(x) - fi[iArea + 1]) {
                return bSign ? x : -x;
            }
        }
    }

    private double func(double x) {
        double expNegativeX = Exponentiation.exp(-x);
        double temp = 1 + expNegativeX;
        temp = temp * temp;
        return expNegativeX / temp;
    }

    private double funcInv(double x) {
        return Exponentiation.log((Exponentiation.sqrt(1 - 4 * x) + 1 - 2 * x) / (2 * x));
    }

    private double tail(Random random) {
        while (true) {
            double u = this.expRnd.nextRandom(random);
            double u2 = random.nextDouble();
            double thre1 = 1 - u + u * u * 0.5;
            thre1 = 1 / (1 + EXP_NEGATIVE_R * thre1);
            thre1 = thre1 * thre1;
            if (u2 < thre1) {
                return u + R_N;
            }
            double thre2 = Exponentiation.exp(-u);
            thre2 = 1 / (1 + EXP_NEGATIVE_R * thre2);
            thre2 = thre2 * thre2;
            if (u2 < thre2) {
                return u + R_N;
            }
        }
    }

    /**
     * Ziggurat法により実装された標準ロジスティック分布乱数発生器インスタンスを返す.
     *
     * @return 標準ロジスティック分布乱数発生器インスタンス
     */
    public static LogisticRnd instance() {
        return INSTANCE;
    }
}
