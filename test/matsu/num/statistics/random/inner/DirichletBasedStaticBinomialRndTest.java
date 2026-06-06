/*
 * Copyright © 2026 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.inner;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Objects;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedIntegerRandomGenerator;
import matsu.num.statistics.random.staticgamma.StaticGammaFactoryForTesting;

/**
 * {@link DirichletBasedStaticBinomialRnd} のテスト.
 */
@RunWith(Enclosed.class)
final class DirichletBasedStaticBinomialRndTest {

    private static final InnerStaticBinomialRnd.Factory FACTORY =
            DirichletBasedStaticBinomialRnd.createFactory(StaticGammaFactoryForTesting.FACTORY);

    @RunWith(Theories.class)
    public static class パラメータによる網羅的テスト {

        @DataPoints
        public static BinomialParameters[] parameters = {
                new BinomialParameters(0, 0.01),
                new BinomialParameters(10, 0.01),
                new BinomialParameters(31, 0.01),
                new BinomialParameters(100, 0.01),
                new BinomialParameters(0, 0.3),
                new BinomialParameters(10, 0.3),
                new BinomialParameters(31, 0.3),
                new BinomialParameters(100, 0.3),
                new BinomialParameters(0, 0.7),
                new BinomialParameters(10, 0.7),
                new BinomialParameters(31, 0.7),
                new BinomialParameters(100, 0.7),
                new BinomialParameters(0, 0.99),
                new BinomialParameters(10, 0.99),
                new BinomialParameters(31, 0.99),
                new BinomialParameters(100, 0.99)
        };

        @Theory
        public void test(BinomialParameters parameter) {
            IntegerRandomGeneratorTestingFramework framework =
                    IntegerRandomGeneratorTestingFramework.instanceOf(
                            new TestedGenerator(FACTORY.instance(), parameter));
            framework.test();
        }
    }

    @RunWith(Theories.class)
    public static class 確率1のテスト {

        @DataPoints
        public static int[] ns = {
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 100, 1_000, 10_000, 100_000
        };

        @Theory
        public void test_乱数生成結果は常にn(int n) {
            InnerStaticBinomialRnd staticBinomialRnd = FACTORY.instance();

            final int iteration = 1000;
            for (int c = 0; c < iteration; c++) {
                int k = staticBinomialRnd.next(n, 1d, BaseRandom.threadSeparatedRandom());
                assertThat(k, is(n));
            }
        }
    }

    @RunWith(Theories.class)
    public static class 確率0のテスト {

        @DataPoints
        public static int[] ns = {
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 100, 1_000, 10_000, 100_000
        };

        @Theory
        public void test_乱数生成結果は常に0(int n) {
            InnerStaticBinomialRnd staticBinomialRnd = FACTORY.instance();

            final int iteration = 1000;
            for (int c = 0; c < iteration; c++) {
                int k = staticBinomialRnd.next(n, 0d, BaseRandom.threadSeparatedRandom());
                assertThat(k, is(0));
            }
        }
    }

    public static class 試行回数の上限での実行トライ {

        @Test
        public void test_試行回数最大でのテスト() {
            InnerStaticBinomialRnd staticBinomialRnd = FACTORY.instance();

            @SuppressWarnings("deprecation")
            int n = InnerStaticBinomialRnd.UPPER_LIMIT_TRIALS;

            final int iteration = 1000;
            for (int c = 0; c < iteration; c++) {
                double p = BaseRandom.threadSeparatedRandom().nextDouble();
                staticBinomialRnd.next(n, p, BaseRandom.threadSeparatedRandom());
            }
        }
    }

    /**
     * 二項乱数に関する {@link TestedIntegerRandomGenerator} を提供する. <br>
     * コンストラクタで所望の二項乱数生成器を与えることで動作する.
     */
    private static final class TestedGenerator implements TestedIntegerRandomGenerator {

        private final BaseRandom random = BaseRandom.threadSeparatedRandom();

        private final InnerStaticBinomialRnd staticBinomialRnd;
        private final BinomialParameters parameters;

        TestedGenerator(
                InnerStaticBinomialRnd staticBinomialRnd, BinomialParameters parameters) {
            this.staticBinomialRnd = Objects.requireNonNull(staticBinomialRnd);
            this.parameters = Objects.requireNonNull(parameters);
        }

        @Override
        public int newValue() {
            return staticBinomialRnd.next(parameters.n, parameters.p, random);
        }

        @Override
        public double cumulativeProbability(int arg) {
            if (arg < 0) {
                return 0d;
            }
            if (arg > parameters.n) {
                return 1d;
            }

            double sum = 0d;
            for (int i = 0; i <= arg; i++) {
                sum += this.probabilityMassFunction(i);
            }
            return sum;
        }

        /**
         * 引数には
         * {@literal 0 <= arg <= n}
         * でなければならない.
         */
        private double probabilityMassFunction(int k) {
            int n = parameters.n;
            double p = parameters.p;

            /*
             * オーバーフロー対策のため, 対数により計算する.
             */
            double logP = 0d;
            //係数
            for (int i = 1; i <= Math.min(k, n - k); i++) {
                logP += Math.log(((double) n - i + 1) / i);
            }

            logP += k * Math.log(p)
                    + (n - k) * Math.log1p(-p);

            return Math.exp(logP);
        }
    }
}
