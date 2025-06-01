/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.arcsin;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.ArcsineRnd;
import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * {@link ZigguratArcsineRnd} クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
final class ZigguratArcsineRndTest {

    public static final Class<?> TEST_CLASS = ZigguratArcsineRnd.class;

    private static final ArcsineRnd.Factory FACTORY =
            ZigguratArcsineRnd.createFactory(ExponentiationForTesting.INSTANCE);

    public static class 乱数テスト {

        @Test
        public void test() {

            FloatingRandomGeneratorTestingFramework framework =
                    FloatingRandomGeneratorTestingFramework.instanceOf(
                            new TestedArcsineRandomGenerator(FACTORY.instance()));
            framework.test();
        }
    }

    @Ignore
    public static class 計算時間評価 {

        @Test
        public void test_乱数生成の実行() {
            System.gc();

            int iteration = 10_000_000;
            ArcsineRnd binomialRnd = FACTORY.instance();
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
