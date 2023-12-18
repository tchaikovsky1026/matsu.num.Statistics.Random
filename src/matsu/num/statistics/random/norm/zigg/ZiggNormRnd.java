/**
 * 2023.3.22
 */
package matsu.num.statistics.random.norm.zigg;

import java.util.Objects;
import java.util.Random;

import matsu.num.commons.Exponentiation;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.NormalRnd;

/**
 * Ziggurat法により実装された標準正規分布に従う乱数発生器. 
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public final class ZiggNormRnd implements NormalRnd {

    private static final NormalRnd INSTANCE = new ZiggNormRnd();

    private static final int N = 128;
    private static final double R_N = 3.44261985589665d;
    private static final double V_N = 0.00991256303533652d;

    private final double[] xi;
    private final double[] fi;
    private final ExponentialRnd expRnd = ExponentialRnd.instance();

    private ZiggNormRnd() {
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

    /**
     * テールは指数分布による棄却法を用いる.
     */
    private double tail(Random random) {
        while (true) {
            double y = (1 / R_N) * this.expRnd.nextRandom(random);
            double ue = this.expRnd.nextRandom(random);
            if (ue > 0.5 * y * y) {
                return y + R_N;
            }
        }
    }

    private double func(double x) {
        return Exponentiation.exp(-x * x * 0.5);
    }

    private double funcInv(double x) {
        return Exponentiation.sqrt(-2.0 * Math.log(x));
    }

    /**
     * Ziggurat法により実装された標準正規分布乱数発生器インスタンスを返す.
     *
     * @return 標準正規分布乱数発生器インスタンス
     */
    public static NormalRnd instance() {
        return INSTANCE;
    }
}
