/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

package matsu.num.statistics.random.binomial;

import org.junit.Ignore;

import matsu.num.statistics.random.BinomialRnd;

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.BinomialRnd.Factory}.
 * 
 * @deprecated このクラスは使用されていない.
 */
@Deprecated
@Ignore
public final class BinomialRndFactoryForTesting {

    public static final BinomialRnd.Factory FACTORY =
            BernoulliTrialBinomialRnd.factory();

    private BinomialRndFactoryForTesting() {
    }
}
