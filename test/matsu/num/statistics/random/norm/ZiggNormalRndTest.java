/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.norm;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * {@link ZiggNormalRndFactory} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class ZiggNormalRndTest {

    public static final Class<?> TEST_CLASS = ZiggNormalRnd.class;
    private static final NormalRnd.Factory FACTORY =
            ZiggNormalRnd.createFactory(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY);

    public static class 乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedNormalRandomGenerator(FACTORY.instance()));
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
            NormalRnd normalRnd = FACTORY.instance();
            BaseRandom random = BaseRandom.threadSeparatedRandom();

            System.out.println(TEST_CLASS.getName() + ": speed measurement");
            System.out.println(normalRnd);

            for (int c = 0; c < 5; c++) {
                long startMills = System.nanoTime();
                for (int i = 0; i < iteration; i++) {
                    normalRnd.nextRandom(random);
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
