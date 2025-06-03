/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.zeta;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.ZetaRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;
import matsu.num.statistics.random.speedutil.SpeedTestExecutor;

/**
 * {@link RejectionSamplingZetaRnd} のテスト.
 */
@RunWith(Enclosed.class)
final class RejectionSamplingZetaRndTest {

    public static final Class<?> TEST_CLASS = RejectionSamplingZetaRnd.class;

    private static final ZetaRnd.Factory FACTORY =
            RejectionSamplingZetaRnd.createFactory(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY);

    @RunWith(Theories.class)
    public static class 乱数のテスト {

        @DataPoints
        public static double[] s_values = { 1.25, 1.5, 2, 3, 4, 10 };

        @Theory
        public void test_s_2(double s) {
            IntegerRandomGeneratorTestingFramework framework =
                    IntegerRandomGeneratorTestingFramework.instanceOf(
                            new TestedZetaRandomGenerator(FACTORY.instanceOf(s)));
            framework.test();
        }
    }

    @Ignore
    public static class 計算時間評価 {

        @Test
        public void test_乱数生成の実行_s_1_5() {

            var testRnd = FACTORY.instanceOf(1.5);
            BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

            var executor = new SpeedTestExecutor(
                    TEST_CLASS, testRnd, 2_000_000,
                    () -> testRnd.nextRandom(baseRandom));
            executor.execute();
        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(2));
            System.out.println();
        }
    }
}
