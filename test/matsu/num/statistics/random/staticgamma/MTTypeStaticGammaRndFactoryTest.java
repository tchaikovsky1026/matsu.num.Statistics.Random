/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.staticgamma;

import static matsu.num.statistics.random.StaticGammaRnd.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Test.None;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;
import matsu.num.statistics.random.norm.NormalFactoryForTesting;
import matsu.num.statistics.random.speedutil.SpeedTestExecutor;

/**
 * {@link MTTypeStaticGammaRnd} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class MTTypeStaticGammaRndFactoryTest {

    public static final Class<?> TEST_CLASS = MTTypeStaticGammaRnd.class;
    private static final StaticGammaRnd.Factory FACTORY =
            MTTypeStaticGammaRnd.createFactory(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY,
                    NormalFactoryForTesting.FACTORY);

    public static class パラメータの境界値テスト {

        private StaticGammaRnd staticGammaRnd;
        private BaseRandom random;

        @Before
        public void before() {
            staticGammaRnd = FACTORY.instance();
            random = BaseRandom.threadSeparatedRandom();
        }

        @Test(expected = None.class)
        public void test_境界内最小値() {
            assertThat(StaticGammaRnd.acceptsParameter(LOWER_LIMIT_SHAPE_PARAMETER), is(true));
            staticGammaRnd.nextRandom(random, LOWER_LIMIT_SHAPE_PARAMETER);
        }

        @Test(expected = None.class)
        public void test_境界内最大値() {
            assertThat(StaticGammaRnd.acceptsParameter(UPPER_LIMIT_SHAPE_PARAMETER), is(true));
            staticGammaRnd.nextRandom(random, UPPER_LIMIT_SHAPE_PARAMETER);
        }
    }

    public static class 乱数のテスト {

        @Test
        public void test_形状パラメータ_4() {
            FloatingRandomGeneratorTestingFramework
                    .instanceOf(new Tested_At_4_StaticGammaRandomGenerator(FACTORY.instance()))
                    .test();
        }

        @Test
        public void test_形状パラメータ_1() {
            FloatingRandomGeneratorTestingFramework
                    .instanceOf(new Tested_At_1_StaticGammaRandomGenerator(FACTORY.instance()))
                    .test();
        }

        @Test
        public void test_形状パラメータ_0_25() {
            FloatingRandomGeneratorTestingFramework
                    .instanceOf(new Tested_At_0_25_StaticGammaRandomGenerator(FACTORY.instance()))
                    .test();
        }
    }

    @Ignore
    public static class 計算時間評価 {

        @Test
        public void test_乱数生成の実行() {

            final double k = 20d;
            var testRnd = FACTORY.instance();
            BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

            var executor = new SpeedTestExecutor(
                    TEST_CLASS, testRnd, 20_000_000,
                    () -> testRnd.nextRandom(baseRandom, k));
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
