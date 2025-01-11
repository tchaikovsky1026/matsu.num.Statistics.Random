/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.staticbeta;

import static matsu.num.statistics.random.StaticBetaRnd.*;
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
import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.staticgamma.StaticGammaFactoryForTesting;

/**
 * {@link GammaBasedStaticBetaRnd}クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
final class GammaBasedStaticBetaRndTest {

    public static final Class<?> TEST_CLASS = GammaBasedStaticBetaRnd.class;
    private static final StaticBetaRnd.Factory FACTORY =
            GammaBasedStaticBetaRnd.createFactory(StaticGammaFactoryForTesting.FACTORY);

    public static class パラメータの境界値テスト {

        private StaticBetaRnd staticBetaRnd;
        private BaseRandom random;

        @Before
        public void before() {
            staticBetaRnd = FACTORY.instance();
            random = BaseRandom.threadSeparatedRandom();
        }

        @Test(expected = None.class)
        public void test_境界内最小値() {
            assertThat(
                    StaticBetaRnd.acceptsParameters(LOWER_LIMIT_SHAPE_PARAMETER, LOWER_LIMIT_SHAPE_PARAMETER),
                    is(true));
            staticBetaRnd.nextRandom(random, LOWER_LIMIT_SHAPE_PARAMETER, LOWER_LIMIT_SHAPE_PARAMETER);
            staticBetaRnd.nextBetaPrime(random, LOWER_LIMIT_SHAPE_PARAMETER, LOWER_LIMIT_SHAPE_PARAMETER);
        }

        @Test(expected = None.class)
        public void test_境界内最大値() {
            assertThat(
                    StaticBetaRnd.acceptsParameters(UPPER_LIMIT_SHAPE_PARAMETER, UPPER_LIMIT_SHAPE_PARAMETER),
                    is(true));
            staticBetaRnd.nextBetaPrime(random, UPPER_LIMIT_SHAPE_PARAMETER, UPPER_LIMIT_SHAPE_PARAMETER);
        }
    }

    public static class ベータ乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
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
            private final double a;
            private final double b;
            private final StaticBetaRnd staticBetaRnd = FACTORY.instance();

            public TestedBeta(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                a = 2;
                b = 1;
            }

            @Override
            public double newValue() {
                return this.staticBetaRnd.nextRandom(random, a, b);
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
            framework = FloatingRandomGeneratorTestingFramework
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
            private final double a;
            private final double b;
            private final StaticBetaRnd staticBetaRnd = FACTORY.instance();

            public TestedBetaPrime(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                a = 2;
                b = 1;
            }

            @Override
            public double newValue() {
                return staticBetaRnd.nextBetaPrime(random, a, b);
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
            System.out.println(FACTORY.instance());
            System.out.println();
        }
    }

}
