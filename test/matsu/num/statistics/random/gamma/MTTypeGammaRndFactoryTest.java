/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.gamma;

import static matsu.num.statistics.random.GammaRnd.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.Test.None;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;
import matsu.num.statistics.random.norm.NormalFactoryForTesting;
import matsu.num.statistics.random.speedutil.SpeedTestExecutor;

/**
 * {@link MTTypeGammaRndFactory} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class MTTypeGammaRndFactoryTest {

    public static final Class<?> TEST_CLASS = MTTypeGammaRndFactory.class;
    private static final GammaRnd.Factory FACTORY =
            MTTypeGammaRndFactory.create(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY, NormalFactoryForTesting.FACTORY);

    @SuppressWarnings("deprecation")
    public static class ファクトリの境界値テスト {

        @Test(expected = None.class)
        public void test_境界内最小値() {
            assertThat(GammaRnd.acceptsParameter(LOWER_LIMIT_SHAPE_PARAMETER), is(true));
            FACTORY.instanceOf(LOWER_LIMIT_SHAPE_PARAMETER);
        }

        @Test(expected = None.class)
        public void test_境界内最大値() {
            assertThat(GammaRnd.acceptsParameter(UPPER_LIMIT_SHAPE_PARAMETER), is(true));
            FACTORY.instanceOf(UPPER_LIMIT_SHAPE_PARAMETER);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最小値外() {
            double k = Math.nextDown(LOWER_LIMIT_SHAPE_PARAMETER);
            assertThat(GammaRnd.acceptsParameter(k), is(false));
            FACTORY.instanceOf(k);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最大値外() {
            double k = Math.nextUp(UPPER_LIMIT_SHAPE_PARAMETER);
            assertThat(GammaRnd.acceptsParameter(k), is(false));
            FACTORY.instanceOf(k);
        }
    }

    public static class 乱数のテスト {

        @Test
        public void test_形状パラメータ_4() {
            FloatingRandomGeneratorTestingFramework
                    .instanceOf(new Tested_At_4_GammaRandomGenerator(FACTORY))
                    .test();
        }

        @Test
        public void test_形状パラメータ_1() {
            FloatingRandomGeneratorTestingFramework
                    .instanceOf(new Tested_At_1_GammaRandomGenerator(FACTORY))
                    .test();
        }

        @Test
        public void test_形状パラメータ_0_25() {
            FloatingRandomGeneratorTestingFramework
                    .instanceOf(new Tested_At_0_25_GammaRandomGenerator(FACTORY))
                    .test();
        }
    }

    @Ignore
    public static class 計算時間評価 {

        @Test
        public void test_乱数生成の実行() {

            var testRnd = FACTORY.instanceOf(20d);
            BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

            var executor = new SpeedTestExecutor(
                    TEST_CLASS, testRnd, 20_000_000,
                    () -> testRnd.nextRandom(baseRandom));
            executor.execute();
        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(4));
            System.out.println(FACTORY.instanceOf(1));
            System.out.println(FACTORY.instanceOf(0.25));
            System.out.println();
        }
    }

}
