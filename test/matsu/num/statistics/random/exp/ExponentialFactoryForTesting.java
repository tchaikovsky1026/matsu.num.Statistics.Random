/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.exp;

import org.junit.Ignore;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

@Ignore
public final class ExponentialFactoryForTesting {

    public static final ExponentialRnd.Factory FACTORY =
            ZiggExponentialRnd.createFactory(ExponentiationForTesting.INSTANCE);

    private ExponentialFactoryForTesting() {
    }
}
