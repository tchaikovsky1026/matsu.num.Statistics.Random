/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.chisq;

import org.junit.Ignore;

import matsu.num.statistics.random.ChiSquaredRnd;
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.ChiSquaredRnd.Factory}.
 */
@Ignore
public final class ChiSqFactoryForTesting {

    public static final ChiSquaredRnd.Factory FACTORY =
            new GammaTypeChiSquaredRndFactory(GammaFactoryForTesting.FACTORY);

    private ChiSqFactoryForTesting() {
    }
}
