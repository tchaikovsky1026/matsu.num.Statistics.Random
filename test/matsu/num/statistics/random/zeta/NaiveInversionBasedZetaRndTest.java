/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.zeta;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.ZetaRnd;

/**
 * {@link NaiveInversionBasedZetaRnd} のテスト.
 */
@RunWith(Enclosed.class)
final class NaiveInversionBasedZetaRndTest {

    public static final Class<?> TEST_CLASS = NaiveInversionBasedZetaRnd.class;

    private static final ZetaRnd.Factory FACTORY =
            NaiveInversionBasedZetaRnd.createFactory();

    @RunWith(Theories.class)
    public static class 乱数のテスト {

        @DataPoints
        public static double[] s_values = { 2, 3, 4, 10 };

        @Theory
        public void test_s_2(double s) {
            IntegerRandomGeneratorTestingFramework framework =
                    IntegerRandomGeneratorTestingFramework.instanceOf(
                            new TestedZetaRandomGenerator(FACTORY.instanceOf(s)));
            framework.test();
        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(1.2));
            System.out.println();
        }
    }
}
