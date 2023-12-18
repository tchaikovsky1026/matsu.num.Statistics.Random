/*
 * 2023.3.22
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.poi.gamma.ByGaHomoProcessBasedPoiRnd;

/**
 * Poisson分布に従う乱数発生器を扱う. <br>
 * Poisson分布の確率関数 P(<i>k</i>) は, <br>
 * P(<i>k</i>) =
 * <ul>
 * <li><i>e</i><sup>-<i>&lambda;</i></sup><i>&lambda;</i><sup><i>k</i></sup>/<i>k</i>!
 * (<i>k</i> &ge; 0)</li>
 * <li>0 (otherwise)</li>
 * </ul>
 * である. <br>
 * <i>&lambda;</i>はパラメータ. <br>
 * 扱える <i>&lambda;</i> は, {@code 0.0 <= lambda <= 1.0E6} である.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public interface PoissonRnd extends IntegerRandomGenerator {

    /**
     * このインスタンスが扱うパラメータ<i>&lambda;</i>の値を返す.
     *
     * @return パラメータ&lambda;
     */
    public abstract double lambda();

    /**
     * 指定したパラメータのPoisson分布乱数発生器インスタンスを返す. 
     *
     * @param lambda パラメータ&lambda;
     * @return パラメータがlambdaのPoisson分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static PoissonRnd instanceOf(double lambda) {
        return ByGaHomoProcessBasedPoiRnd.instanceOf(lambda);
    }
}
