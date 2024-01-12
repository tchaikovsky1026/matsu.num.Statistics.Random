/**
 * 2024.1.8
 */
package matsu.num.statistics.random.geo;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GeometricRnd;

/**
 * このパッケージに用意された {@linkplain ExponentialRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class GeometricRndFactory {

    private GeometricRndFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * 指定した成功確率の幾何分布乱数発生器インスタンスを返す.
     * 
     * <p>
     * 扱える成功確率 <i>p</i> は, {@code 1.0E-7 <= p <= 1.0} である.
     * </p>
     *
     * @param p 成功確率
     * @return 成功確率がpの幾何分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static GeometricRnd instanceOf(double p) {
        return new InversionBasedGeoRnd(p);
    }
}
