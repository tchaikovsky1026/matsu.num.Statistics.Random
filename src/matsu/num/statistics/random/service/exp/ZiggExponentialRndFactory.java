/**
 * 2024.2.21
 */
package matsu.num.statistics.random.service.exp;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;

/**
 * Ziggurat法による標準指数乱数のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class ZiggExponentialRndFactory implements ExponentialRnd.Factory {

    private final ExponentialRnd expRnd;

    public ZiggExponentialRndFactory(RandomGeneratorFactoryProvider provider) {
        super();
        this.expRnd = new ZiggExponentialRnd(provider.lib().exponentiation());
    }

    @Override
    public ExponentialRnd instance() {
        return this.expRnd;
    }

    @Override
    public String toString() {
        return "ExponentialRnd.Factory";
    }

}
