/**
 * 2024.1.9
 */
package matsu.num.statistics.random.poi;

import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.PoissonRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.common.Exponentiation;
import matsu.num.statistics.random.common.ExponentiationFactory;
import matsu.num.statistics.random.gamma.GammaRndFactory;

/**
 * ガンマ分布乱数発生器を利用した, Poisson分布に従う乱数発生器を扱う.
 * 
 * <p>
 * 扱えるパラメータ <i>&lambda;</i> は, {@code 0.0 <= lambda <= 1.0E6} である.
 * </p>
 *
 * @author Matsuura Y.
 * @version 17.5
 */
final class GammaHomoProcessBasedPoissonRnd implements PoissonRnd {

    private static final double MAX_LAMBDA = 1E6;
    private static final int GAMMA_RND_BIT = 20;

    /**
     * 形状パラメータが1, 2, 4, ... のガンマ乱数生成器. <br>
     * 最大は 2^20 = 1_048_576 (GAMMA_RND_BIT = 20)
     */
    private static final GammaRnd[] GAMMA_RNDS;

    private final double lambda;

    private final Exponentiation exponentiation = ExponentiationFactory.instance();

    static {
        GAMMA_RNDS = new GammaRnd[GAMMA_RND_BIT + 1];
        int k = 1;
        for (int i = 0; i < GAMMA_RND_BIT + 1; i++) {
            GAMMA_RNDS[i] = GammaRndFactory.instanceOf(k);
            k = k * 2;
        }
    }

    /**
     * 指定したパラメータのPoisson分布乱数発生器インスタンスを構築する. <br>
     * パラメータ <i>&lambda;</i> は, {@code 0.0 <= lambda <= 1.0E6} でなければならない.
     *
     * @param lambda パラメータ&lambda;
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    GammaHomoProcessBasedPoissonRnd(double lambda) {
        if (!(0 <= lambda && lambda <= MAX_LAMBDA)) {
            throw new IllegalArgumentException(
                    String.format(
                            "パラメータ不正:lambda=%s", lambda));
        }
        this.lambda = lambda;
    }

    @Override
    public double lambda() {
        return this.lambda;
    }

    @Override
    public int nextRandom(Random random) {
        double z = this.lambda;
        int shift = 0;
        while (true) {
            if (z < 4) {
                return shift + byExpMethod(random, z);
            }
            int intTestKmin = Integer.highestOneBit((int) z >> 1);
            double uGamma = GAMMA_RNDS[31 - Integer.numberOfLeadingZeros(intTestKmin)].nextRandom(random);
            if (uGamma > z) {
                return shift + byDirichletMethod(random, intTestKmin, z, uGamma);
            }
            z -= uGamma;
            shift += intTestKmin;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "PoissonRnd(%s)", this.lambda());
    }

    /**
     * zが小さいときに有効なExp法.
     */
    private int byExpMethod(Random random, double z) {
        double residual = exponentiation.exp(z);
        int k = 0;
        while (true) {
            residual *= random.nextDouble();
            if (residual <= 1) {
                return k;
            }
            k++;
        }
    }

    /**
     * Dirichlet法.
     */
    private int byDirichletMethod(Random random, int initK, double initZ, double initW) {
        int k = initK;
        int kBit = 31 - Integer.numberOfLeadingZeros(k);
        double z = initZ;
        double w = initW;

        int shift = 0;
        while (true) {
            if (kBit == 0) {
                return shift;
            }

            //kを更新
            k = k >> 1;
            kBit--;
            GammaRnd gRndK = GAMMA_RNDS[kBit];
            final double u1 = gRndK.nextRandom(random);
            final double u2 = gRndK.nextRandom(random);
            final double u12 = u1 + u2 + 1E-100;
            final double w1 = u1 / u12 * w;

            if (w1 >= z) {
                w = w1;
            } else {
                z -= w1;
                w -= w1;
                shift += k;
            }
        }

    }
}
