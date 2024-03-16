/**
 * 2024.2.21
 */
package matsu.num.statistics.random.service.staticgamma;

import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * このパッケージに用意された {@link StaticGammaRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class MTTypeStaticGammaRndFactory implements StaticGammaRnd.Factory {

    private final StaticGammaRnd instance;

    public MTTypeStaticGammaRndFactory(RandomGeneratorFactoryProvider provider) {
        super();
        this.instance = new MTTypeStaticGammaRnd(
                provider.lib().exponentiation(),
                provider.get(RandomGeneratorType.EXPONENTIAL_RND),
                provider.get(RandomGeneratorType.NORMAL_RND));
    }

    @Override
    public StaticGammaRnd instance() {
        return this.instance;
    }

    @Override
    public String toString() {
        return "StaticGammaRnd.Factory";
    }
}
