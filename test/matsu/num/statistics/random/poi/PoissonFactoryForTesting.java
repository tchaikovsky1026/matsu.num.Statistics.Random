/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.poi;

import org.junit.Ignore;

import matsu.num.statistics.random.PoissonRnd;
import matsu.num.statistics.random.lib.ExponentiationForTesting;
import matsu.num.statistics.random.staticgamma.StaticGammaFactoryForTesting;

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.PoissonRnd.Factory}.
 * 
 * @deprecated このクラスは使用されていない.
 */
@Ignore
@Deprecated
public final class PoissonFactoryForTesting {

    public static final PoissonRnd.Factory FACTORY =
            GammaDirichletBasedPoissonRnd.createFactory(
                    ExponentiationForTesting.INSTANCE, StaticGammaFactoryForTesting.FACTORY);

    private PoissonFactoryForTesting() {
    }
}
