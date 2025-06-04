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
import matsu.num.statistics.random.speedutil.SpeedTestExecutor;

/**
 * {@link InversionBasedArcsineRnd} クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@SuppressWarnings("deprecation")
@RunWith(Enclosed.class)
final class InversionBasedArcsineRndTest {

    public static final Class<?> TEST_CLASS = InversionBasedArcsineRnd.class;

    private static final ArcsineRnd.Factory FACTORY =
            InversionBasedArcsineRnd.createFactory();

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
            var testRnd = FACTORY.instance();
            BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

            var executor = new SpeedTestExecutor(
                    TEST_CLASS, testRnd, 50_000_000,
                    () -> testRnd.nextRandom(baseRandom));
            executor.execute();
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
