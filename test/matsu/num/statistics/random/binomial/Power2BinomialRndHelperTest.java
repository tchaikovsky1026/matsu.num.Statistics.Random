/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.binomial;

import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BinomialRnd;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;

/**
 * {@link NaiveBinomialRndHelper} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class Power2BinomialRndHelperTest {

    private static final BinomialRnd.Factory FACTORY = new Factory();

    @RunWith(Theories.class)
    public static class パラメータによる網羅的テスト {

        @DataPoints
        public static BinomialParameters[] parameters = {
                new BinomialParameters(15, 0.01),
                new BinomialParameters(15, 0.3),
                new BinomialParameters(15, 0.7),
                new BinomialParameters(15, 0.99),
                new BinomialParameters(31, 0.01),
                new BinomialParameters(31, 0.3),
                new BinomialParameters(31, 0.7),
                new BinomialParameters(31, 0.99),
                new BinomialParameters(63, 0.01),
                new BinomialParameters(63, 0.3),
                new BinomialParameters(63, 0.7),
                new BinomialParameters(63, 0.99),
                new BinomialParameters(127, 0.01),
                new BinomialParameters(127, 0.3),
                new BinomialParameters(127, 0.7),
                new BinomialParameters(127, 0.99),
                new BinomialParameters(255, 0.01),
                new BinomialParameters(255, 0.3),
                new BinomialParameters(255, 0.7),
                new BinomialParameters(255, 0.99)
        };

        @Theory
        public void test(BinomialParameters parameter) {
            IntegerRandomGeneratorTestingFramework framework =
                    IntegerRandomGeneratorTestingFramework.instanceOf(
                            new TestedBinomialRandomGenerator(parameter.createFrom(FACTORY)));
            framework.test();
        }
    }

    private static final class Factory extends SkeletalBinomialRnd.Factory {

        @Override
        BinomialRnd createInstanceOf(int n, double p) {
            return new BinomialImpl(n, p);
        }

        private static final class BinomialImpl extends SkeletalBinomialRnd {

            private static final GammaRnd[] GAMMA_RNDS;

            static {
                GAMMA_RNDS = new GammaRnd[25];

                int n = 1;
                for (int i = 0; i < GAMMA_RNDS.length; i++) {
                    GAMMA_RNDS[i] = GammaFactoryForTesting.FACTORY.instanceOf(n);
                    n *= 2;
                }
            }

            private final Power2BinomialRndHelper helper;

            BinomialImpl(int n, double p) {
                super(n, p);
                this.helper = new Power2BinomialRndHelper(
                        32,
                        new NaiveBinomialRndHelper(),
                        GAMMA_RNDS);
            }

            @Override
            public int nextRandom(BaseRandom random) {
                return this.helper.next(this.n, this.p, random);
            }
        }
    }
}
