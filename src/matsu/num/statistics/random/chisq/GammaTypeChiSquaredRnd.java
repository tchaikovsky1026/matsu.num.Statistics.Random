/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.chisq;

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
 * @version 20.0
 */
final class GammaTypeChiSquaredRnd implements ChiSquaredRnd {

    private final double k;

    private final GammaRnd gammaRnd;

    GammaTypeChiSquaredRnd(double k, GammaRnd.Factory gammaRndBuilder) {
        super();

        this.gammaRnd = gammaRndBuilder.instanceOf(k * 0.5);
        this.k = k;
    }

    @Override
    public final double degreesOfFreedom() {
        return this.k;
    }

    @Override
    public final double nextRandom(BaseRandom random) {
        return this.gammaRnd.nextRandom(random) * 2;
    }

    @Override
    public String toString() {
        return String.format(
                "ChiSquaredRnd(%s)", this.degreesOfFreedom());
    }

}
