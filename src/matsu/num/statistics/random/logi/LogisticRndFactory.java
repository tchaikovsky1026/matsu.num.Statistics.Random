/**
 * 2024.1.8
 */
package matsu.num.statistics.random.logi;

import matsu.num.statistics.random.LogisticRnd;

/**
 * このパッケージに用意された {@linkplain LogisticRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class LogisticRndFactory {

    private static final LogisticRnd INSTANCE = new ZiggLogiRnd();

    private LogisticRndFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * Ziggurat法により実装された標準ロジスティック分布乱数発生器インスタンスを返す.
     *
     * @return 標準ロジスティック分布乱数発生器インスタンス
     */
    public static LogisticRnd instance() {
        return INSTANCE;
    }
}
