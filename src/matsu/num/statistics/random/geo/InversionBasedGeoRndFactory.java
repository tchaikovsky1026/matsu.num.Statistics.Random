/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
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
 * @version 22.2
 */
public final class InversionBasedGeoRndFactory extends SkeletalGeometricRndFactory {

    private final Exponentiation exponentiation;
    private final ExponentialRnd.Factory exponentialRndFactory;

    public InversionBasedGeoRndFactory(
            Exponentiation exponentiation, ExponentialRnd.Factory exponentialRndFactory) {
        this.exponentiation = Objects.requireNonNull(exponentiation);
        this.exponentialRndFactory = Objects.requireNonNull(exponentialRndFactory);
    }

    @Override
    GeometricRnd createInstanceOf(double p) {
        return new InversionBasedGeoRnd(p, this.exponentiation, this.exponentialRndFactory);
    }
}
