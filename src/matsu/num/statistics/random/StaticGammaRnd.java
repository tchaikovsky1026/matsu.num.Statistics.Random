/**
 * 2024.1.9
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.gamma.StaticGammaRndFactory;

/**
 * <p>
 * 乱数生成のたびにパラメータを指定する標準ガンマ分布乱数発生器
 * (Staticガンマ乱数発生器)
 * を扱う.
 * </p>
 * 
 * <p>
 * 標準ガンマ分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>k</i> は形状パラメータ).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup><i>k</i> - 1</sup> exp(-<i>x</i>)
 * &emsp; (<i>x</i> &ge; 0)
 * </li>
 * 
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * <p>
 * 扱うことができる形状パラメータは, <br>
 * {@code 1.0E-2 <= k <= 1.0E+28} <br>
 * である.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public interface StaticGammaRnd {

    /**
     * <p>
     * 形状パラメータを与えて, 標準ガンマ分布に従う乱数を発生させる.
     * </p>
     *
     * @param random 基本乱数発生器
     * @param k 形状パラメータ
     * @return 形状パラメータが <i>k</i> の標準ガンマ分布に従う乱数の値
     * @throws IllegalArgumentException パラメータが範囲外の場合
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public abstract double nextRandom(Random random, double k);

    /**
     * <p>
     * Staticガンマ乱数発生器インスタンスを返す.
     * </p>
     *
     * @return Staticガンマ乱数発生器インスタンス
     */
    public static StaticGammaRnd instance() {
        return StaticGammaRndFactory.instance();
    }
}
