/*
 * 2023.3.22
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.weibull.gumbel.ByGumWeiRnd;

/**
 * 標準Weibull分布に従う乱数発生器を扱う. <br>
 * 標準Weibull分布の確率密度関数 P(<i>x</i>) は,  <br>
 * P(<i>x</i>) &prop;
 * <ul>
 * <li><i>x</i><sup><i>k</i> - 1</sup> exp(-<i>x</i><sup><i>k</i></sup>) (<i>x</i> &ge; 0)</li>
 * <li>0 (otherwise)</li>
 * </ul>
 * である.
 * <i>k</i>は形状パラメータ. <br>
 * 扱える <i>k</i> は, {@code 1.0E-2 <= k <= 1.0E+14} である.
 * 
 * @author Matsuura Y.
 * @version 11.0
 */
public interface WeibullRnd extends FloatingRandomGenerator {

    /**
     * このインスタンスが扱う形状パラメータの値を返す.
     *
     * @return 形状パラメータ
     */
    public abstract double shapeParameter();

    /**
     * 指定した形状パラメータのWeibull分布乱数発生器インスタンスを返す.
     *
     * @param k 形状パラメータ
     * @return 形状パラメータがkのWeibull分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static WeibullRnd instanceOf(double k) {
        return ByGumWeiRnd.instanceOf(k);
    }
}
