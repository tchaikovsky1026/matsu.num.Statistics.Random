/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.poi;

import static matsu.num.statistics.random.PoissonRnd.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.None;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.PoissonRnd;
import matsu.num.statistics.random.TestedIntegerRandomGenerator;
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * {@link GammaHomoProcessBasedPoissonRnd} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class GammaHomoProcessBasedPoissonRndFactoryTest {

    public static final Class<?> TEST_CLASS = GammaHomoProcessBasedPoissonRnd.class;
    private static final PoissonRnd.Factory FACTORY =
            GammaHomoProcessBasedPoissonRnd.createFactory(
                    ExponentiationForTesting.INSTANCE, GammaFactoryForTesting.FACTORY);

    @SuppressWarnings("deprecation")
    public static class ファクトリの境界値テスト {

        @Test(expected = None.class)
        public void test_境界内最小値() {
            assertThat(PoissonRnd.acceptsParameter(LOWER_LIMIT_LAMBDA), is(true));
            FACTORY.instanceOf(LOWER_LIMIT_LAMBDA);
        }

        @Test(expected = None.class)
        public void test_境界内最大値() {
            assertThat(PoissonRnd.acceptsParameter(UPPER_LIMIT_LAMBDA), is(true));
            FACTORY.instanceOf(UPPER_LIMIT_LAMBDA);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最小値外() {
            double lambda = Math.nextDown(LOWER_LIMIT_LAMBDA);
            assertThat(PoissonRnd.acceptsParameter(lambda), is(false));
            FACTORY.instanceOf(lambda);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最大値外() {
            double lambda = Math.nextUp(UPPER_LIMIT_LAMBDA);
            assertThat(PoissonRnd.acceptsParameter(lambda), is(false));
            FACTORY.instanceOf(lambda);
        }
    }

    public static class Lambda0に関するテスト {

        @Test
        public void test() {
            PoissonRnd poissonRnd = FACTORY.instanceOf(0d);
            BaseRandom random = BaseRandom.threadSeparatedRandom();

            int u = poissonRnd.nextRandom(random);
            assertThat(u, is(0));
        }
    }

    public static class Lambda10のテスト {

        private IntegerRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = IntegerRandomGeneratorTestingFramework
                    .instanceOf(new TestedAtLambda(BaseRandom.threadSeparatedRandom(), 10));
        }

        @Test
        public void test() {
            framework.test();
        }

    }

    public static class Lambda100のテスト {

        private IntegerRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = IntegerRandomGeneratorTestingFramework
                    .instanceOf(new TestedAtLambda(BaseRandom.threadSeparatedRandom(), 100));
        }

        @Test
        public void test() {
            framework.test();
        }
    }

    /**
     * Poisson乱数発生器のテスタ.
     */
    private static final class TestedAtLambda implements TestedIntegerRandomGenerator {

        private final BaseRandom random;
        private final PoissonRnd poiRnd;

        public TestedAtLambda(BaseRandom random, double lambda) {
            this.random = Objects.requireNonNull(random);
            this.poiRnd = FACTORY.instanceOf(lambda);
        }

        @Override
        public int newValue() {
            return poiRnd.nextRandom(random);
        }

        @Override
        public double cumulativeProbability(int arg) {
            if (arg < 0) {
                return 0;
            }

            final double lambda = this.poiRnd.lambda();

            double sum = 0;
            double v = 1;
            for (int k = 0; k <= arg; k++) {
                sum += v;
                v *= lambda / (k + 1);
            }
            return sum * Math.exp(-lambda);
        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(100));
            System.out.println();
        }
    }

}
