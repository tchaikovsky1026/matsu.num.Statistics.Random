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
import matsu.num.statistics.random.lib.ExponentiationForTesting;
import matsu.num.statistics.random.staticgamma.StaticGammaFactoryForTesting;

/**
 * {@link GammaDirichletBasedStaticPoissonRnd} のテスト.
 */
@RunWith(Enclosed.class)
final class GammaDirichletBasedStaticPoissonRndTest {

    private static final InnerStaticPoissonRnd.Factory FACTORY =
            GammaDirichletBasedStaticPoissonRnd.createFactory(
                    ExponentiationForTesting.INSTANCE,
                    StaticGammaFactoryForTesting.FACTORY,
                    InnerStaticBinomialFactoryForTesting.FACTORY);

    @RunWith(Theories.class)
    public static class パラメータによる網羅的テスト {

        @DataPoints
        public static double[] lambdas = {
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 20, 30, 40, 50, 60, 70, 80, 90, 100
        };

        @Theory
        public void test(double lambda) {
            IntegerRandomGeneratorTestingFramework framework =
                    IntegerRandomGeneratorTestingFramework.instanceOf(
                            new TestedGenerator(FACTORY.instance(), lambda));
            framework.test();
        }
    }

    public static class lambda0のテスト {

        @Test
        public void test_乱数生成結果は常に0() {
            InnerStaticPoissonRnd staticPoissonRnd = FACTORY.instance();

            final int iteration = 1000;
            for (int c = 0; c < iteration; c++) {
                int k = staticPoissonRnd.next(0d, BaseRandom.threadSeparatedRandom());
                assertThat(k, is(0));
            }
        }
    }

    /**
     * Poisson 乱数に関する {@link TestedIntegerRandomGenerator} を提供する. <br>
     * コンストラクタで所望の二項乱数生成器を与えることで動作する.
     */
    private static final class TestedGenerator implements TestedIntegerRandomGenerator {

        private final BaseRandom random = BaseRandom.threadSeparatedRandom();

        private final InnerStaticPoissonRnd staticPoissonRnd;
        private final double lambda;

        TestedGenerator(
                InnerStaticPoissonRnd staticPoissonRnd, double lambda) {
            this.staticPoissonRnd = Objects.requireNonNull(staticPoissonRnd);
            this.lambda = lambda;
        }

        @Override
        public int newValue() {
            return staticPoissonRnd.next(lambda, random);
        }

        @Override
        public double cumulativeProbability(int arg) {
            if (arg < 0) {
                return 0d;
            }

            double sum = 0;
            double v = 1;
            for (int k = 0; k <= arg; k++) {
                sum += v;
                v *= lambda / (k + 1);
            }
            return sum * Math.exp(-lambda);
        }
    }
}
