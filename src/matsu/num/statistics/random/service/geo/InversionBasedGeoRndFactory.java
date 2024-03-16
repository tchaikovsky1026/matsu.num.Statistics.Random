/**
 * 2024.2.23
 */
package matsu.num.statistics.random.service.geo;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GeometricRnd;
import matsu.num.statistics.random.service.Exponentiation;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * 逆関数法に基づく, 幾何分布に従う乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class InversionBasedGeoRndFactory implements GeometricRnd.Factory {

    private final Exponentiation exponentiation;
    private final ExponentialRnd.Factory exponentialRndFactory;

    public InversionBasedGeoRndFactory(RandomGeneratorFactoryProvider provider) {
        this.exponentiation = provider.lib().exponentiation();
        this.exponentialRndFactory = provider.get(RandomGeneratorType.EXPONENTIAL_RND);
    }

    @Override
    public boolean acceptsParameter(double p) {
        return GeometricRnd.LOWER_LIMIT_SUCCESS_PROBABILITY <= p
                && p <= GeometricRnd.UPPER_LIMIT_SUCCESS_PROBABILITY;
    }

    @Override
    public GeometricRnd instanceOf(double p) {
        if (!this.acceptsParameter(p)) {
            throw new IllegalArgumentException(String.format("パラメータ不正:p=%s", p));
        }
        return new InversionBasedGeoRnd(p, this.exponentiation, this.exponentialRndFactory);
    }

    @Override
    public String toString() {
        return "GeometricRnd.Factory";
    }

}
