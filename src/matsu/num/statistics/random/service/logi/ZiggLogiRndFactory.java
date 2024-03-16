/**
 * 2024.2.21
 */
package matsu.num.statistics.random.service.logi;

import matsu.num.statistics.random.LogisticRnd;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * Ziggurat法による標準ロジスティック分布乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class ZiggLogiRndFactory implements LogisticRnd.Factory {

    private final LogisticRnd instance;

    public ZiggLogiRndFactory(RandomGeneratorFactoryProvider provider) {
        super();
        this.instance = new ZiggLogiRnd(
                provider.lib().exponentiation(),
                provider.get(RandomGeneratorType.EXPONENTIAL_RND));
    }

    @Override
    public LogisticRnd instance() {
        return this.instance;
    }

    @Override
    public String toString() {
        return "LogisticRnd.Factory";
    }
}
