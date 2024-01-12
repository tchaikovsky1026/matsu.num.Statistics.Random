/*
 * 2024.1.8
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.gamma.GammaRndFactory;
import matsu.num.statistics.random.gamma.StaticGammaRndFactory;

/**
 * <p>
 * 標準ガンマ分布に従う乱数発生器を扱う.
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
 * @author Matsuura Y.
 * @version 17.4
 */
public interface GammaRnd extends FloatingRandomGenerator {

    /**
     * <p>
     * このインスタンスが扱う形状パラメータの値を返す.
     * </p>
     *
     * @return 形状パラメータ
     */
    public abstract double shapeParameter();

    /**
     * <p>
     * 指定した形状パラメータのガンマ分布乱数発生器インスタンスを返す.
     * </p>
     * 
     * <p>
     * 扱える形状パラメータ <i>k</i> は, <br>
     * {@code 1.0E-2 <= k <= 1.0E+28} <br>
     * である.
     * </p>
     *
     * @param k 形状パラメータ
     * @return 形状パラメータが <i>k</i> のガンマ分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static GammaRnd instanceOf(double k) {
        return GammaRndFactory.instanceOf(k);
    }

    /**
     * <p>
     * 形状パラメータを与えて, 標準ガンマ分布に従う乱数を発生させる.
     * </p>
     * 
     * <p>
     * 扱える形状パラメータ <i>k</i> は, <br>
     * {@code 1.0E-2 <= k <= 1.0E+28} <br>
     * である.
     * </p>
     *
     * @param random 基本乱数発生器
     * @param k 形状パラメータ
     * @return 形状パラメータが <i>k</i> の標準ガンマ分布に従う乱数の値
     * @throws IllegalArgumentException パラメータが範囲外の場合
     * @throws NullPointerException 引数にnullが含まれる場合
     * @deprecated 代替インターフェースあり: {@linkplain StaticGammaRnd},
     *                 このstaticメソッドでなく, インターフェース経由でアクセスすることを推奨する.
     */
    @Deprecated
    public static double nextRandom(Random random, double k) {
        return StaticGammaRndFactory.instance().nextRandom(random, k);
    }
}
