/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.binomial;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BinomialRnd;
import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;
import matsu.num.statistics.random.speedutil.SpeedTestExecutor;

/**
 * {@link DirichletBasedBinomialRnd} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class DirichletBasedBinomialRndTest {

    public static final Class<?> TEST_CLASS = DirichletBasedBinomialRnd.class;

    private static final BinomialRnd.Factory FACTORY =
            DirichletBasedBinomialRnd.createFactory(GammaFactoryForTesting.FACTORY);

    @RunWith(Theories.class)
    public static class パラメータによる網羅的テスト {

        @DataPoints
        public static BinomialParameters[] parameters = {
                new BinomialParameters(1, 0.01),
                new BinomialParameters(10, 0.01),
                new BinomialParameters(31, 0.01),
                new BinomialParameters(100, 0.01),
                new BinomialParameters(1, 0.3),
                new BinomialParameters(10, 0.3),
                new BinomialParameters(31, 0.3),
                new BinomialParameters(100, 0.3),
                new BinomialParameters(1, 0.7),
                new BinomialParameters(10, 0.7),
                new BinomialParameters(31, 0.7),
                new BinomialParameters(100, 0.7),
                new BinomialParameters(1, 0.99),
                new BinomialParameters(10, 0.99),
                new BinomialParameters(31, 0.99),
                new BinomialParameters(100, 0.99)
        };

        @Theory
        public void test(BinomialParameters parameter) {
            IntegerRandomGeneratorTestingFramework framework =
                    IntegerRandomGeneratorTestingFramework.instanceOf(
                            new TestedBinomialRandomGenerator(parameter.createFrom(FACTORY)));
            framework.test();
        }
    }

    @RunWith(Theories.class)
    public static class 成功確率0に関するテスト {

        @DataPoints
        public static int[] trialsList = {
                0, 1, 2, 10, 31, 100
        };

        @Theory
        public void test(int trials) {
            BinomialRnd binomialRnd = FACTORY.instanceOf(trials, 0d);
            BaseRandom random = BaseRandom.threadSeparatedRandom();

            final int iteration = 100;
            for (int c = 0; c < iteration; c++) {
                int u = binomialRnd.nextRandom(random);
                assertThat(u, is(0));
            }
        }
    }

    @RunWith(Theories.class)
    public static class 成功確率1に関するテスト {

        @DataPoints
        public static int[] trialsList = {
                0, 1, 2, 10, 31, 100
        };

        @Theory
        public void test(int trials) {
            BinomialRnd binomialRnd = FACTORY.instanceOf(trials, 1d);
            BaseRandom random = BaseRandom.threadSeparatedRandom();

            final int iteration = 100;
            for (int c = 0; c < iteration; c++) {
                int u = binomialRnd.nextRandom(random);
                assertThat(u, is(binomialRnd.numberOfTrials()));
            }
        }
    }

    public static class 試行回数0に関するテスト {

        @Test
        public void test() {
            BinomialRnd binomialRnd = FACTORY.instanceOf(0, 0.5d);
            BaseRandom random = BaseRandom.threadSeparatedRandom();

            int u = binomialRnd.nextRandom(random);
            assertThat(u, is(0));
        }
    }

    @Ignore
    public static class 計算時間評価 {

        @Test
        public void test_乱数生成の実行() {
            var testRnd = FACTORY.instanceOf(1_000_000, 0.5);
            BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

            var executor = new SpeedTestExecutor(
                    TEST_CLASS, testRnd, 500_000,
                    () -> testRnd.nextRandom(baseRandom));
            executor.execute();
        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(20, 0.3));
            System.out.println();
        }
    }
}
