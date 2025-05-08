/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.6
 */
package matsu.num.statistics.random.chisq;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ChiSquaredRnd;
import matsu.num.statistics.random.GammaRnd;

/**
 * ガンマ分布乱数器を利用した, カイ二乗分布に従う乱数発生器.
 * 
 * <p>
 * 自由度kのカイ二乗分布は, 形状パラメータk/2, スケールパラメータ2のガンマ分布である.
 * </p>
 *
 * @author Matsuura Y.
 */
public final class GammaTypeChiSquaredRnd extends SkeletalChiSquaredRnd {

    private final GammaRnd gammaRnd;

    private GammaTypeChiSquaredRnd(double k, GammaRnd.Factory gammaRndBuilder) {
        super(k);

        this.gammaRnd = gammaRndBuilder.instanceOf(k * 0.5);
    }

    @Override
    public final double nextRandom(BaseRandom random) {
        return this.gammaRnd.nextRandom(random) * 2;
    }

    /**
     * ガンマ分布乱数器を利用した {@link ChiSquaredRnd} 実装のインスタンス生成を扱う.
     * 
     * @param gammaRndFactory ガンマ乱数生成器のファクトリ
     * @return 乱数生成器ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static ChiSquaredRnd.Factory createFactory(GammaRnd.Factory gammaRndFactory) {
        return new Factory(Objects.requireNonNull(gammaRndFactory));
    }

    private static final class Factory extends SkeletalChiSquaredRnd.Factory {

        private final GammaRnd.Factory gammaRndFactory;

        Factory(GammaRnd.Factory gammaRndFactory) {
            this.gammaRndFactory = gammaRndFactory;
        }

        @Override
        ChiSquaredRnd createInstanceOf(double k) {
            return new GammaTypeChiSquaredRnd(k, this.gammaRndFactory);
        }
    }
}
