/**
 * 2024.1.9
 */
package matsu.num.statistics.random.tdist;

import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.common.Exponentiation;
import matsu.num.statistics.random.common.ExponentiationFactory;
import matsu.num.statistics.random.gamma.GammaRndFactory;
import matsu.num.statistics.random.norm.NormalRndFactory;

/**
 * 正規分布とガンマ分布乱数発生器を利用した, t分布に従う乱数発生器を扱う.
 * 
 * <p>
 * 扱える自由度 <i>&nu;</i> は, {@code 2.0E-2 <= nu <= 2.0E+28} である.
 * </p>
 *
 * @author Matsuura Y.
 * @version 17.5
 */
final class NormalGammaBasedTDistRnd implements TDistributionRnd {

    private final double nu;
    private final GammaRnd gammaRnd;

    private final NormalRnd normalRnd = NormalRndFactory.instance();

    private final Exponentiation exponentiation = ExponentiationFactory.instance();

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
    NormalGammaBasedTDistRnd(double nu) {
        this.gammaRnd = GammaRndFactory.instanceOf(nu * 0.5);
        this.nu = nu;
    }

    @Override
    public final double degreesOfFreedom() {
        return this.nu;
    }

    @Override
    public final double nextRandom(Random random) {
        return this.normalRnd.nextRandom(random)
                / exponentiation.sqrt(this.gammaRnd.nextRandom(random) * 2 / this.nu);
    }

    @Override
    public String toString() {
        return String.format(
                "TDistRnd(%s)", this.degreesOfFreedom());
    }
}
