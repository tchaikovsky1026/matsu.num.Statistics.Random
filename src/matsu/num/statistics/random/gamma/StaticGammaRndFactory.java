/**
 * 2024.1.9
 */
package matsu.num.statistics.random.gamma;

import matsu.num.statistics.random.StaticGammaRnd;

/**
 * このパッケージに用意された {@linkplain StaticGammaRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class StaticGammaRndFactory {

    private static final StaticGammaRnd INSTANCE = new MTTypeStaticGammaRnd();

    private StaticGammaRndFactory() {
        //インスタンス生成不可
        throw new AssertionError();
    }

    /**
     * <p>
     * Staticガンマ乱数発生器インスタンスを返す.
     * </p>
     *
     * @return Staticガンマ乱数発生器インスタンス
     */
    public static StaticGammaRnd instance() {
        return INSTANCE;
    }
}
