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

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.VoigtRnd.Factory}.
 * 
 * @deprecated このクラスは使用されていない.
 */
@Ignore
@Deprecated
public final class VoigtFactoryForTesting {

    public static final VoigtRnd.Factory FACTORY =
            StandardImplVoigtRnd.createFactory(
                    NormalFactoryForTesting.FACTORY, CauchyFactoryForTesting.FACTORY);

    private VoigtFactoryForTesting() {
    }
}
