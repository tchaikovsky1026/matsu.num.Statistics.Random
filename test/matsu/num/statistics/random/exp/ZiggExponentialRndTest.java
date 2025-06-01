/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.exp;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * {@link ZiggExponentialRnd} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class ZiggExponentialRndTest {

    public static final Class<?> TEST_CLASS = ZiggExponentialRnd.class;
    private static final ExponentialRnd.Factory FACTORY =
            ZiggExponentialRnd.createFactory(ExponentiationForTesting.INSTANCE);

    public static class 乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedExponentialRandomGenerator(FACTORY.instance()));
        }

        @Test
        public void test() {
            framework.test();
        }
    }

    @Ignore
    public static class 計算時間評価 {

        @Test
        public void test_乱数生成の実行() {
            System.gc();

            int iteration = 50_000_000;
            ExponentialRnd binomialRnd = FACTORY.instance();
            BaseRandom random = BaseRandom.threadSeparatedRandom();

            System.out.println(TEST_CLASS.getName() + ": speed measurement");
            System.out.println(binomialRnd);

            for (int c = 0; c < 5; c++) {
                long startMills = System.nanoTime();
                for (int i = 0; i < iteration; i++) {
                    binomialRnd.nextRandom(random);
                }
                long endMills = System.nanoTime();
                System.out.println(
                        "%.3f ns".formatted((double) (endMills - startMills) / iteration));
            }

            System.out.println();
        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instance());
            System.out.println();
        }
    }
}
