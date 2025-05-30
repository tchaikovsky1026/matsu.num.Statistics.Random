/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.fdist;

import org.junit.Ignore;

import matsu.num.statistics.random.FDistributionRnd;
import matsu.num.statistics.random.beta.BetaFactoryForTesting;

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.FDistributionRnd.Factory}.
 * 
 * @deprecated このクラスは使用されていない.
 */
@Ignore
@Deprecated
public final class FDistFactoryForTesting {

    public static final FDistributionRnd.Factory FACTORY =
            BetaBasedFDistributionRnd.createFactory(BetaFactoryForTesting.FACTORY);

    private FDistFactoryForTesting() {
    }
}
