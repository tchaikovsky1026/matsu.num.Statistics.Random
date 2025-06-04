/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.exp;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.lib.ExponentiationForTesting;
import matsu.num.statistics.random.speedutil.SpeedTestExecutor;

/**
 * {@link LongSubstitutedZiggExponentialRnd} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class LongSubstitutedZiggExponentialRndTest {

    public static final Class<?> TEST_CLASS = LongSubstitutedZiggExponentialRnd.class;
    private static final ExponentialRnd.Factory FACTORY =
            LongSubstitutedZiggExponentialRnd.createFactory(ExponentiationForTesting.INSTANCE);

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
            var testRnd = FACTORY.instance();
            BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

            var myRndExecutor = new SpeedTestExecutor(
                    TEST_CLASS, testRnd, 50_000_000,
                    () -> testRnd.nextRandom(baseRandom));
            myRndExecutor.execute();

            var javaApiExecutor = new SpeedTestExecutor(
                    null, "ThreadLocalRandom.nextExponential", 50_000_000,
                    () -> ThreadLocalRandom.current().nextExponential());
            javaApiExecutor.execute();
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
