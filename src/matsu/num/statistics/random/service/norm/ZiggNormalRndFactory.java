/**
 * 2024.2.21
 */
package matsu.num.statistics.random.service.norm;

import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.service.CommonLib;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * このパッケージに用意された {@link NormalRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class ZiggNormalRndFactory implements NormalRnd.Factory {

    private final CommonLib lib;
    private final NormalRnd normalRnd;
    
    public ZiggNormalRndFactory(RandomGeneratorFactoryProvider provider) {
        super();
        this.lib = provider.lib();
        this.normalRnd = new ZiggNormalRnd(
                this.lib.exponentiation(), 
                provider.get(RandomGeneratorType.EXPONENTIAL_RND));
    }

    @Override
    public NormalRnd instance() {
        return this.normalRnd;
    }

    @Override
    public String toString() {
        return "NormalRnd.Factory";
    }
}
