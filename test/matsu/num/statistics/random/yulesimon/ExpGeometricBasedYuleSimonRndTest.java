/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.yulesimon;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.YuleSimonRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * {@link ExpGeometricBasedYuleSimonRnd} のテスト.
 */
@RunWith(Enclosed.class)
final class ExpGeometricBasedYuleSimonRndTest {

    public static final Class<?> TEST_CLASS = ExpGeometricBasedYuleSimonRnd.class;

    private static final YuleSimonRnd.Factory FACTORY =
            ExpGeometricBasedYuleSimonRnd.createFactory(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY);

    @RunWith(Theories.class)
    public static class 乱数のテスト {

        @DataPoints
        public static double[] rho_values = { 0.3, 1.5, 2, 3, 4, 10 };

        @Theory
        public void test(double rho) {
            IntegerRandomGeneratorTestingFramework framework =
                    IntegerRandomGeneratorTestingFramework.instanceOf(
                            new TestedYuleSimonRandomGenerator(FACTORY.instanceOf(rho)));
            framework.test();
        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(1.5));
            System.out.println();
        }
    }
}
