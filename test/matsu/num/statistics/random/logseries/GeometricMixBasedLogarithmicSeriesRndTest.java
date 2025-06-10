/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.logseries;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.LogarithmicSeriesRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * {@link GeometricMixBasedLogarithmicSeriesRnd} クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
final class GeometricMixBasedLogarithmicSeriesRndTest {

    public static final Class<?> TEST_CLASS = GeometricMixBasedLogarithmicSeriesRnd.class;

    private static final LogarithmicSeriesRnd.Factory FACTORY =
            GeometricMixBasedLogarithmicSeriesRnd.createFactory(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY);

    @RunWith(Theories.class)
    public static class 乱数のテスト {

        @DataPoints
        public static double[] p_values = { 0, 0.01, 0.1, 0.3, 0.5, 0.7, 0.9 };

        @Theory
        public void test(double p) {
            IntegerRandomGeneratorTestingFramework framework =
                    IntegerRandomGeneratorTestingFramework.instanceOf(
                            new TestedLogarithmicSeriesRandomGenerator(FACTORY.instanceOf(p)));
            framework.test();
        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(0.3));
            System.out.println();
        }
    }
}
