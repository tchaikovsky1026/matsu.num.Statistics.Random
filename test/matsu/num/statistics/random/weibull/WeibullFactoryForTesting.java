/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.weibull;

import org.junit.Ignore;

import matsu.num.statistics.random.WeibullRnd;
import matsu.num.statistics.random.gumbel.GumbelFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.WeibullRnd.Factory}.
 * 
 * @deprecated このクラスは使用されていない.
 */
@Ignore
@Deprecated
public final class WeibullFactoryForTesting {

    public static final WeibullRnd.Factory FACTORY =
            GumbelBasedWeibullRnd.createFactory(
                    ExponentiationForTesting.INSTANCE, GumbelFactoryForTesting.FACTORY);

    private WeibullFactoryForTesting() {
    }
}
