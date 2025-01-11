/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.staticbeta;

import org.junit.Ignore;

import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.staticgamma.StaticGammaFactoryForTesting;

@Ignore
public final class StaticBetaFactoryForTesting {

    public static final StaticBetaRnd.Factory FACTORY =
            GammaBasedStaticBetaRnd.createFactory(StaticGammaFactoryForTesting.FACTORY);

    private StaticBetaFactoryForTesting() {
    }
}
