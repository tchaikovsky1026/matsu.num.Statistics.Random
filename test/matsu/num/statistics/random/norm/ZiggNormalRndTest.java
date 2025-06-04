/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.norm;

import java.util.concurrent.ThreadLocalRandom;

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
import matsu.num.statistics.random.speedutil.SpeedTestExecutor;

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

            var testRnd = FACTORY.instance();
            BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

            var executor = new SpeedTestExecutor(
                    TEST_CLASS, testRnd, 50_000_000,
                    () -> testRnd.nextRandom(baseRandom));
            executor.execute();
        }
    }

    @Ignore
    public static class Java標準ライブラリの計算時間評価 {

        @Test
        public void test_乱数生成の実行() {
            var executor = new SpeedTestExecutor(
                    TEST_CLASS, "ThreadLocalRandom.nextGaussian", 50_000_000,
                    () -> ThreadLocalRandom.current().nextGaussian());
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
