/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.gamma;

import static matsu.num.statistics.random.GammaRnd.*;
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
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;
import matsu.num.statistics.random.norm.NormalFactoryForTesting;

/**
 * {@link MTTypeGammaRndFactory}クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
final class MTTypeGammaRndFactoryTest {

    public static final Class<?> TEST_CLASS = MTTypeGammaRndFactory.class;
    private static final GammaRnd.Factory FACTORY =
            new MTTypeGammaRndFactory(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY, NormalFactoryForTesting.FACTORY);

    public static class ファクトリの境界値テスト {

        @Test(expected = None.class)
        public void test_境界内最小値() {
            assertThat(GammaRnd.acceptsParameter(LOWER_LIMIT_SHAPE_PARAMETER), is(true));
            FACTORY.instanceOf(LOWER_LIMIT_SHAPE_PARAMETER);
        }

        @Test(expected = None.class)
        public void test_境界内最大値() {
            assertThat(GammaRnd.acceptsParameter(UPPER_LIMIT_SHAPE_PARAMETER), is(true));
            FACTORY.instanceOf(UPPER_LIMIT_SHAPE_PARAMETER);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最小値外() {
            double k = Math.nextDown(LOWER_LIMIT_SHAPE_PARAMETER);
            assertThat(GammaRnd.acceptsParameter(k), is(false));
            FACTORY.instanceOf(k);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最大値外() {
            double k = Math.nextUp(UPPER_LIMIT_SHAPE_PARAMETER);
            assertThat(GammaRnd.acceptsParameter(k), is(false));
            FACTORY.instanceOf(k);
        }
    }

    public static class 形状パラメータ4のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedAt4(BaseRandom.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * k=4のガンマ乱数発生器の変則型のテスタ. <br>
         * 累積分布関数は, <br>
         * 1 - e^(-x) (1 + x + (1/2) * x^2 + (1/6)x^3)
         *
         */
        private static final class TestedAt4 implements TestedFloatingRandomGenerator {

            private final BaseRandom random;
            private final GammaRnd gammaRnd;

            public TestedAt4(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.gammaRnd = FACTORY.instanceOf(4);
            }

            @Override
            public double newValue() {
                return gammaRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                double arg2 = arg * arg;
                double arg3 = arg2 * arg;
                return 1 - Math.exp(-arg) * (1 + arg + 0.5 * arg2 + (1.0 / 6.0) * arg3);
            }

        }
    }

    public static class 形状パラメータ1のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedAt1(BaseRandom.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * k=1のガンマ乱数発生器のテスタ=標準指数分布.
         */
        private static final class TestedAt1 implements TestedFloatingRandomGenerator {

            private final BaseRandom random;
            private final GammaRnd gammaRnd;

            public TestedAt1(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.gammaRnd = FACTORY.instanceOf(1);
            }

            @Override
            public double newValue() {
                return gammaRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 1 - Math.exp(-arg);
            }
        }
    }

    public static class 形状パラメータ0_25のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedAtQuarter(BaseRandom.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * k=0.25のガンマ乱数発生器の変則型のテスタ. <br>
         * 指数分布に帰着させる. <br>
         * <br>
         * X1からX4がk=0.25ガンマ分布に従うとき,
         * {@literal  Z = X1 + X2 + X3 + X4}は標準指数分布に従う.
         */
        private static final class TestedAtQuarter implements TestedFloatingRandomGenerator {

            private final BaseRandom random;
            private final GammaRnd gammaRnd;

            public TestedAtQuarter(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.gammaRnd = FACTORY.instanceOf(0.25);
            }

            @Override
            public double newValue() {
                return gammaRnd.nextRandom(random) + gammaRnd.nextRandom(random) + gammaRnd.nextRandom(random)
                        + gammaRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 1 - Math.exp(-arg);
            }

        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(4));
            System.out.println(FACTORY.instanceOf(1));
            System.out.println(FACTORY.instanceOf(0.25));
            System.out.println();
        }
    }

}
