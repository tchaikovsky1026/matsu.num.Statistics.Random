/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.cauchy;

import org.junit.Ignore;

import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.CauchyRnd.Factory}.
 */
@Ignore
public final class CauchyFactoryForTesting {
    public static final CauchyRnd.Factory FACTORY =
            ZiggCauchyRnd.createFactory(ExponentiationForTesting.INSTANCE);

    private CauchyFactoryForTesting() {
    }
}
