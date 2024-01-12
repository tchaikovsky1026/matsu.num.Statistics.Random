/*
 * 2024.1.8
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.exp.ExponentialRndFactory;

/**
 * <p>
 * 標準指数分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準指数分布の確率密度関数 P(<i>x</i>) は次のとおりである.
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * exp(-<i>x</i>)
 * &emsp; (<i>x</i> &ge; 0)
 * </li>
 * 
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 *
 * @author Matsuura Y.
 * @version 17.4
 */
public interface ExponentialRnd extends FloatingRandomGenerator {

    /**
     * <p>
     * 標準指数分布乱数発生器インスタンスを返す.
     * </p>
     *
     * @return 標準指数分布乱数発生器インスタンス
     */
    public static ExponentialRnd instance() {
        return ExponentialRndFactory.instance();
    }
}
