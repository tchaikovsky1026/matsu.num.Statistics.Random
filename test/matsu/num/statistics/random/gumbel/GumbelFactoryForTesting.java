/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.gumbel;

import org.junit.Ignore;

import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.GumbelRnd.Factory}.
 */
@Ignore
public final class GumbelFactoryForTesting {

    public static final GumbelRnd.Factory FACTORY =
            UniZiggGumbelRnd.createFactory(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY);

    private GumbelFactoryForTesting() {
    }
}
