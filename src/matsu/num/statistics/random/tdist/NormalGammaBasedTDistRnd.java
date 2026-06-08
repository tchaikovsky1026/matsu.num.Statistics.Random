/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.6.8
 */
package matsu.num.statistics.random.tdist;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.UnexpectedRandomGenerationException;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * 正規分布とガンマ分布乱数発生器を利用した, t分布に従う乱数発生器を扱う.
 *
 * @author Matsuura Y.
 */
public final class NormalGammaBasedTDistRnd extends SkeletalTDistributionRnd {

    private final GammaRnd gammaRnd;
    private final Exponentiation exponentiation;

    /**
     * 指定した自由度のt分布乱数発生器インスタンスを構築する.
     * 
     * <p>
     * 扱える自由度 <i>&nu;</i> は, {@code 2.0E-2 <= nu <= 2.0E+28} である.
     * </p>
     *
     * @param nu 自由度
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    private NormalGammaBasedTDistRnd(double nu,
            Exponentiation exponentiation,
            GammaRnd.Factory gammaRndFactory) {
        super(nu);

        this.exponentiation = exponentiation;
        this.gammaRnd = gammaRndFactory.instanceOf(nu * 0.5);
    }

    @Override
    public final double nextRandom(BaseRandom random) {
        double out;

        // 乱数生成異常を検知するためのiterationCount
        int iteCount = 0;
        do {
            iteCount++;
            if (iteCount >= Integer.MAX_VALUE) {
                // 乱数生成の異常
                throw new UnexpectedRandomGenerationException();
            }

            out = random.nextGaussian()
                    / exponentiation.sqrt(this.gammaRnd.nextRandom(random) * 2 / this.nu);

            // outが (0/0) や (inf/inf)　となった場合のフォロー
        } while (Double.isNaN(out));
        return out;
    }

    /**
     * 正規ガンマタイプの {@link TDistributionRnd} のファクトリインスタンスを生成する.
     * 
     * @param exponentiation 指数関数の計算
     * @param gammaRndFactory ガンマ乱数生成器のファクトリ
     * @return 乱数生成器ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static TDistributionRnd.Factory createFactory(
            Exponentiation exponentiation,
            GammaRnd.Factory gammaRndFactory) {

        return new Factory(
                Objects.requireNonNull(exponentiation),
                Objects.requireNonNull(gammaRndFactory));
    }

    /**
     * 正規ガンマタイプのt分布に従う乱数発生器のファクトリ.
     */
    private static final class Factory extends SkeletalTDistributionRnd.Factory {

        private final Exponentiation exponentiation;
        private final GammaRnd.Factory gammaRndFactory;

        Factory(
                Exponentiation exponentiation,
                GammaRnd.Factory gammaRndFactory) {
            super();
            this.exponentiation = exponentiation;
            this.gammaRndFactory = gammaRndFactory;
        }

        @Override
        TDistributionRnd createInstanceOf(double nu) {
            return new NormalGammaBasedTDistRnd(nu, this.exponentiation, this.gammaRndFactory);
        }
    }
}
