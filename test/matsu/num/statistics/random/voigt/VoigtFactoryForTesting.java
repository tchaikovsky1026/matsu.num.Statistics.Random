/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.voigt;

import org.junit.Ignore;

import matsu.num.statistics.random.VoigtRnd;
import matsu.num.statistics.random.cauchy.CauchyFactoryForTesting;
import matsu.num.statistics.random.norm.NormalFactoryForTesting;

@Ignore
public final class VoigtFactoryForTesting {

    public static final VoigtRnd.Factory FACTORY =
            new StandardImplVoigtRndFactory(
                    NormalFactoryForTesting.FACTORY, CauchyFactoryForTesting.FACTORY);

    private VoigtFactoryForTesting() {
    }
}
