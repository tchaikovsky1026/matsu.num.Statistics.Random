/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.23
 */
package matsu.num.statistics.random;

/**
 * Cauchy分布のt分布としてのビューを扱うクラス.
 * 
 * @author Matsuura Y.
 * @deprecated このクラスは version 26 以降削除される. <br>
 *                 {@link CauchyRnd#asTDistributionRnd() } のデフォルト実装を返すためだけに存在する.
 */
@Deprecated
final class CauchyRndTDistView implements TDistributionRnd {

    private final CauchyRnd cauchyRnd;

    /**
     * 唯一のコンストラクタ.
     */
    CauchyRndTDistView(CauchyRnd cauchyRnd) {
        this.cauchyRnd = cauchyRnd;
    }

    @Override
    public final double degreesOfFreedom() {
        return 1d;
    }

    @Override
    public String toString() {
        return String.format(
                "TDistRnd(nu = %s)", this.degreesOfFreedom());
    }

    @Override
    public double nextRandom(BaseRandom random) {
        return this.cauchyRnd.nextRandom(random);
    }
}
