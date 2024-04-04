/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.geo;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GeometricRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * 逆関数法に基づく, 幾何分布に従う乱数発生器を扱う.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
final class InversionBasedGeoRnd implements GeometricRnd {

    private final double coeff;
    private final double p;

    private final ExponentialRnd expRnd;

    InversionBasedGeoRnd(double p, Exponentiation exponentiation, ExponentialRnd.Factory exponentialRndFactory) {
        this.p = p;
        this.coeff = -1 / exponentiation.log1p(-p);
        this.expRnd = exponentialRndFactory.instance();
    }

    @Override
    public final double successPobability() {
        return p;
    }

    @Override
    public int nextRandom(BaseRandom random) {
        while (true) {
            double out = coeff * this.expRnd.nextRandom(random);
            if (out < Integer.MAX_VALUE) {
                return 1 + (int) out;
            }
        }
    }

    @Override
    public String toString() {
        return String.format(
                "GeometricRnd(%s)", this.successPobability());
    }
}
