/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.poi;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * ガンマ分布乱数発生器を利用した, Poisson分布に従う乱数発生器を扱う.
 *
 * @author Matsuura Y.
 * @version 21.0
 */
final class GammaHomoProcessBasedPoissonRnd extends SkeletalPoissonRnd {

    /**
     * lambdaの範囲を網羅できるための, ガンマ乱数の形状パラメータのビット数. <br>
     * 1,2,4,...,2^20までで, 20.
     */
    static final int GAMMA_RND_BIT = 20;

    /**
     * 形状パラメータが1, 2, 4, ... のガンマ乱数発生器. <br>
     * 最大は 2^20 = 1_048_576 (GAMMA_RND_BIT = 20)
     */
    private final GammaRnd[] gammaRnds;

    private final Exponentiation exponentiation;

    /**
     * 指定したパラメータのPoisson分布乱数発生器インスタンスを構築する. <br>
     * パラメータ <i>&lambda;</i> は, {@code 0.0 <= lambda <= 1.0E6} でなければならない.
     *
     * @param lambda パラメータ
     */
    GammaHomoProcessBasedPoissonRnd(double lambda, GammaRnd[] gammaRnds, Exponentiation exponentiation) {
        super(lambda);

        this.gammaRnds = gammaRnds;
        this.exponentiation = exponentiation;
    }

    @Override
    public int nextRandom(BaseRandom random) {
        double z = this.lambda;
        int shift = 0;
        while (true) {
            if (z < 4) {
                return shift + byExpMethod(random, z);
            }
            int intTestKmin = Integer.highestOneBit((int) z >> 1);
            double uGamma = gammaRnds[31 - Integer.numberOfLeadingZeros(intTestKmin)].nextRandom(random);
            if (uGamma > z) {
                return shift + byDirichletMethod(random, intTestKmin, z, uGamma);
            }
            z -= uGamma;
            shift += intTestKmin;
        }
    }

    /**
     * zが小さいときに有効なExp法.
     */
    private int byExpMethod(BaseRandom random, double z) {
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
    private int byDirichletMethod(BaseRandom random, int initK, double initZ, double initW) {
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
            GammaRnd gRndK = gammaRnds[kBit];
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
