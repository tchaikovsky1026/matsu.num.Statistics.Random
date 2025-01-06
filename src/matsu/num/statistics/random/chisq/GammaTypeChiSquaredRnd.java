/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.chisq;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GammaRnd;

/**
 * ガンマ分布乱数器を利用した, カイ二乗分布に従う乱数発生器.
 * 
 * <p>
 * 自由度kのカイ二乗分布は, 形状パラメータk/2, スケールパラメータ2のガンマ分布である.
 * </p>
 *
 * @author Matsuura Y.
 * @version 21.0
 */
final class GammaTypeChiSquaredRnd extends SkeletalChiSquaredRnd {

    private final GammaRnd gammaRnd;

    GammaTypeChiSquaredRnd(double k, GammaRnd.Factory gammaRndBuilder) {
        super(k);

        this.gammaRnd = gammaRndBuilder.instanceOf(k * 0.5);
    }

    @Override
    public final double nextRandom(BaseRandom random) {
        return this.gammaRnd.nextRandom(random) * 2;
    }
}
