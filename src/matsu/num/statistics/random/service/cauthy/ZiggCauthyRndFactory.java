/**
 * 2024.2.21
 */
package matsu.num.statistics.random.service.cauthy;

import matsu.num.statistics.random.CauthyRnd;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;

/**
 * このパッケージに用意された {@link CauthyRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class ZiggCauthyRndFactory implements CauthyRnd.Factory {

    private final CauthyRnd instance;

    public ZiggCauthyRndFactory(RandomGeneratorFactoryProvider provider) {
        this.instance = new ZiggCauthyRnd(provider.lib().exponentiation());
    }

    @Override
    public CauthyRnd instance() {
        return this.instance;
    }

    @Override
    public String toString() {
        return "CauthyRnd.Factory";
    }
}
