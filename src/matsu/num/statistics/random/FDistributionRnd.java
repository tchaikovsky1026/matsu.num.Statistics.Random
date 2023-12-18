/*
 * 2023.3.22
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.fdist.beta.ByBeFRnd;

/**
 * F分布に従う乱数発生器を扱う. <br>
 * F分布の確率密度関数 P(<i>x</i>) は,  <br>
 * P(<i>x</i>) &prop;
 * <ul>
 * <li><i>x</i><sup>(<i>d</i><sub>1</sub> - 2)/2</sup>/(1 +
 * (<i>d</i><sub>1</sub>/<i>d</i><sub>2</sub>)<i>x</i>)<sup>(<i>d</i><sub>1</sub>
 * +
 * <i>d</i><sub>2</sub>)/2</sup> &emsp; (<i>x</i> &ge; 0)</li>
 * <li>0 &emsp; (otherwise)</li>
 * </ul>
 * である. <br>
 * <i>d</i><sub>1</sub>, <i>d</i><sub>2</sub>は自由度. <br>
 * 扱える <i>d</i><sub>1</sub>, <i>d</i><sub>2</sub> は,
 * {@code 2.0E-2 <= (d1,d2) <= 2.0E+14} である.
 *
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public interface FDistributionRnd extends FloatingRandomGenerator {

    /**
     * このインスタンスが扱う分子自由度d1の値を返す.
     *
     * @return 分子自由度d1
     */
    public abstract double numeratorDegreesOfFreedom();

    /**
     * このインスタンスが扱う分母自由度d2の値を返す.
     *
     * @return 分母自由度d2
     */
    public abstract double denominatorDegreesOfFreedom();

    /**
     * 指定した自由度のF分布乱数発生器インスタンスを返す. 
     *
     * @param d1 分子自由度
     * @param d2 分母自由度
     * @return 自由度がd1,d2のF分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static FDistributionRnd instanceOf(double d1, double d2) {
        return ByBeFRnd.instanceOf(d1, d2);
    }
}
