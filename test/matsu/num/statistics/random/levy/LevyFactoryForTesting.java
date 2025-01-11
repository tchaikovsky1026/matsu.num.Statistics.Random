/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.levy;

import org.junit.Ignore;

import matsu.num.statistics.random.LevyRnd;
import matsu.num.statistics.random.norm.NormalFactoryForTesting;

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.LevyRnd.Factory}.
 */
@Ignore
public final class LevyFactoryForTesting {

    public static final LevyRnd.Factory FACTORY =
            NormalBasedLevyRnd.createFactory(NormalFactoryForTesting.FACTORY);

    private LevyFactoryForTesting() {
    }
}
