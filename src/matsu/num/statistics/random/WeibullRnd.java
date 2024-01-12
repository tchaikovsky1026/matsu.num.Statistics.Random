/*
 * 2024.1.9
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.weibull.WeibullRndFactory;

/**
 * <p>
 * 標準Weibull分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準Weibull分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>k</i> は形状パラメータ).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup><i>k</i> - 1</sup> exp(-<i>x</i><sup><i>k</i></sup>)
 * &emsp; (<i>x</i> &ge; 0)
 * </li>
 * 
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public interface WeibullRnd extends FloatingRandomGenerator {

    /**
     * <p>
     * このインスタンスが扱う形状パラメータ <i>k</i> の値を返す.
     * </p>
     *
     * @return 形状パラメータ <i>k</i>
     */
    public abstract double shapeParameter();

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
        return WeibullRndFactory.instanceOf(k);
    }
}
