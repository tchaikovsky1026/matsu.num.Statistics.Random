/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.staticbeta;

import java.util.function.BiFunction;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.StaticGammaRnd;

/**
 * Marsaglia-Tsangの方法に基づく, ガンマ乱数のstaticな生成器. <br>
 * 扱える形状パラメータa,bは, {@code 1.0E-2 <= a <= 1.0E+14 && 1.0E-2 <= b <= 1.0E+14} である.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
final class GammaBasedStaticBetaRnd implements StaticBetaRnd {

    private final StaticGammaRnd staticGammaRnd;

    private static final BiFunction<Double, Double, IllegalArgumentException> exceptionGetter =
            (a, b) -> new IllegalArgumentException(String.format("パラメータ不正:a=%s, b=%s", a, b));

    GammaBasedStaticBetaRnd(StaticGammaRnd.Factory staticGammaRndFactory) {
        super();
        this.staticGammaRnd = staticGammaRndFactory.instance();
    }

    @Override
    public boolean acceptsParameters(double a, double b) {
        return LOWER_LIMIT_SHAPE_PARAMETER <= a && a <= UPPER_LIMIT_SHAPE_PARAMETER
                && LOWER_LIMIT_SHAPE_PARAMETER <= b && b <= UPPER_LIMIT_SHAPE_PARAMETER;
    }

    @Override
    public double nextRandom(BaseRandom random, double a, double b) {
        if (!this.acceptsParameters(a, b)) {
            throw exceptionGetter.apply(a, b);
        }

        double u1 = this.staticGammaRnd.nextRandom(random, a);
        double u2 = this.staticGammaRnd.nextRandom(random, b);
        double out = u1 / (u1 + u2);
        return Double.isFinite(out) ? out : 0;
    }

    @Override
    public double nextBetaPrime(BaseRandom random, double a, double b) {
        if (!this.acceptsParameters(a, b)) {
            throw exceptionGetter.apply(a, b);
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
