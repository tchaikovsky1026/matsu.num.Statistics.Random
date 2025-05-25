/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.negbinomial;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.NegativeBinomialRnd;
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * {@link GammaPoissonBasedNegativeBinomialRnd} クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
final class GammaPoissonBasedNegativeBinomialRndTest {

    public static final Class<?> TEST_CLASS = GammaPoissonBasedNegativeBinomialRnd.class;

    private static final NegativeBinomialRnd.Factory FACTORY =
            GammaPoissonBasedNegativeBinomialRnd.createFactory(
                    ExponentiationForTesting.INSTANCE, GammaFactoryForTesting.FACTORY);

    @RunWith(Theories.class)
    public static class パラメータによる網羅的テスト {

        @DataPoints
        public static NegativeBinomialParameters[] parameters = {
                new NegativeBinomialParameters(1, 0.01),
                new NegativeBinomialParameters(3, 0.01),
                new NegativeBinomialParameters(10, 0.01),
                new NegativeBinomialParameters(1, 0.3),
                new NegativeBinomialParameters(10, 0.3),
                new NegativeBinomialParameters(31, 0.3),
                new NegativeBinomialParameters(100, 0.3),
                new NegativeBinomialParameters(1, 0.7),
                new NegativeBinomialParameters(10, 0.7),
                new NegativeBinomialParameters(31, 0.7),
                new NegativeBinomialParameters(100, 0.7),
                new NegativeBinomialParameters(1, 1),
                new NegativeBinomialParameters(10, 1),
                new NegativeBinomialParameters(31, 1),
                new NegativeBinomialParameters(100, 1)
        };

        @Theory
        public void test(NegativeBinomialParameters parameter) {
            IntegerRandomGeneratorTestingFramework framework =
                    IntegerRandomGeneratorTestingFramework.instanceOf(
                            new TestedNegativeBinomialRandomGenerator(parameter.createFrom(FACTORY)));
            framework.test();
        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(20, 0.3));
            System.out.println();
        }
    }
}
