/**
 * 2024.2.23
 */
package matsu.num.statistics.random.service.weibull;

import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.WeibullRnd;
import matsu.num.statistics.random.service.Exponentiation;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * 標準Gumbelベースの標準Weibull分布乱数生成器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class GumbelBasedWeibullRndFactory implements WeibullRnd.Factory {

    private final Exponentiation exponentiation;
    private final GumbelRnd.Factory gumbelRndFactory;

    public GumbelBasedWeibullRndFactory(RandomGeneratorFactoryProvider provider) {
        super();
        this.exponentiation = provider.lib().exponentiation();
        this.gumbelRndFactory = provider.get(RandomGeneratorType.GUMBEL_RND);
    }

    @Override
    public boolean acceptsParameter(double k) {
        return WeibullRnd.LOWER_LIMIT_SHAPE_PARAMETER <= k
                && k <= WeibullRnd.UPPER_LIMIT_SHAPE_PARAMETER;
    }

    @Override
    public WeibullRnd instanceOf(double k) {
        if (!this.acceptsParameter(k)) {
            throw new IllegalArgumentException(
                    String.format(
                            "パラメータ不正:k=%s", k));
        }
        return new GumbelBasedWeibullRnd(k, this.exponentiation, this.gumbelRndFactory);
    }

    @Override
    public String toString() {
        return "WeibullRnd.Factory";
    }
}
