/**
 * 2023.3.22
 */
package matsu.num.statistics.random.tdist.normgamma;

import java.util.Random;

import matsu.num.commons.Exponentiation;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.TDistributionRnd;

/**
 * 正規分布とガンマ分布乱数発生器を利用した, t分布に従う乱数発生器を扱う.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public final class ByNormGaTRnd implements TDistributionRnd {

    private final double nu;
    private final GammaRnd gammaRnd;

    private final NormalRnd normalRnd = NormalRnd.instance();

    /**
     * @throws IllegalArgumentException パラメータ異常でガンマ分布の生成に失敗
     */
    private ByNormGaTRnd(double nu) {
        this.gammaRnd = GammaRnd.instanceOf(nu * 0.5);
        this.nu = nu;
    }

    @Override
    public final double degreesOfFreedom() {
        return this.nu;
    }

    @Override
    public final double nextRandom(Random random) {
        return this.normalRnd.nextRandom(random) / Exponentiation.sqrt(this.gammaRnd.nextRandom(random) * 2 / this.nu);
    }

    /**
     * 指定した自由度のt分布乱数発生器インスタンスを返す.
     *
     * @param nu 自由度
     * @return 自由度がnuのt分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static TDistributionRnd instanceOf(double nu) {
        return new ByNormGaTRnd(nu);
    }
}
