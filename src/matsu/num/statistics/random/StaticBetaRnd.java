/**
 * 2024.1.9
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.beta.StaticBetaRndFactory;

/**
 * <p>
 * 乱数生成のたびにパラメータを指定するベータ分布乱数発生器
 * (Staticベータ乱数発生器)
 * を扱う.
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
 * <p>
 * 扱うことができる形状パラメータは, <br>
 * {@code 1.0E-2 <= (a, b) <= 1.0E+14}
 * である.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public interface StaticBetaRnd {

    /**
     * <p>
     * 形状パラメータを与えて, ベータ分布に従う乱数を発生させる.
     * </p>
     *
     * @param random 基本乱数発生器
     * @param a 形状パラメータ
     * @param b 形状パラメータ
     * @return 形状パラメータが (<i>a</i>, <i>b</i>) のベータ分布に従う乱数の値
     * @throws IllegalArgumentException パラメータが範囲外の場合
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public abstract double nextRandom(Random random, double a, double b);

    /**
     * <p>
     * 形状パラメータを与えて, ベータプライム分布に従う乱数を発生させる.
     * </p>
     *
     * @param random 基本乱数発生器
     * @param a 形状パラメータ
     * @param b 形状パラメータ
     * @return 形状パラメータが (<i>a</i>, <i>b</i>) のベータプライム分布に従う乱数の値
     * @throws IllegalArgumentException パラメータが範囲外の場合
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public abstract double nextBetaPrime(Random random, double a, double b);

    /**
     * <p>
     * Staticベータ乱数発生器インスタンスを返す.
     * </p>
     *
     * @return Staticベータ乱数発生器インスタンス
     */
    public static StaticBetaRnd instance() {
        return StaticBetaRndFactory.instance();
    }

}
