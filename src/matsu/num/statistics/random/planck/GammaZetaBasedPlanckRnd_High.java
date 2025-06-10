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
 * <p>
 * ガンマ-ゼータ乱数を基にした,
 * パラメータが大きい Planck 乱数を扱う.
 * </p>
 * 
 * <p>
 * パラメータが大きい場合, ZetaRndは確率1で1を返す. <br>
 * この場合, ZetaRndは必要ない.
 * </p>
 * 
 * @author Matsuura Y.
 */
final class GammaZetaBasedPlanckRnd_High extends SkeletalPlanckRnd {

    private final GammaRnd gammaRnd;

    /**
     * 唯一のコンストラクタ.
     * 
     * <p>
     * (alpha + 1) が ZetaRndの パラメータに使用できてはいけない
     * (アサーションエラー). <br>
     * 呼び出しもとでバリデーションが必要.
     * </p>
     */
    GammaZetaBasedPlanckRnd_High(double alpha,
            GammaRnd.Factory gammaRndFactory) {
        super(alpha);

        assert !ZetaRnd.acceptsParameter(alpha + 1d) : "alphaが不正";

        this.gammaRnd = gammaRndFactory.instanceOf(alpha + 1d);
    }

    @Override
    public double nextRandom(BaseRandom random) {
        return this.gammaRnd.nextRandom(random);
    }
}
