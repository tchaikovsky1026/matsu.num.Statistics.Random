/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.cat;

import org.junit.Ignore;

import matsu.num.statistics.random.CategoricalRnd;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.CategoricalRnd.Factory}.
 * 
 * @deprecated このクラスは使用されていない.
 */
@Ignore
@Deprecated
public final class CategoricalFactoryForTesting {

    public static final CategoricalRnd.Factory FACTORY =
            new TableBasedCategoricalRndFactory(ExponentiationForTesting.INSTANCE);

    private CategoricalFactoryForTesting() {
    }
}
