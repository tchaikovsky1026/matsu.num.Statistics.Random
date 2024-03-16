/**
 * 2024.1.13
 */
package matsu.num.statistics.random.service.cauthy;

import matsu.num.statistics.random.CauthyRnd;
import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.service.Exponentiation;

/**
 * Ziggurat法により実装された, 標準Cauthy分布に従う乱数発生器.
 * 
 * <p>
 * 1 / (1 + <i>x</i><sup><i>2</i></sup>)
 * に対してZigguratを適用する.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 18.0
 */
final class ZiggCauthyRnd implements CauthyRnd {

    private static final int N = 128;
    private static final double R_N = 158.474291831405d;
    private static final double V_N = 0.0126200085059016d;

    private final double[] xi;
    private final double[] fi;

    private final TDistributionRnd tdistView;

    private final Exponentiation exponentiation;

    ZiggCauthyRnd(Exponentiation exponentiation) {
        super();
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

        this.tdistView = new TDistView();
    }

    /**
     * 確率密度関数相当. <br>
     * 1/(1 + x^2)
     * 
     * @param x
     * @return
     */
    private double func(double x) {
        return 1 / (1 + x * x);
    }

    /**
     * {@literal x >= 0}における確率密度関数の逆関数. <br>
     * sqrt(-2log(f))
     * 
     * @param f
     * @return
     */
    private double funcInv(double f) {
        return exponentiation.sqrt(1d / f - 1);
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
     * tailは,
     * 1/x^2を提案分布(逆関数法)とする棄却法を用いる.
     */
    private double tail(BaseRandom random) {
        while (true) {
            double u = random.nextDouble();
            if (random.nextDouble() * (R_N + u) < R_N) {
                double y = exponentiation.sqrt(R_N / u);
                if (Double.isFinite(y)) {
                    return y;
                }
            }
        }
    }

    @Override
    public TDistributionRnd asTDistributionRnd() {
        return this.tdistView;
    }

    @Override
    public String toString() {
        return "CauthyRnd";
    }

    /**
     * Cauthy分布のt分布としてのビューを扱うクラス.
     */
    private final class TDistView implements TDistributionRnd {

        /**
         * 唯一のコンストラクタ.
         */
        TDistView() {
            super();
        }

        @Override
        public double degreesOfFreedom() {
            return 1d;
        }

        @Override
        public double nextRandom(BaseRandom random) {
            return ZiggCauthyRnd.this.nextRandom(random);
        }

        @Override
        public String toString() {
            return "TDistRnd(Cauthy)";
        }
    }
}
