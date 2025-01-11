/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.beta;

import org.junit.Ignore;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;

@Ignore
public final class BetaFactoryForTesting {

    public static final BetaRnd.Factory FACTORY =
            new GammaBasedBetaRndFactory(GammaFactoryForTesting.FACTORY);

    private BetaFactoryForTesting() {
    }

}
