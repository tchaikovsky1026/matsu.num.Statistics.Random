/**
 * 2024.1.9
 */
package matsu.num.statistics.random.beta;

import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.gamma.StaticGammaRndFactory;

/**
 * Marsaglia-Tsangの方法に基づく, ガンマ乱数のstaticな生成器. <br>
 * 扱える形状パラメータa,bは, {@code 1.0E-2 <= a <= 1.0E+14 && 1.0E-2 <= b <= 1.0E+14} である.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
final class GammaBasedStaticBetaRnd implements StaticBetaRnd {

    /**
     * 形状パラメータの下限.
     */
    private static final double LOWER_LIMIT_OF_SHAPE_PARAMETER = 1E-2;

    /**
     * 形状パラメータの上限.
     */
    private static final double UPPER_LIMIT_OF_SHAPE_PARAMETER = 1E14;

    private final StaticGammaRnd staticGammaRnd = StaticGammaRndFactory.instance();

    GammaBasedStaticBetaRnd() {
        super();
    }

    @Override
    public double nextRandom(Random random, double a, double b) {
        if (!(LOWER_LIMIT_OF_SHAPE_PARAMETER <= a && a <= UPPER_LIMIT_OF_SHAPE_PARAMETER
                && LOWER_LIMIT_OF_SHAPE_PARAMETER <= b && b <= UPPER_LIMIT_OF_SHAPE_PARAMETER)) {
            throw new IllegalArgumentException(
                    String.format(
                            "パラメータ不正:a = %s, b = %s", a, b));
        }

        double u1 = this.staticGammaRnd.nextRandom(random, a);
        double u2 = this.staticGammaRnd.nextRandom(random, b);
        double out = u1 / (u1 + u2);
        return Double.isFinite(out) ? out : 0;
    }

    @Override
    public double nextBetaPrime(Random random, double a, double b) {
        if (!(LOWER_LIMIT_OF_SHAPE_PARAMETER <= a && a <= UPPER_LIMIT_OF_SHAPE_PARAMETER
                && LOWER_LIMIT_OF_SHAPE_PARAMETER <= b && b <= UPPER_LIMIT_OF_SHAPE_PARAMETER)) {
            throw new IllegalArgumentException(
                    String.format(
                            "パラメータ不正:a = %s, b = %s", a, b));
        }

        double u1 = this.staticGammaRnd.nextRandom(random, a);
        double u2 = this.staticGammaRnd.nextRandom(random, b);
        double out = u1 / u2;
        return Double.isFinite(out) ? out : 0;
    }

    @Override
    public String toString() {
        return "StaticBetaRnd";
    }
}
