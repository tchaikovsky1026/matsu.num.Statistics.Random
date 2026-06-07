/*
 * Copyright © 2026 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.inner;

import org.junit.Ignore;

import matsu.num.statistics.random.lib.ExponentiationForTesting;
import matsu.num.statistics.random.staticgamma.StaticGammaFactoryForTesting;

/**
 * テストクラスで使用する
 * {@link matsu.num.statistics.random.inner.InnerStaticPoissonRnd.Factory}.
 */
@Ignore
public final class InnerStaticPoissonFactoryForTesting {

    public static final InnerStaticPoissonRnd.Factory FACTORY =
            GammaDirichletBasedStaticPoissonRnd.createFactory(
                    ExponentiationForTesting.INSTANCE,
                    StaticGammaFactoryForTesting.FACTORY,
                    InnerStaticBinomialFactoryForTesting.FACTORY);

    private InnerStaticPoissonFactoryForTesting() {
    }
}
