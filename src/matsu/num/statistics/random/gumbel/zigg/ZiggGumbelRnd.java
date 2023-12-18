/**
 * 2023.3.22
 */
package matsu.num.statistics.random.gumbel.zigg;

import java.util.Objects;
import java.util.Random;

import matsu.num.commons.Exponentiation;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GumbelRnd;

/**
 * Ziggurat法により実装された, 標準Gumbel分布に従う乱数発生器.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public final class ZiggGumbelRnd implements GumbelRnd {

    private static final GumbelRnd INSTANCE = new ZiggGumbelRnd();

    private static final int N = 128;
    private static final double R_P = 7.4267177720719d;
    private static final double V_P = 0.00501225058838053d;

    private static final double R_M = 2.18063761379537d;
    private static final double V_M = 0.00290539698161451d;

    private static final double EXP_R_M = Exponentiation.exp(R_M);
    private static final int THRESHOLD_SIGN = 790015085; //2^{31}*(1/e)

    private final double[] xi_P;
    private final double[] fi_P;

    private final double[] xi_M;
    private final double[] fi_M;

    private final ExponentialRnd expRnd = ExponentialRnd.instance();

    private ZiggGumbelRnd() {
        if (Objects.nonNull(INSTANCE)) {
            throw new AssertionError();
        }

        xi_P = new double[N + 1];
        fi_P = new double[N + 1]; //配列サイズが2^n個にならないよう、無駄スペースあり
        xi_P[N] = V_P / func_p(R_P);
        xi_P[N - 1] = R_P;
        fi_P[N - 1] = func_p(xi_P[N - 1]);
        for (int i = N - 2; i >= 1; i--) {
            xi_P[i] = funcInv_p(fi_P[i + 1] + V_P / xi_P[i + 1]);
            fi_P[i] = func_p(xi_P[i]);
        }
        xi_P[0] = 0.0;
        fi_P[0] = func_p(xi_P[0]);

        xi_M = new double[N + 1];
        fi_M = new double[N + 1]; //配列サイズが2^n個にならないよう、無駄スペースあり
        xi_M[N] = V_M / func_m(R_M);
        xi_M[N - 1] = R_M;
        fi_M[N - 1] = func_m(xi_M[N - 1]);
        for (int i = N - 2; i >= 1; i--) {
            xi_M[i] = funcInv_m(fi_M[i + 1] + V_M / xi_M[i + 1]);
            fi_M[i] = func_m(xi_M[i]);
        }
        xi_M[0] = 0.0;
        fi_M[0] = func_m(xi_M[0]);
    }

    @Override
    public double nextRandom(Random random) {
        int sig = random.nextInt() >>> 1;
        if (sig < THRESHOLD_SIGN) {
            return -next_m(random);
        } else {
            return next_p(random);
        }
    }

    /*
     標準ガンベル分布の最頻値はx=0である. 
     x >= 0をとる確率は 1-(1/e)
     x <= 0をとる確率は (1/e)
     であるので, まず確率(1/e) vs. 1-(1/e)で領域 負 or 正 を選び, 
     それぞれの領域でZiggurat法を用いる.
     */
    /**
     * {@literal x >= 0}の領域での確率密度関数
     */
    private double func_p(double x) {
        double exp = Exponentiation.exp(-x);
        return Exponentiation.exp(-exp - x);
    }

    /**
     * {@literal x >= 0}の領域での確率密度関数の逆関数.
     */
    private double funcInv_p(double f) {
        return -Exponentiation.log(-lambertWp(-f));
    }

    /**
     * {@literal x <= 0}の領域での確率密度関数
     */
    private double func_m(double x) {
        double exp = Exponentiation.exp(x);
        return Exponentiation.exp(-exp + x);
    }

    /**
     * {@literal x <= 0}の領域での確率密度関数の逆関数.
     */
    private double funcInv_m(double f) {
        return Exponentiation.log(-lambertWm(-f));
    }

    /**
     * {@literal x >= 0}の領域での乱数生成:このエリアは確率1-(1/e)で選択される.
     */
    private double next_p(Random random) {
        while (true) {
            int int32 = random.nextInt();
            int iArea = int32 & (N - 1);

            double x = xi_P[iArea + 1] * random.nextDouble();
            if (x < xi_P[iArea]) {
                return x;
            }
            if (iArea == N - 1) {
                return tail_p(random);
            }
            if (random.nextDouble() * (fi_P[iArea] - fi_P[iArea + 1]) <= func_p(x) - fi_P[iArea + 1]) {
                return x;
            }
        }
    }

    /**
     * {@literal x <= 0}の領域での乱数生成:このエリアは確率(1/e)で選択される.
     */
    private double next_m(Random random) {

        while (true) {
            int int32 = random.nextInt();
            int iArea = int32 & (N - 1);

            double x = xi_M[iArea + 1] * random.nextDouble();
            if (x < xi_M[iArea]) {
                return x;
            }
            if (iArea == N - 1) {
                return tail_m(random);
            }
            if (random.nextDouble() * (fi_M[iArea] - fi_M[iArea + 1]) <= func_m(x) - fi_M[iArea + 1]) {
                return x;
            }
        }
    }

    private double tail_p(Random random) {
        while (true) {
            double z = R_P + this.expRnd.nextRandom(random);
            if (1 < Exponentiation.exp(z) * this.expRnd.nextRandom(random)) {
                return z;
            }
        }
    }

    private double tail_m(Random random) {
        return Exponentiation.log(EXP_R_M + this.expRnd.nextRandom(random));
    }

    /**
     * ランベルトW関数の主枝:W_0.
     */
    private double lambertWp(double z) {
        final double negInvE = -1 / Math.E;
        final double threshold = 1E-11;
        if (z < negInvE) {
            return Double.NaN;
        }
        double z_p_invE = z - negInvE;
        if (z_p_invE < threshold) {
            return -1 + Exponentiation.sqrt((2 * Math.E) * z_p_invE);
        }
        if (z < 10) {
            double w0 = z < 0 ? -1 + Exponentiation.sqrt((2 * Math.E) * z_p_invE) : Exponentiation.log(z + 1);
            final int iteration_z0 = 4;
            for (int i = 0; i < iteration_z0; i++) {
                double inv1pW0 = 1 / (1 + w0);
                double L = (w0 - z * Exponentiation.exp(-w0)) * inv1pW0;
                w0 -= L * (1 + L * (2 + w0) * (0.5 * inv1pW0));
            }
            return w0;
        } else {
            final int iteration_z10 = 3;
            double logZ = Exponentiation.log(z);
            double w0 = logZ;
            for (int i = 0; i < iteration_z10; i++) {
                double inv1pW0 = 1 / (1 + w0);
                double L = (w0 - logZ + Exponentiation.log(w0)) * inv1pW0;
                w0 -= (w0 * L) * (1 - L * (0.5 * inv1pW0));
            }
            return w0;
        }
    }

    /**
     * ランベルトW関数の分枝:W_{-1}.
     */
    private double lambertWm(double z) {
        final double negInvE = -1 / Math.E;
        final double threshold = 1E-11;
        if (z < negInvE || z >= 0) {
            return Double.NaN;
        }
        double z_p_invE = z - negInvE;
        if (z_p_invE < threshold) {
            return -1 - Exponentiation.sqrt((2 * Math.E) * z_p_invE);
        }
        final double z_w2 = -0.270670566;
        if (z < z_w2) {
            double w0 = -1 - Exponentiation.sqrt((2 * Math.E) * z_p_invE);
            final int iteration_z0 = 3;
            for (int i = 0; i < iteration_z0; i++) {
                double inv1pW0 = 1 / (1 + w0);
                double L = (w0 - z * Exponentiation.exp(-w0)) * inv1pW0;
                w0 -= L * (1 + L * (2 + w0) * (0.5 * inv1pW0));
            }
            return w0;
        } else {
            final int iteration_z10 = 3;
            double logMinusZ = Exponentiation.log(-z);
            double w0 = logMinusZ - 1;
            for (int i = 0; i < iteration_z10; i++) {
                double inv1pW0 = 1 / (1 + w0);
                double L = (w0 - logMinusZ + Exponentiation.log(-w0)) * inv1pW0;
                w0 -= (w0 * L) * (1 - L * (0.5 * inv1pW0));
            }
            return w0;
        }
    }

    /**
     * Ziggurat法により実装された標準ガンベル分布乱数発生器インスタンスを返す.
     *
     * @return 標準ガンベル分布乱数発生器インスタンス
     */
    public static GumbelRnd instance() {
        return INSTANCE;
    }
}
