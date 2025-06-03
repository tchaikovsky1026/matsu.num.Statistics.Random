/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.binomial;

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
import matsu.num.statistics.random.speedutil.SpeedTestExecutor;

/**
 * {@link NaiveBinomialRndHelper} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class NaiveBinomialRndHelperTest {

    public static final Class<?> TEST_CLASS = NaiveBinomialRndHelper.class;

    private static final BinomialRnd.Factory FACTORY = new Factory();

    @RunWith(Theories.class)
    public static class パラメータによる網羅的テスト {

        @DataPoints
        public static BinomialParameters[] parameters = {
                new BinomialParameters(10, 0.1),
                new BinomialParameters(31, 0.1),
                new BinomialParameters(100, 0.1),
                new BinomialParameters(10, 0.3),
                new BinomialParameters(31, 0.3),
                new BinomialParameters(100, 0.3),
                new BinomialParameters(10, 0.7),
                new BinomialParameters(31, 0.7),
                new BinomialParameters(100, 0.7)
        };

        @Theory
        public void test(BinomialParameters parameter) {
            IntegerRandomGeneratorTestingFramework framework =
                    IntegerRandomGeneratorTestingFramework.instanceOf(
                            new TestedBinomialRandomGenerator(parameter.createFrom(FACTORY)));
            framework.test();
        }
    }

    @Ignore
    public static class 計算時間評価 {

        @Test
        public void test_乱数生成の実行() {
            var testRnd = FACTORY.instanceOf(1_000, 0.5);
            BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

            var executor = new SpeedTestExecutor(
                    TEST_CLASS, testRnd, 50_000,
                    () -> testRnd.nextRandom(baseRandom));
            executor.execute();
        }
    }

    private static final class Factory extends SkeletalBinomialRnd.Factory {

        @Override
        BinomialRnd createInstanceOf(int n, double p) {
            return new BinomialImpl(n, p);
        }

        private static final class BinomialImpl extends SkeletalBinomialRnd {

            private final NaiveBinomialRndHelper helper;

            BinomialImpl(int n, double p) {
                super(n, p);
                this.helper = new NaiveBinomialRndHelper();
            }

            @Override
            public int nextRandom(BaseRandom random) {
                return this.helper.next(this.n, this.p, random);
            }
        }
    }
}
