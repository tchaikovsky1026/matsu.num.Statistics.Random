/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.beta;

import static matsu.num.statistics.random.BetaRnd.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.None;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;

/**
 * {@link GammaBasedBetaRnd} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class GammaBasedBetaRndTest {

    public static final Class<?> TEST_CLASS = GammaBasedBetaRnd.class;
    private static final BetaRnd.Factory FACTORY =
            GammaBasedBetaRnd.createFactory(GammaFactoryForTesting.FACTORY);

    public static class ファクトリの境界値テスト {

        @Test(expected = None.class)
        public void test_境界内最小値() {
            assertThat(BetaRnd.acceptsParameters(LOWER_LIMIT_SHAPE_PARAMETER, LOWER_LIMIT_SHAPE_PARAMETER), is(true));
            FACTORY.instanceOf(LOWER_LIMIT_SHAPE_PARAMETER, LOWER_LIMIT_SHAPE_PARAMETER);
        }

        @Test(expected = None.class)
        public void test_境界内最大値() {
            assertThat(BetaRnd.acceptsParameters(UPPER_LIMIT_SHAPE_PARAMETER, UPPER_LIMIT_SHAPE_PARAMETER), is(true));
            FACTORY.instanceOf(UPPER_LIMIT_SHAPE_PARAMETER, UPPER_LIMIT_SHAPE_PARAMETER);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最小値外_a() {
            double a = Math.nextDown(LOWER_LIMIT_SHAPE_PARAMETER);
            double b = LOWER_LIMIT_SHAPE_PARAMETER;
            assertThat(BetaRnd.acceptsParameters(a, b), is(false));
            FACTORY.instanceOf(a, b);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最小値外_b() {
            double a = LOWER_LIMIT_SHAPE_PARAMETER;
            double b = Math.nextDown(LOWER_LIMIT_SHAPE_PARAMETER);
            assertThat(BetaRnd.acceptsParameters(a, b), is(false));
            FACTORY.instanceOf(a, b);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最大値外_a() {
            double a = Math.nextUp(UPPER_LIMIT_SHAPE_PARAMETER);
            double b = UPPER_LIMIT_SHAPE_PARAMETER;
            assertThat(BetaRnd.acceptsParameters(a, b), is(false));
            FACTORY.instanceOf(a, b);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最大値外_b() {
            double a = UPPER_LIMIT_SHAPE_PARAMETER;
            double b = Math.nextUp(UPPER_LIMIT_SHAPE_PARAMETER);
            assertThat(BetaRnd.acceptsParameters(a, b), is(false));
            FACTORY.instanceOf(a, b);
        }
    }

    public static class ベータ乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework =
                    FloatingRandomGeneratorTestingFramework
                            .instanceOf(new TestedBeta(BaseRandom.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * a=2,b=1のベータ乱数発生器のテスタ. <br>
         * 累積分布関数はx^2
         */
        private static final class TestedBeta implements TestedFloatingRandomGenerator {

            private final BaseRandom random;
            private final BetaRnd betaRnd;

            public TestedBeta(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.betaRnd = FACTORY.instanceOf(2, 1);
            }

            @Override
            public double newValue() {
                return betaRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return arg * arg;
            }

        }
    }

    public static class ベータプライム乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework =
                    FloatingRandomGeneratorTestingFramework
                            .instanceOf(new TestedBetaPrime(BaseRandom.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * a=2,b=1のベータプライム乱数発生器のテスタ. <br>
         * 累積分布関数は<br>
         * {@literal 1 - (1+2x)/(1+x)^2}
         */
        private static final class TestedBetaPrime implements TestedFloatingRandomGenerator {

            private final BaseRandom random;
            private final BetaRnd betaRnd;

            public TestedBetaPrime(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.betaRnd = FACTORY.instanceOf(2, 1);
            }

            @Override
            public double newValue() {
                return betaRnd.nextBetaPrime(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 1 - (1 + 2 * arg) / ((1 + arg) * (1 + arg));
            }

        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(2, 1));
            System.out.println();
        }
    }
}
