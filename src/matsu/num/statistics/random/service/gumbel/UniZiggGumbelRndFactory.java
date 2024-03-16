/**
 * 2024.2.21
 */
package matsu.num.statistics.random.service.gumbel;

import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * 単峰Zigg標準Gumbel分布に従う乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class UniZiggGumbelRndFactory implements GumbelRnd.Factory {

    private final GumbelRnd instance;

    public UniZiggGumbelRndFactory(RandomGeneratorFactoryProvider provider) {
        this.instance = new UniZiggGumbelRnd(
                provider.lib().exponentiation(), 
                provider.get(RandomGeneratorType.EXPONENTIAL_RND));
    }

    @Override
    public GumbelRnd instance() {
        return this.instance;
    }

    @Override
    public String toString() {
        return "GumbelRnd.Factory";
    }
}
