/*
 * 2023.3.22
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.geo.inv.InvBasedGeoRnd;

/**
 * 幾何分布に従う乱数発生器を扱う. <br>
 * 幾何分布の確率関数 P(<i>k</i>) は, <br>
 * P(<i>k</i>) =
 * <ul>
 * <li><i>p</i>(1 - <i>p</i>)<sup><i>k</i> - 1</sup> (<i>k</i> &ge; 1)</li>
 * <li>0 (otherwise)</li>
 * </ul>
 * である. <br>
 * <i>p</i>は成功確率. <br>
 * 扱える <i>p</i> は, {@code 1.0E-7 <= p <= 1.0} である.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public interface GeometricRnd extends IntegerRandomGenerator {

    /**
     * このインスタンスが扱う成功確率の値を返す.
     *
     * @return 成功確率
     */
    public abstract double successPobability();

    /**
     * 指定した成功確率の幾何分布乱数発生器インスタンスを返す. 
     *
     * @param p 成功確率
     * @return 成功確率がpの幾何分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static GeometricRnd instanceOf(double p) {
        return InvBasedGeoRnd.instanceOf(p);
    }
}
