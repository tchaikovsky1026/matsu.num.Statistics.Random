/*
 * 2023.3.22
 */
package matsu.num.statistics.random;

import java.util.Random;

import matsu.num.statistics.random.gamma.mt.MarsagliaTsangGaRndFactory;
import matsu.num.statistics.random.gamma.mt.MarsagliaTsangStaticGaRnd;

/**
 * 標準ガンマ分布に従う乱数発生器を扱う. <br>
 * 標準ガンマ分布の確率密度関数 P(<i>x</i>) は,  <br>
 * P(<i>x</i>) &prop;
 * <ul>
 * <li><i>x</i><sup><i>k</i> - 1</sup> exp(-<i>x</i>) (<i>x</i> &ge; 0)</li>
 * <li>0 (otherwise)</li>
 * </ul>
 * である.
 * <i>k</i>は形状パラメータ. <br>
 * 扱える <i>k</i> は, {@code 1.0E-2 <= k <= 1.0E+28} である.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public interface GammaRnd extends FloatingRandomGenerator {

    /**
     * このインスタンスが扱う形状パラメータの値を返す.
     *
     * @return 形状パラメータ
     */
    public abstract double shapeParameter();

    /**
     * 指定した形状パラメータのガンマ分布乱数発生器インスタンスを返す.
     *
     * @param k 形状パラメータ
     * @return 形状パラメータがkのガンマ分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static GammaRnd instanceOf(double k) {
        return MarsagliaTsangGaRndFactory.instanceOf(k);
    }

    /**
     * 形状パラメータを与えて, 標準ガンマ分布に従う乱数を発生させる.
     *
     * @param random 基本乱数発生器
     * @param k 形状パラメータ
     * @return 形状パラメータがkの標準ガンマ分布に従う乱数の値
     * @throws IllegalArgumentException パラメータが範囲外の場合
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static double nextRandom(Random random, double k) {
        return MarsagliaTsangStaticGaRnd.nextRandom(random, k);
    }
}
