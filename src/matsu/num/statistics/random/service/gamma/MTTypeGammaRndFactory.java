/**
 * 2024.2.23
 */
package matsu.num.statistics.random.service.gamma;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.service.Exponentiation;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * Marsaglia-Tsangに基づく, ガンマ乱数生成器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 18.3
 */
public final class MTTypeGammaRndFactory implements GammaRnd.Factory {

    private final GammaRnd gammaRndAt1;

    private final Exponentiation exponentiation;
    private final ExponentialRnd.Factory exponentialRndFactory;
    private final NormalRnd.Factory normalRndFactory;

    public MTTypeGammaRndFactory(RandomGeneratorFactoryProvider provider) {
        super();
        this.exponentiation = provider.lib().exponentiation();
        this.exponentialRndFactory = provider.get(RandomGeneratorType.EXPONENTIAL_RND);
        this.gammaRndAt1 = new ExpBasedGammaRndAt1(this.exponentialRndFactory);
        this.normalRndFactory = provider.get(RandomGeneratorType.NORMAL_RND);
    }

    @Override
    public boolean acceptsParameter(double k) {
        return GammaRnd.LOWER_LIMIT_SHAPE_PARAMETER <= k && k <= GammaRnd.UPPER_LIMIT_SHAPE_PARAMETER;
    }

    @Override
    public GammaRnd instanceOf(double k) {
        if (!this.acceptsParameter(k)) {
            throw new IllegalArgumentException(String.format("パラメータ不正:k=%s", k));
        }

        //MarsagliaTsangに基づく乱数生成器
        if (k < 1) {
            return new MTTypeGammaRndUnder1(
                    k, this.exponentiation, this.exponentialRndFactory, this.normalRndFactory);
        }
        if (k == 1) {
            return gammaRndAt1;
        }

        return new MTTypeGammaRndOver1(k, this.exponentiation, this.normalRndFactory);
    }

    @Override
    public String toString() {
        return "GammaRnd.Factory";
    }

}
