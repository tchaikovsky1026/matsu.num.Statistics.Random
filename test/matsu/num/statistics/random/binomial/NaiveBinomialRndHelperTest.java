/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.binomial;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BinomialRnd;
import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;

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

    public static class 計算時間評価 {

        @Test
        public void test_乱数生成の実行() {
            int iteration = 1_000;
            BinomialRnd binomialRnd = FACTORY.instanceOf(1_000, 0.5);
            BaseRandom random = BaseRandom.threadSeparatedRandom();

            System.out.println(TEST_CLASS.getName() + ": speed measurement");
            System.out.println(binomialRnd);

            for (int c = 0; c < 5; c++) {
                long startMills = System.currentTimeMillis();
                for (int i = 0; i < iteration; i++) {
                    binomialRnd.nextRandom(random);
                }
                long endMills = System.currentTimeMillis();
                System.out.println((endMills - startMills) * 1E3 / iteration + " us");
            }

            System.out.println();
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
