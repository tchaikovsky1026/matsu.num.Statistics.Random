/**
 * 2024.2.21
 */
package matsu.num.statistics.random.service.staticbeta;

import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * このパッケージが扱う, {@link StaticBetaRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class GammaBasedStaticBetaRndFactory implements StaticBetaRnd.Factory {

    private final StaticBetaRnd instance;

    public GammaBasedStaticBetaRndFactory(RandomGeneratorFactoryProvider provider) {
        this.instance = new GammaBasedStaticBetaRnd(provider.get(RandomGeneratorType.STATIC_GAMMA_RND));
    }

    /**
     * <p>
     * Staticベータ乱数発生器インスタンスを返す.
     * </p>
     *
     * @return Staticベータ乱数発生器インスタンス
     */
    @Override
    public StaticBetaRnd instance() {
        return this.instance;
    }

    @Override
    public String toString() {
        return "StaticBetaRnd.Factory";
    }
}
