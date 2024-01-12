/**
 * 2024.1.9
 */
package matsu.num.statistics.random.beta;

import matsu.num.statistics.random.StaticBetaRnd;

/**
 * このパッケージが扱う, {@linkplain StaticBetaRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class StaticBetaRndFactory {

    private static final StaticBetaRnd INSTANCE = new GammaBasedStaticBetaRnd();

    private StaticBetaRndFactory() {
        //インスタンス生成不可
        throw new AssertionError();
    }

    /**
     * <p>
     * Staticベータ乱数発生器インスタンスを返す.
     * </p>
     *
     * @return Staticベータ乱数発生器インスタンス
     */
    public static StaticBetaRnd instance() {
        return INSTANCE;
    }
}
