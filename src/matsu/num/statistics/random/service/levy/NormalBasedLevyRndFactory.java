/**
 * 2024.2.21
 */
package matsu.num.statistics.random.service.levy;

import matsu.num.statistics.random.LevyRnd;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * 標準正規分布を用いた, 標準L&eacute;vy乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class NormalBasedLevyRndFactory implements LevyRnd.Factory {

    private final LevyRnd instance;
    
    public NormalBasedLevyRndFactory(RandomGeneratorFactoryProvider provider) {
        super();
        this.instance = new NormalBasedLevyRnd(
                provider.get(RandomGeneratorType.NORMAL_RND));
    }

    @Override
    public LevyRnd instance() {
        return this.instance;
    }

    @Override
    public String toString() {
        return "LevyRnd.Factory";
    }
}
