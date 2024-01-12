/**
 * 2024.1.8
 */
package matsu.num.statistics.random.beta;

import matsu.num.statistics.random.BetaRnd;

/**
 * このパッケージに用意された {@linkplain BetaRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class BetaRndFactory {

    private BetaRndFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * <p>
     * 指定した形状パラメータのベータ分布乱数発生器インスタンスを返す.
     * </p>
     * 
     * <p>
     * 扱える形状パラメータ <i>a</i>, <i>b</i> は, <br>
     * {@code 1.0E-2 <= (a, b) <= 1.0E+14} <br>
     * である.
     * </p>
     *
     * @param a 形状パラメータ
     * @param b 形状パラメータ
     * @return 形状パラメータが (<i>a</i>, <i>b</i>) のベータ分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static BetaRnd instanceOf(double a, double b) {
        return new GammaBasedBetaEnd(a, b);
    }
}
