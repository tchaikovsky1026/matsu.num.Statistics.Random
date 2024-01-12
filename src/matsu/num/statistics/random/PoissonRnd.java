/*
 * 2024.1.9
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.poi.PoissonRndFactory;

/**
 * <p>
 * Poisson分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * Poisson分布の確率質量関数 P(<i>k</i>) は次のとおりである
 * (<i>&lambda;</i> はパラメータ).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>k</i>) =
 * <i>e</i><sup>-<i>&lambda;</i></sup>
 * <i>&lambda;</i><sup><i>k</i></sup>
 * /
 * <i>k</i>!
 * &emsp; (<i>k</i> &ge; 0)
 * </li>
 * 
 * <li>P(<i>k</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public interface PoissonRnd extends IntegerRandomGenerator {

    /**
     * <p>
     * このインスタンスが扱うパラメータ <i>&lambda;</i> の値を返す.
     * </p>
     *
     * @return パラメータ <i>&lambda;</i>
     */
    public abstract double lambda();

    /**
     * <p>
     * 指定したパラメータのPoisson分布乱数発生器インスタンスを返す.
     * </p>
     * 
     * <p>
     * 扱えるパラメータ <i>&lambda;</i> は, <br>
     * {@code 0.0 <= lambda <= 1.0E6} <br>
     * である.
     * </p>
     *
     * @param lambda パラメータ
     * @return パラメータが <i>&lambda;</i> のPoisson分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static PoissonRnd instanceOf(double lambda) {
        return PoissonRndFactory.instanceOf(lambda);
    }
}
