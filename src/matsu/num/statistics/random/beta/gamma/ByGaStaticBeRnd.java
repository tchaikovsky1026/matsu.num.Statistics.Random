/**
 * 2023.3.22
 */
package matsu.num.statistics.random.beta.gamma;

import java.util.Random;

import matsu.num.statistics.random.GammaRnd;

/**
 * Marsaglia-Tsangの方法に基づく, ガンマ乱数のstaticな生成器. <br> 
 * 扱える形状パラメータa,bは, {@code 1.0E-2 <= a <= 1.0E+14 && 1.0E-2 <= b <= 1.0E+14} である.
 * 
 * @author Matsuura Y.
 * @version 11.0
 */
public final class ByGaStaticBeRnd {

    /**
     * 形状パラメータの下限.
     */
    private static final double LOWER_LIMIT_OF_SHAPE_PARAMETER = 1E-2;

    /**
     * 形状パラメータの上限.
     */
    private static final double UPPER_LIMIT_OF_SHAPE_PARAMETER = 1E14;

    private ByGaStaticBeRnd() {
        throw new AssertionError();
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
        if (!(LOWER_LIMIT_OF_SHAPE_PARAMETER <= a && a <= UPPER_LIMIT_OF_SHAPE_PARAMETER
                && LOWER_LIMIT_OF_SHAPE_PARAMETER <= b && b <= UPPER_LIMIT_OF_SHAPE_PARAMETER)) {
            throw new IllegalArgumentException(String.format(
                    "パラメータ不正:a = %.16G, b = %.16G", a, b));
        }

        double u1 = GammaRnd.nextRandom(random, a);
        double u2 = GammaRnd.nextRandom(random, b);
        double out = u1 / (u1 + u2);
        return Double.isFinite(out) ? out : 0;
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
        if (!(LOWER_LIMIT_OF_SHAPE_PARAMETER <= a && a <= UPPER_LIMIT_OF_SHAPE_PARAMETER
                && LOWER_LIMIT_OF_SHAPE_PARAMETER <= b && b <= UPPER_LIMIT_OF_SHAPE_PARAMETER)) {
            throw new IllegalArgumentException(String.format(
                    "パラメータ不正:a = %.16G, b = %.16G", a, b));
        }

        double u1 = GammaRnd.nextRandom(random, a);
        double u2 = GammaRnd.nextRandom(random, b);
        double out = u1 / u2;
        return Double.isFinite(out) ? out : 0;
    }

}
