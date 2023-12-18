/*
 * 2023.3.22
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.tdist.normgamma.ByNormGaTRnd;

/**
 * t分布に従う乱数発生器を扱う. <br>
 * t分布の確率密度関数 P(<i>x</i>) は,  <br>
 * P(<i>x</i>) &prop; (1 +
 * <i>x</i><sup><i>2</i></sup>/<i>&nu;</i>)<sup>-(<i>&nu;</i> + 1)/2</sup>
 * <br>
 * である.
 * <i>&nu;</i>は自由度. <br>
 * 扱える <i>&nu;</i> は, {@code 2.0E-2 <= nu <= 2.0E+28} である.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public interface TDistributionRnd extends FloatingRandomGenerator {

    /**
     * このインスタンスが扱う自由度の値を返す.
     *
     * @return 自由度
     */
    public abstract double degreesOfFreedom();

    /**
     * 指定した自由度のt分布乱数発生器インスタンスを返す. 
     *
     * @param nu 自由度
     * @return 自由度がnuのt分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static TDistributionRnd instanceOf(double nu) {
        return ByNormGaTRnd.instanceOf(nu);
    }
}
