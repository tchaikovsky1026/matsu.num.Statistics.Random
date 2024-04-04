/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.staticgamma;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Marsaglia-Tsangの方法に基づく, ガンマ乱数のstaticな生成器. <br>
 * 扱える形状パラメータkは, {@code 1.0E-2 <= k <= 1.0E+28} である.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
final class MTTypeStaticGammaRnd implements StaticGammaRnd {

    private final ExponentialRnd expRnd;
    private final NormalRnd normalRnd;

    private final Exponentiation exponentiation;

    MTTypeStaticGammaRnd(Exponentiation exponentiation, ExponentialRnd.Factory exponentialRndFactory,
            NormalRnd.Factory normalRndFactory) {
        super();
        this.exponentiation = Objects.requireNonNull(exponentiation);
        this.expRnd = exponentialRndFactory.instance();
        this.normalRnd = normalRndFactory.instance();
    }

    @Override
    public boolean acceptsParameter(double k) {
        return LOWER_LIMIT_SHAPE_PARAMETER <= k && k <= UPPER_LIMIT_SHAPE_PARAMETER;
    }

    @Override
    public double nextRandom(BaseRandom random, double k) {
        if (!this.acceptsParameter(k)) {
            throw new IllegalArgumentException(String.format("パラメータ不正:k=%s", k));
        }

        if (k == 1) {
            return this.expRnd.nextRandom(random);
        }
        if (k > 1) {
            double d = k - (1.0 / 3.0);
            double c = (1.0 / 3.0) / exponentiation.sqrt(d);
            return nextGammaOver1(random, d, c);
        }

        //k<1
        double d = k + (2.0 / 3.0);
        double c = (1.0 / 3.0) / exponentiation.sqrt(d);
        return nextGammaOver1(random, d, c) * exponentiation.exp(-this.expRnd.nextRandom(random) / k);
    }

    private double nextGammaOver1(BaseRandom random, double d, double c) {
        while (true) {
            double z = this.normalRnd.nextRandom(random);
            double v = 1 + c * z;
            double w = v * v * v;
            double y = d * w;
            if (y < 0.0) {
                continue;
            }
            double u = random.nextDouble();
            double z2 = z * z;
            if (u > z2 * z2 * 0.0331 || z2 * 0.5 + d * exponentiation.log(w) + d - y > exponentiation.log(1 - u)) {
                return y;
            }
        }
    }

    @Override
    public String toString() {
        return "StaticGammaRnd";
    }
}
