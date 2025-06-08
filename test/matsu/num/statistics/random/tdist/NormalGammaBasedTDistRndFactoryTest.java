/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.tdist;

import static matsu.num.statistics.random.TDistributionRnd.*;
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
import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;
import matsu.num.statistics.random.norm.NormalFactoryForTesting;

/**
 * {@link NormalGammaBasedTDistRnd} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class NormalGammaBasedTDistRndFactoryTest {

    public static final Class<?> TEST_CLASS = NormalGammaBasedTDistRnd.class;
    private static final TDistributionRnd.Factory FACTORY =
            NormalGammaBasedTDistRnd.createFactory(
                    ExponentiationForTesting.INSTANCE,
                    NormalFactoryForTesting.FACTORY,
                    GammaFactoryForTesting.FACTORY);

    @SuppressWarnings("deprecation")
    public static class ファクトリの境界値テスト {

        @Test(expected = None.class)
        public void test_境界内最小値() {
            assertThat(TDistributionRnd.acceptsParameter(LOWER_LIMIT_DEGREES_OF_FREEDOM), is(true));
            FACTORY.instanceOf(LOWER_LIMIT_DEGREES_OF_FREEDOM);
        }

        @Test(expected = None.class)
        public void test_境界内最大値() {
            assertThat(TDistributionRnd.acceptsParameter(UPPER_LIMIT_DEGREES_OF_FREEDOM), is(true));
            FACTORY.instanceOf(UPPER_LIMIT_DEGREES_OF_FREEDOM);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最小値外() {
            double nu = Math.nextDown(LOWER_LIMIT_DEGREES_OF_FREEDOM);
            assertThat(TDistributionRnd.acceptsParameter(nu), is(false));
            FACTORY.instanceOf(nu);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最大値外() {
            double nu = Math.nextUp(UPPER_LIMIT_DEGREES_OF_FREEDOM);
            assertThat(TDistributionRnd.acceptsParameter(nu), is(false));
            FACTORY.instanceOf(nu);
        }
    }

    public static class 自由度2のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedAt2(BaseRandom.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * nu=2のt乱数発生器のテスタ. <br>
         * 累積分布関数は, <br>
         * (1/2)[1 + t/sqrt(2 + t^2)]
         *
         */
        private static final class TestedAt2 implements TestedFloatingRandomGenerator {

            private final BaseRandom random;
            private final TDistributionRnd tRnd;

            public TestedAt2(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.tRnd = FACTORY.instanceOf(2);
            }

            @Override
            public double newValue() {
                return tRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 0.5 * (1 + arg / Math.sqrt(2 + arg * arg));
            }

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
