/*
 * 2023.3.22
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.exp.zigg.ZiggExpRnd;

/**
 * 標準指数分布に従う乱数発生器を扱う. <br>
 * 標準指数分布の確率密度関数 P(<i>x</i>) は,  <br>
 * P(<i>x</i>) &prop;
 * <ul>
 * <li>exp(-<i>x</i>) &emsp; (<i>x</i> &ge; 0) </li>
 * <li>0 &emsp; (otherwise)</li>
 * </ul>
 * である.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public interface ExponentialRnd extends FloatingRandomGenerator {

    /**
     * 標準指数分布乱数発生器インスタンスを返す.
     *
     * @return 標準指数分布乱数発生器インスタンス
     */
    public static ExponentialRnd instance() {
        return ZiggExpRnd.instance();
    }
}
