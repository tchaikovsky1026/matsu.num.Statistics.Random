/**
 * 2024.1.9
 */
package matsu.num.statistics.random.weibull;

import matsu.num.statistics.random.WeibullRnd;

/**
 * このパッケージが扱う {@linkplain WeibullRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class WeibullRndFactory {

    private WeibullRndFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * <p>
     * 指定した形状パラメータのWeibull分布乱数発生器インスタンスを返す.
     * </p>
     * 
     * <p>
     * 扱える形状パラメータ <i>k</i> は, <br>
     * {@code 1.0E-2 <= k <= 1.0E+14} <br>
     * である.
     * </p>
     *
     * @param k 形状パラメータ
     * @return 形状パラメータが <i>k</i> のWeibull分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static WeibullRnd instanceOf(double k) {
        return new GumbelBasedWeibullRnd(k);
    }
}
