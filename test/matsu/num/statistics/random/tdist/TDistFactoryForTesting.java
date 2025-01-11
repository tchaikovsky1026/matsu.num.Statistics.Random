/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.tdist;

import org.junit.Ignore;

import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;
import matsu.num.statistics.random.norm.NormalFactoryForTesting;

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.TDistributionRnd.Factory}.
 */
@Ignore
public final class TDistFactoryForTesting {

    public static final TDistributionRnd.Factory FACTORY =
            new NormalGammaBasedTDistRndFactory(
                    ExponentiationForTesting.INSTANCE,
                    NormalFactoryForTesting.FACTORY,
                    GammaFactoryForTesting.FACTORY);

    private TDistFactoryForTesting() {
    }
}
