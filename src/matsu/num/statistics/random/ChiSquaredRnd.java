/*
 * 2024.1.8
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.chisq.ChiSquaredRndFactory;

/**
 * <p>
 * カイ二乗分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * カイ二乗分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>k</i> は自由度).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup><i>k</i> / 2 - 1</sup>
 * exp(-<i>x</i> / 2)
 * &emsp; (<i>x</i> &ge; 0)
 * </li>
 * 
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 *
 * @author Matsuura Y.
 * @version 17.4
 */
public interface ChiSquaredRnd extends FloatingRandomGenerator {

    /**
     * <p>
     * このインスタンスが扱う自由度 <i>k</i> の値を返す.
     * </p>
     *
     * @return 自由度 <i>k</i>
     */
    public abstract double degreesOfFreedom();

    /**
     * <p>
     * 指定した自由度のカイ二乗分布乱数発生器インスタンスを返す.
     * </p>
     * 
     * <p>
     * 扱える自由度 <i>k</i> は, <br>
     * {@code 2.0E-2 <= k <= 2.0E+28} <br>
     * である.
     * </p>
     *
     * @param k 自由度
     * @return 自由度が <i>k</i> のカイ二乗分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static ChiSquaredRnd instanceOf(double k) {
        return ChiSquaredRndFactory.instanceOf(k);
    }
}
