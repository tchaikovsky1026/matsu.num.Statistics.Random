/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.staticgamma;

import org.junit.Ignore;

import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.StaticGammaRnd.Factory}.
 */
@Ignore
public final class StaticGammaFactoryForTesting {

    public static final StaticGammaRnd.Factory FACTORY =
            MTTypeStaticGammaRnd.createFactory(
                    ExponentiationForTesting.INSTANCE);

    private StaticGammaFactoryForTesting() {
    }
}
