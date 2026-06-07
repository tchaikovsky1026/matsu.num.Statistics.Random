/*
 * Copyright © 2026 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.inner;

import org.junit.Ignore;

import matsu.num.statistics.random.staticgamma.StaticGammaFactoryForTesting;

/**
 * テストクラスで使用する
 * {@link matsu.num.statistics.random.inner.InnerStaticBinomialRnd.Factory}.
 */
@Ignore
public final class InnerStaticBinomialFactoryForTesting {

    public static final InnerStaticBinomialRnd.Factory FACTORY =
            DirichletBasedStaticBinomialRnd.createFactory(StaticGammaFactoryForTesting.FACTORY);

    private InnerStaticBinomialFactoryForTesting() {
    }
}
