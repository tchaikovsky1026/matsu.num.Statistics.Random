/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.9
 */
package matsu.num.statistics.random.planck;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.ZetaRnd;

/**
 * ガンマ-ゼータ乱数を基にした,
 * パラメータが小, 中程度の Planck 乱数を扱う.
 * 
 * @author Matsuura Y.
 */
final class GammaZetaBasedPlanckRnd_LowMiddle extends SkeletalPlanckRnd {

    private final GammaRnd gammaRnd;
    private final ZetaRnd zetaRnd;

    /**
     * 唯一のコンストラクタ.
     * 
     * <p>
     * (alpha + 1) が ZetaRndの パラメータに適さなければならない
     * (アサーションエラー). <br>
     * 呼び出しもとでバリデーションが必要.
     * </p>
     */
    GammaZetaBasedPlanckRnd_LowMiddle(double alpha,
            GammaRnd.Factory gammaRndFactory, ZetaRnd.Factory zetaRndFactory) {
        super(alpha);

        assert ZetaRnd.acceptsParameter(alpha + 1d) : "alphaが不正";

        this.gammaRnd = gammaRndFactory.instanceOf(alpha + 1d);
        this.zetaRnd = zetaRndFactory.instanceOf(alpha + 1d);
    }

    @Override
    public double nextRandom(BaseRandom random) {
        return this.gammaRnd.nextRandom(random) / this.zetaRnd.nextRandom(random);
    }
}
