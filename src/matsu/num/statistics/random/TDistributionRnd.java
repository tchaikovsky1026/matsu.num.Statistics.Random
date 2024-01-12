/*
 * 2024.1.9
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.tdist.TDistributionRndFactory;

/**
 * Student-t分布に従う乱数発生器を扱う.
 * 
 * <p>
 * Student-t分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>&nu;</i> は自由度). <br>
 * P(<i>x</i>) &prop;
 * (1 + <i>x</i><sup><i>2</i></sup> / <i>&nu;</i>)
 * <sup>-(<i>&nu;</i> + 1) / 2</sup>
 * </p>
 *
 * @author Matsuura Y.
 * @version 17.4
 */
public interface TDistributionRnd extends FloatingRandomGenerator {

    /**
     * <p>
     * このインスタンスが扱う自由度 <i>&nu;</i> の値を返す.
     * </p>
     *
     * @return 自由度 <i>&nu;</i>
     */
    public abstract double degreesOfFreedom();

    /**
     * <p>
     * 指定した自由度のStudent-t分布乱数発生器インスタンスを返す.
     * </p>
     * 
     * <p>
     * 扱える自由度 <i>&nu;</i> は, <br>
     * {@code 2.0E-2 <= nu <= 2.0E+28} <br>
     * である.
     * </p>
     *
     * @param nu 自由度
     * @return 自由度が <i>&nu;</i> のStudent-t分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static TDistributionRnd instanceOf(double nu) {
        return TDistributionRndFactory.instanceOf(nu);
    }
}
