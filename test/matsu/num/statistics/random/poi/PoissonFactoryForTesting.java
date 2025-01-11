/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.poi;

import org.junit.Ignore;

import matsu.num.statistics.random.PoissonRnd;
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

@Ignore
public final class PoissonFactoryForTesting {

    public static final PoissonRnd.Factory FACTORY =
            new GammaHomoProcessBasedPoissonRndFactory(
                    ExponentiationForTesting.INSTANCE, GammaFactoryForTesting.FACTORY);

    private PoissonFactoryForTesting() {
    }
}
