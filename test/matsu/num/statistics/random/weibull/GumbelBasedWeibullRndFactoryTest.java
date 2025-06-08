/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.weibull;

import static matsu.num.statistics.random.WeibullRnd.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.None;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.WeibullRnd;
import matsu.num.statistics.random.gumbel.GumbelFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * {@link GumbelBasedWeibullRnd} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class GumbelBasedWeibullRndFactoryTest {

    public static final Class<?> TEST_CLASS = GumbelBasedWeibullRnd.class;
    private static final WeibullRnd.Factory FACTORY =
            GumbelBasedWeibullRnd.createFactory(
                    ExponentiationForTesting.INSTANCE, GumbelFactoryForTesting.FACTORY);

    @SuppressWarnings("deprecation")
    public static class ファクトリの境界値テスト {

        @Test(expected = None.class)
        public void test_境界内最小値() {
            assertThat(WeibullRnd.acceptsParameter(LOWER_LIMIT_SHAPE_PARAMETER), is(true));
            FACTORY.instanceOf(LOWER_LIMIT_SHAPE_PARAMETER);
        }

        @Test(expected = None.class)
        public void test_境界内最大値() {
            assertThat(WeibullRnd.acceptsParameter(UPPER_LIMIT_SHAPE_PARAMETER), is(true));
            FACTORY.instanceOf(UPPER_LIMIT_SHAPE_PARAMETER);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最小値外() {
            double k = Math.nextDown(LOWER_LIMIT_SHAPE_PARAMETER);
            assertThat(WeibullRnd.acceptsParameter(k), is(false));
            FACTORY.instanceOf(k);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最大値外() {
            double k = Math.nextUp(UPPER_LIMIT_SHAPE_PARAMETER);
            assertThat(WeibullRnd.acceptsParameter(k), is(false));
            FACTORY.instanceOf(k);
        }
    }

    public static class パラメータ2のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            double k = 2;
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedWeibull(BaseRandom.threadSeparatedRandom(), k));
        }

        @Test
        public void test() {
            framework.test();
        }
    }

    private static final class TestedWeibull implements TestedFloatingRandomGenerator {

        private final BaseRandom random;
        private final WeibullRnd weibullRnd;

        public TestedWeibull(BaseRandom random, double k) {
            this.random = Objects.requireNonNull(random);
            this.weibullRnd = FACTORY.instanceOf(k);
        }

        @Override
        public double newValue() {
            return weibullRnd.nextRandom(random);
        }

        @Override
        public double cumulativeProbability(double arg) {
            double k = weibullRnd.shapeParameter();
            return 1 - Math.exp(-Math.exp(k * Math.log(arg)));
        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(2));
            System.out.println();
        }
    }

}
