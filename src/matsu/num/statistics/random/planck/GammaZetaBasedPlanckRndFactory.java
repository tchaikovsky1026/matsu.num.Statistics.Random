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

import java.util.Objects;

import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.PlanckRnd;
import matsu.num.statistics.random.ZetaRnd;

/**
 * ガンマ-ゼータ乱数を基にした Planck 乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 */
public final class GammaZetaBasedPlanckRndFactory extends SkeletalPlanckRnd.Factory {

    private final GammaRnd.Factory gammaRndFactory;
    private final ZetaRnd.Factory zetaRndFactory;

    /**
     * 唯一のコンストラクタ. <br>
     * バリデーションはしていない.
     */
    private GammaZetaBasedPlanckRndFactory(
            GammaRnd.Factory gammaRndFactory, ZetaRnd.Factory zetaRndFactory) {
        super();
        this.gammaRndFactory = gammaRndFactory;
        this.zetaRndFactory = zetaRndFactory;
    }

    @Override
    PlanckRnd createInstanceOf(double alpha) {
        return ZetaRnd.acceptsParameter(alpha + 1)
                ? new GammaZetaBasedPlanckRnd_LowMiddle(alpha, gammaRndFactory, zetaRndFactory)
                : new GammaZetaBasedPlanckRnd_High(alpha, gammaRndFactory);
    }

    /**
     * ファクトリを生成する.
     * 
     * @param gammaRndFactory ガンマ乱数発生器ファクトリ
     * @param zetaRndFactory ゼータ乱数発生器ファクトリ
     * @return Planck 乱数ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static PlanckRnd.Factory create(
            GammaRnd.Factory gammaRndFactory, ZetaRnd.Factory zetaRndFactory) {
        return new GammaZetaBasedPlanckRndFactory(
                Objects.requireNonNull(gammaRndFactory),
                Objects.requireNonNull(zetaRndFactory));
    }

}
