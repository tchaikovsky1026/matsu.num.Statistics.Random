/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.9
 */
package matsu.num.statistics.random.zeta;

import org.junit.Ignore;

import matsu.num.statistics.random.ZetaRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.ZetaRnd.Factory}
 * 
 * @author Matsuura Y.
 */
@Ignore
public final class ZetaRndFactoryForTesting {

    public static final ZetaRnd.Factory FACTORY =
            RejectionSamplingZetaRnd.createFactory(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY);

    private ZetaRndFactoryForTesting() {
        //インスタンス化不可
        throw new AssertionError();
    }
}
