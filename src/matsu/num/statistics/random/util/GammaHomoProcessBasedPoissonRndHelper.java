/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.8
 */
package matsu.num.statistics.random.util;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * <p>
 * ガンマ分布乱数発生器を利用した, Poisson分布に従う乱数発生器を扱う内部ヘルパ. <br>
 * 扱える lambda の最大値は定数が用意されている (最小値は0).
 * </p>
 * 
 * <p>
 * このクラスはメソッドの契約違反に対して適切に例外がスローされるようになっていない. <br>
 * したがって, モジュール外には非公開でなければならない.
 * </p>
 * 
 * @author Matsuura Y.
 */
public final class GammaHomoProcessBasedPoissonRndHelper {

    /**
     * lambdaの範囲を網羅できるための, ガンマ乱数の形状パラメータのビット数. <br>
     * 1,2,4,...,2^29までで, 29.
     */
    private static final int GAMMA_RND_BIT = 29;

    /**
     * 扱うことができる最大のlambda
     */
    public static final double MAX_LAMBDA = (1 << GAMMA_RND_BIT);

    /**
     * 最大は 2^29 (GAMMA_RND_BIT = 29)
     */
    private final GammaRndPower2Storage gammaRndPower2Storages;

    private final Exponentiation exponentiation;

    /**
     * 唯一のコンストラクタ.
     * 
     * @param exponentiation 指数関数計算器
     * @param gammaRndFactory ガンマ乱数発生器ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public GammaHomoProcessBasedPoissonRndHelper(
            Exponentiation exponentiation, GammaRnd.Factory gammaRndFactory) {
        super();

        this.gammaRndPower2Storages = GammaRndPower2Storage.create(
                GAMMA_RND_BIT + 1, gammaRndFactory);
        this.exponentiation = Objects.requireNonNull(exponentiation);
    }

    /**
     * 与えたlambdaに対するPoisson乱数を生成する.
     * 
     * <p>
     * 注意: 引数lambdaが不正の場合にも例外はスローされない. <br>
     * 引数に関する契約は, クラス説明の通り.
     * </p>
     * 
     * @param lambda lambda
     * @param random random
     * @return Poisson乱数
     */
    public int next(double lambda, BaseRandom random) {
        assert 0 <= lambda && lambda <= MAX_LAMBDA : "Illegal parameter: lambda = " + lambda;

        double z = lambda;
        int shift = 0;
        while (true) {
            if (z < 4) {
                shift += byExpMethod(random, z);
                break;
            }

            // (z/2)を超えない最大の2の累乗数
            int intTestKmin = Integer.highestOneBit((int) z >> 1);

            double uGamma = this.gammaRndPower2Storages.getAt(
                    Power2Util.floorLog2(intTestKmin))
                    .nextRandom(random);
            if (uGamma > z) {
                shift += byDirichletMethod(random, intTestKmin, z, uGamma);
                break;
            }
            z -= uGamma;
            shift += intTestKmin;
        }

        // shift < 0 の場合, オーバーフローしたと思われる
        return shift < 0
                ? 0
                : shift;
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
            GammaRnd gRndK = this.gammaRndPower2Storages.getAt(kBit);
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
