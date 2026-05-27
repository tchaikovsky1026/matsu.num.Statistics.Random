/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.gamma;

import org.junit.Ignore;

import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.GammaRnd.Factory}.
 */
@Ignore
public final class GammaFactoryForTesting {

    public static final GammaRnd.Factory FACTORY =
            MTTypeGammaRndFactory.create(ExponentiationForTesting.INSTANCE);

    private GammaFactoryForTesting() {
    }
}
