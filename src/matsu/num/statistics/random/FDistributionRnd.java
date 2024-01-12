/*
 * 2024.1.8
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.fdist.FDistributionRndFactory;

/**
 * <p>
 * F分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * F分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>d</i><sub>1</sub>, <i>d</i><sub>2</sub> は自由度).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup>(<i>d</i><sub>1</sub> - 2) / 2</sup>
 * /
 * (1 + (<i>d</i><sub>1</sub> / <i>d</i><sub>2</sub>) <i>x</i>)
 * <sup>(<i>d</i><sub>1</sub> + <i>d</i><sub>2</sub>) / 2</sup>
 * &emsp; (<i>x</i> &ge; 0)
 * </li>
 * 
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public interface FDistributionRnd extends FloatingRandomGenerator {

    /**
     * <p>
     * このインスタンスが扱う分子自由度 <i>d</i><sub>1</sub> の値を返す.
     * </p>
     *
     * @return 分子自由度 <i>d</i><sub>1</sub>
     */
    public abstract double numeratorDegreesOfFreedom();

    /**
     * <p>
     * このインスタンスが扱う分母自由度 <i>d</i><sub>2</sub> の値を返す.
     * </p>
     *
     * @return 分母自由度 <i>d</i><sub>2</sub>
     */
    public abstract double denominatorDegreesOfFreedom();

    /**
     * <p>
     * 指定した自由度のF分布乱数発生器インスタンスを返す.
     * </p>
     * 
     * <p>
     * 扱える自由度 <i>d</i><sub>1</sub>, <i>d</i><sub>2</sub> は, <br>
     * {@code 2.0E-2 <= (d1, d2) <= 2.0E+14} <br>
     * である.
     * </p>
     *
     * @param d1 分子自由度
     * @param d2 分母自由度
     * @return 自由度が (<i>d</i><sub>1</sub>, <i>d</i><sub>2</sub>) のF分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static FDistributionRnd instanceOf(double d1, double d2) {
        return FDistributionRndFactory.instanceOf(d1, d2);
    }
}
