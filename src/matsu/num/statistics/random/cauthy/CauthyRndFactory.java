/**
 * 2024.1.8
 */
package matsu.num.statistics.random.cauthy;

import matsu.num.statistics.random.CauthyRnd;

/**
 * このパッケージに用意された {@linkplain CauthyRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class CauthyRndFactory {

    private static final CauthyRnd INSTANCE = new ZiggCauthyRnd();

    private CauthyRndFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }
    
    /**
     * 標準Cauthy分布乱数発生器インスタンスを返す.
     *
     * @return 標準Cauthy分布乱数発生器インスタンス
     */
    public static CauthyRnd instance() {
        return INSTANCE;
    }
}
