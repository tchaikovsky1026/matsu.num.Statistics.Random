/**
 * 2024.1.8
 */
package matsu.num.statistics.random.gumbel;

import matsu.num.statistics.random.GumbelRnd;

/**
 * このパッケージに用意された {@linkplain GumbelRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class GumbelRndFactory {

    private static final GumbelRnd INSTANCE = new UniZiggGumbelRnd();

    private GumbelRndFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * Ziggurat法により実装された標準ガンベル分布乱数発生器インスタンスを返す.
     *
     * @return 標準ガンベル分布乱数発生器インスタンス
     */
    public static GumbelRnd instance() {
        return INSTANCE;
    }

}
