/*
 * 2023.3.22
 */
package matsu.num.statistics.random;

import java.util.Random;

import matsu.num.statistics.random.beta.gamma.ByGaBeRnd;
import matsu.num.statistics.random.beta.gamma.ByGaStaticBeRnd;

/**
 * ベータ分布に従う乱数発生器を扱う. <br>
 * ベータ分布の確率密度関数 P(<i>x</i>) は,  <br>
 * P(<i>x</i>) &prop;
 * <ul>
 * <li><i>x</i><sup><i>a</i> - 1</sup>
 * (1 - <i>x</i>)<sup><i>b</i> - 1</sup> (0 &le; <i>x</i> &le; 1)</li>
 * <li>0 (otherwise)</li>
 * </ul>
 * である.
 * <i>a</i>,<i>b</i>は形状パラメータ. <br>
 * 扱える<i>a</i>,<i>b</i> は,
 * {@code 1.0E-2 <= (a,b) <= 1.0E+14} である.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public interface BetaRnd extends FloatingRandomGenerator {

    /**
     * このインスタンスが扱う形状パラメータaの値を返す.
     *
     * @return 形状パラメータa
     */
    public abstract double shapeA();

    /**
     * このインスタンスが扱う形状パラメータbの値を返す.
     *
     * @return 形状パラメータb
     */
    public abstract double shapeB();

    /**
     * 与えられた基本乱数発生器を用いて, ベータプライム分布に従う乱数を生成する. <br>
     * ベータプライム分布の確率密度関数 P(<i>x</i>) は,  <br>
     * P(<i>x</i>) &prop;
     * <ul>
     * <li><i>x</i><sup><i>a</i> - 1</sup>/ (1 + <i>x</i>)<sup><i>a</i> +
     * <i>b</i></sup> (<i>x</i> &ge; 0)</li>
     * <li>0 (otherwise)</li>
     * </ul>
     * である.
     *
     * @param random 基本乱数発生器
     * @return ベータプライム分布に従う乱数の値
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public abstract double nextBetaPrime(Random random);

    /**
     * 指定した形状パラメータのベータ分布乱数発生器インスタンスを返す.
     *
     * @param a 形状パラメータa
     * @param b 形状パラメータb
     * @return 形状パラメータが(a,b)のベータ分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static BetaRnd instanceOf(double a, double b) {
        return ByGaBeRnd.instanceOf(a, b);
    }

    /**
     * 形状パラメータを与えて, ベータ分布に従う乱数を発生させる.
     *
     * @param random 基本乱数発生器
     * @param a 形状パラメータa
     * @param b 形状パラメータb
     * @return 形状パラメータが(a,b)のベータ分布に従う乱数の値
     * @throws IllegalArgumentException パラメータが範囲外の場合
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static double nextRandom(Random random, double a, double b) {
        return ByGaStaticBeRnd.nextRandom(random, a, b);
    }

    /**
     * 形状パラメータを与えて, ベータプライム分布に従う乱数を発生させる.
     *
     * @param random 基本乱数発生器
     * @param a 形状パラメータa
     * @param b 形状パラメータb
     * @return 形状パラメータが(a,b)のベータプライム分布に従う乱数の値
     * @throws IllegalArgumentException パラメータが範囲外の場合
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static double nextBetaPrime(Random random, double a, double b) {
        return ByGaStaticBeRnd.nextBetaPrime(random, a, b);
    }
}
