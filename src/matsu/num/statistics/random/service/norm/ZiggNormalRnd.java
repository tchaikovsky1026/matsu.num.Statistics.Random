/**
 * 2024.1.13
 */
package matsu.num.statistics.random.service.norm;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.service.Exponentiation;

/**
 * Ziggurat法により実装された標準正規分布に従う乱数発生器.
 *
 * @author Matsuura Y.
 * @version 18.0
 */
final class ZiggNormalRnd implements NormalRnd {

    private static final int N = 128;
    private static final double R_N = 3.44261985589665d;
    private static final double V_N = 0.00991256303533652d;

    private final double[] xi;
    private final double[] fi;
    private final ExponentialRnd expRnd;

    private final Exponentiation exponentiation;

    ZiggNormalRnd(Exponentiation exponentiation, ExponentialRnd.Factory exponentialRndFactory) {
        super();
        this.expRnd = exponentialRndFactory.instance();
        this.exponentiation = exponentiation;

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
    public double nextRandom(BaseRandom random) {
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
    private double tail(BaseRandom random) {
        while (true) {
            double y = (1 / R_N) * this.expRnd.nextRandom(random);
            double ue = this.expRnd.nextRandom(random);
            if (ue > 0.5 * y * y) {
                return y + R_N;
            }
        }
    }

    /**
     * 確率密度関数.
     * 
     * @param x
     * @return
     */
    private double func(double x) {
        return exponentiation.exp(-x * x * 0.5);
    }

    /**
     * {@literal x >= 0}における確率密度関数の逆関数. <br>
     * sqrt(-2log(f))
     * 
     * @param f
     * @return
     */
    private double funcInv(double f) {
        return exponentiation.sqrt(-2.0 * Math.log(f));
    }

    @Override
    public String toString() {
        return "NormalRnd";
    }

}
