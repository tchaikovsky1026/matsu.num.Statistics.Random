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
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;

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

    public static class 大きいnに対する計算時間評価 {

        @Test
        public void test_乱数生成の実行() {
            int iteration = 10_000;
            BinomialRnd binomialRnd = FACTORY.instanceOf(1_000_000, 0.5);
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
}
