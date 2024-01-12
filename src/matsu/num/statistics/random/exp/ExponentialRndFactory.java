/**
 * 2024.1.8
 */
package matsu.num.statistics.random.exp;

import matsu.num.statistics.random.ExponentialRnd;

/**
 * このパッケージに用意された {@linkplain ExponentialRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class ExponentialRndFactory {

    private static final ExponentialRnd INSTANCE = new ZiggExpRnd();

    private ExponentialRndFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * Ziggurat法により実装された標準指数分布乱数発生器インスタンスを返す.
     *
     * @return 標準指数分布乱数発生器インスタンス
     */
    public static ExponentialRnd instance() {
        return INSTANCE;
    }
}
