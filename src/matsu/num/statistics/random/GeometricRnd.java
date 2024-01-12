/*
 * 2024.1.8
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.geo.GeometricRndFactory;

/**
 * <p>
 * 幾何分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 幾何分布の確率質量関数 P(<i>k</i>) は次のとおりである
 * (<i>p</i>は成功確率).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>k</i>) =
 * <i>p</i> (1 - <i>p</i>)<sup><i>k</i> - 1</sup>
 * &emsp; (<i>k</i> &ge; 1)
 * </li>
 * 
 * <li>P(<i>k</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 *
 * @author Matsuura Y.
 * @version 17.4
 */
public interface GeometricRnd extends IntegerRandomGenerator {

    /**
     * <p>
     * このインスタンスが扱う成功確率の値を返す.
     * </p>
     *
     * @return 成功確率
     */
    public abstract double successPobability();

    /**
     * <p>
     * 指定した成功確率の幾何分布乱数発生器インスタンスを返す.
     * </p>
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
        return GeometricRndFactory.instanceOf(p);
    }
}
