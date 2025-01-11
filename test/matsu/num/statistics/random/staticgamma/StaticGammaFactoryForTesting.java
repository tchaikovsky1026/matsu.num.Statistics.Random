/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.staticgamma;

import org.junit.Ignore;

import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;
import matsu.num.statistics.random.norm.NormalFactoryForTesting;

@Ignore
public final class StaticGammaFactoryForTesting {

    public static final StaticGammaRnd.Factory FACTORY =
            MTTypeStaticGammaRnd.createFactory(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY,
                    NormalFactoryForTesting.FACTORY);

    private StaticGammaFactoryForTesting() {
    }
}
