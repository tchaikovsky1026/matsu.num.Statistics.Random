/**
 * 2024.2.23
 */
package matsu.num.statistics.random.service.tdist;

import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.service.Exponentiation;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * 正規ガンマタイプのt分布に従う乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class NormalGammaBasedTDistRndFactory implements TDistributionRnd.Factory {

    private final Exponentiation exponentiation;
    private final NormalRnd.Factory normalRndFactory;
    private final GammaRnd.Factory gammaRndFactory;

    public NormalGammaBasedTDistRndFactory(RandomGeneratorFactoryProvider provider) {
        super();
        this.exponentiation = provider.lib().exponentiation();
        this.normalRndFactory = provider.get(RandomGeneratorType.NORMAL_RND);
        this.gammaRndFactory = provider.get(RandomGeneratorType.GAMMA_RND);
    }

    @Override
    public boolean acceptsParameter(double nu) {
        return TDistributionRnd.LOWER_LIMIT_DEGREES_OF_FREEDOM <= nu
                && nu <= TDistributionRnd.UPPER_LIMIT_DEGREES_OF_FREEDOM;
    }

    @Override
    public TDistributionRnd instanceOf(double nu) {
        if (!this.acceptsParameter(nu)) {
            throw new IllegalArgumentException(String.format("パラメータ不正:nu=%s", nu));
        }
        return new NormalGammaBasedTDistRnd(nu, this.exponentiation, this.normalRndFactory, this.gammaRndFactory);
    }

    @Override
    public String toString() {
        return "TDistRnd.Factory";
    }
}
