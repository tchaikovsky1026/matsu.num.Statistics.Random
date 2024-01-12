/**
 * 2024.1.8
 */
package matsu.num.statistics.random.chisq;

import matsu.num.statistics.random.ChiSquaredRnd;

/**
 * このパッケージに用意された {@linkplain ChiSquaredRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class ChiSquaredRndFactory {

    private ChiSquaredRndFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }
    
    /**
     * 指定した自由度のカイ二乗分布乱数発生器インスタンスを返す.
     * 
     * <p>
     * 扱える自由度 <i>k</i> は, <br>
     * {@code 2.0E-2 <= k <= 2.0E+28} <br>
     * である.
     * </p>
     *
     * @param k 自由度
     * @return 自由度がkのカイ二乗分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static ChiSquaredRnd instanceOf(double k) {
        return new GammaTypeChiSquaredRnd(k);
    }
}
