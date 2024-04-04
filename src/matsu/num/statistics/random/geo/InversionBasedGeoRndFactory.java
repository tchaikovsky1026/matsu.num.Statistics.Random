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

import java.util.Objects;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GeometricRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * 逆関数法に基づく, 幾何分布に従う乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
public final class InversionBasedGeoRndFactory implements GeometricRnd.Factory {

    private final Exponentiation exponentiation;
    private final ExponentialRnd.Factory exponentialRndFactory;

    public InversionBasedGeoRndFactory(Exponentiation exponentiation, ExponentialRnd.Factory exponentialRndFactory) {
        this.exponentiation = Objects.requireNonNull(exponentiation);
        this.exponentialRndFactory = Objects.requireNonNull(exponentialRndFactory);
    }

    @Override
    public boolean acceptsParameter(double p) {
        return GeometricRnd.LOWER_LIMIT_SUCCESS_PROBABILITY <= p
                && p <= GeometricRnd.UPPER_LIMIT_SUCCESS_PROBABILITY;
    }

    @Override
    public GeometricRnd instanceOf(double p) {
        if (!this.acceptsParameter(p)) {
            throw new IllegalArgumentException(String.format("パラメータ不正:p=%s", p));
        }
        return new InversionBasedGeoRnd(p, this.exponentiation, this.exponentialRndFactory);
    }

    @Override
    public String toString() {
        return "GeometricRnd.Factory";
    }

}
