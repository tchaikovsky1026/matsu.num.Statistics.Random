/**
 * 2024.1.8
 */
package matsu.num.statistics.random.norm;

import matsu.num.statistics.random.NormalRnd;

/**
 * このパッケージに用意された {@linkplain NormalRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class NormalRndFactory {

    private static final NormalRnd INSTANCE = new ZiggNormRnd();

    private NormalRndFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * Ziggurat法により実装された標準正規分布乱数発生器インスタンスを返す.
     *
     * @return 標準正規分布乱数発生器インスタンス
     */
    public static NormalRnd instance() {
        return INSTANCE;
    }
}
