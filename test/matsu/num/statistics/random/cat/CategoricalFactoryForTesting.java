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
 */
@Ignore
public final class CategoricalFactoryForTesting {

    public static final CategoricalRnd.Factory FACTORY =
            TableBasedCategoricalRnd.createFactory(ExponentiationForTesting.INSTANCE);

    private CategoricalFactoryForTesting() {
    }
}
