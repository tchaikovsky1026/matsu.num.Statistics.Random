/*
 * 2024.1.8
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.beta.BetaRndFactory;
import matsu.num.statistics.random.beta.StaticBetaRndFactory;

/**
 * <p>
 * ベータ分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * ベータ分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>a</i>, <i>b</i> は形状パラメータ).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup><i>a</i> - 1</sup>
 * (1 - <i>x</i>)<sup><i>b</i> - 1</sup>
 * &emsp; (0 &le; <i>x</i> &le; 1)
 * </li>
 * 
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * <p>
 * このインターフェースではベータ分布に加えて, ベータプライム分布に従う乱数生成も扱う. <br>
 * ベータプライム分布の確率密度関数 P(<i>x</i>) は次のとおりである.
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup><i>a</i> - 1</sup>
 * /
 * (1 + <i>x</i>)<sup><i>a</i> + <i>b</i></sup>
 * &emsp; (<i>x</i> &ge; 0)
 * </li>
 * 
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 *
 * @author Matsuura Y.
 * @version 17.4
 */
public interface BetaRnd extends FloatingRandomGenerator {

    /**
     * <p>
     * このインスタンスが扱う形状パラメータ <i>a</i> の値を返す.
     * </p>
     *
     * @return 形状パラメータ <i>a</i>
     */
    public abstract double shapeA();

    /**
     * <p>
     * このインスタンスが扱う形状パラメータ <i>b</i> の値を返す.
     * </p>
     *
     * @return 形状パラメータ <i>b</i>
     */
    public abstract double shapeB();

    /**
     * <p>
     * 与えられた基本乱数発生器を用いて, ベータプライム分布に従う乱数を発生させる.
     * </p>
     * 
     * @param random 基本乱数発生器
     * @return ベータプライム分布に従う乱数の値
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public abstract double nextBetaPrime(Random random);

    /**
     * <p>
     * 指定した形状パラメータのベータ分布乱数発生器インスタンスを返す.
     * </p>
     * 
     * <p>
     * 扱える形状パラメータ <i>a</i>, <i>b</i> は, <br>
     * {@code 1.0E-2 <= (a, b) <= 1.0E+14} <br>
     * である.
     * </p>
     *
     * @param a 形状パラメータ
     * @param b 形状パラメータ
     * @return 形状パラメータが (<i>a</i>, <i>b</i>) のベータ分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static BetaRnd instanceOf(double a, double b) {
        return BetaRndFactory.instanceOf(a, b);
    }

    /**
     * <p>
     * 形状パラメータを与えて, ベータ分布に従う乱数を発生させる.
     * </p>
     * 
     * <p>
     * 扱える形状パラメータ <i>a</i>, <i>b</i> は, <br>
     * {@code 1.0E-2 <= (a, b) <= 1.0E+14} <br>
     * である.
     * </p>
     *
     * @param random 基本乱数発生器
     * @param a 形状パラメータ
     * @param b 形状パラメータ
     * @return 形状パラメータが (<i>a</i>, <i>b</i>) のベータ分布に従う乱数の値
     * @throws IllegalArgumentException パラメータが範囲外の場合
     * @throws NullPointerException 引数にnullが含まれる場合
     * @deprecated 代替インターフェースあり: {@linkplain StaticBetaRnd},
     *                 このstaticメソッドでなく, インターフェース経由でアクセスすることを推奨する.
     */
    @Deprecated
    public static double nextRandom(Random random, double a, double b) {
        return StaticBetaRndFactory.instance().nextRandom(random, a, b);
    }

    /**
     * <p>
     * 形状パラメータを与えて, ベータプライム分布に従う乱数を発生させる.
     * </p>
     * 
     * <p>
     * 扱える形状パラメータ <i>a</i>, <i>b</i> は, <br>
     * {@code 1.0E-2 <= (a, b) <= 1.0E+14} <br>
     * である.
     * </p>
     *
     * @param random 基本乱数発生器
     * @param a 形状パラメータ
     * @param b 形状パラメータ
     * @return 形状パラメータが (<i>a</i>, <i>b</i>) のベータプライム分布に従う乱数の値
     * @throws IllegalArgumentException パラメータが範囲外の場合
     * @throws NullPointerException 引数にnullが含まれる場合
     * @deprecated 代替インターフェースあり: {@linkplain StaticBetaRnd},
     *                 このstaticメソッドでなく, インターフェース経由でアクセスすることを推奨する.
     */
    @Deprecated
    public static double nextBetaPrime(Random random, double a, double b) {
        return StaticBetaRndFactory.instance().nextBetaPrime(random, a, b);
    }
}
