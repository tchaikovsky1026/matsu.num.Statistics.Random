/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.logi;

import org.junit.Ignore;

import matsu.num.statistics.random.LogisticRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

@Ignore
public final class LogisticFactoryForTesting {

    public static final LogisticRnd.Factory FACTORY =
            ZiggLogiRnd.createFactory(
                    ExponentiationForTesting.INSTANCE, ExponentialFactoryForTesting.FACTORY);

    private LogisticFactoryForTesting() {
    }
}
