/**
 * 2024.1.9
 */
package matsu.num.statistics.random.levy;

import matsu.num.statistics.random.LevyRnd;

/**
 * このパッケージで扱う {@linkplain LevyRnd} の実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class LevyRndFactory {

    private static final LevyRnd INSTANCE = new NormalBasedLevyRnd();

    private LevyRndFactory() {
        //インスタンス生成不可
        throw new AssertionError();
    }

    /**
     * <p>
     * 標準L&eacute;vy分布乱数発生器インスタンスを返す.
     * </p>
     *
     * @return 標準L&eacute;vy分布乱数発生器インスタンス
     */
    public static LevyRnd instance() {
        return INSTANCE;
    }
}
