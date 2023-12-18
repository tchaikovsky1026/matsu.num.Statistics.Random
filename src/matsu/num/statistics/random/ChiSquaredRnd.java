/*
 * 2023.3.22
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.chisq.gamma.ByGaChiSqRnd;

/**
 * カイ二乗分布に従う乱数発生器を扱う. <br>
 * カイ二乗分布の確率密度関数 P(<i>x</i>) は,  <br>
 * P(<i>x</i>) &prop;
 * <ul>
 * <li><i>x</i><sup><i>k</i>/2 - 1</sup> exp(-<i>x</i>/2) (<i>x</i> &ge; 0)</li>
 * <li>0 (otherwise)</li>
 * </ul>
 * である.
 * <i>k</i>は自由度. <br>
 * 扱える<i>k</i> は, {@code 2.0E-2 <= k <= 2.0E+28} である.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public interface ChiSquaredRnd extends FloatingRandomGenerator {

    /**
     * このインスタンスが扱う自由度の値を返す.
     *
     * @return 自由度
     */
    public abstract double degreesOfFreedom();

    /**
     * 指定した自由度のカイ二乗分布乱数発生器インスタンスを返す. 
     *
     * @param k 自由度
     * @return 自由度がkのカイ二乗分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static ChiSquaredRnd instanceOf(double k) {
        return ByGaChiSqRnd.instanceOf(k);
    }
}
