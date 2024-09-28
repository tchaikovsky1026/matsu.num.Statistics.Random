package matsu.num.statistics.random.chisq;

import static matsu.num.statistics.random.ChiSquaredRnd.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.None;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ChiSquaredRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;

/**
 * {@link GammaTypeChiSquaredRndFactory}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
final class GammaTypeChiSquaredRndFactoryTest {

    public static final Class<?> TEST_CLASS = GammaTypeChiSquaredRndFactory.class;
    private static final ChiSquaredRnd.Factory FACTORY =
            new GammaTypeChiSquaredRndFactory(GammaFactoryForTesting.FACTORY);

    public static class ファクトリの境界値テスト {

        @Test(expected = None.class)
        public void test_境界内最小値() {
            assertThat(ChiSquaredRnd.acceptsParameter(LOWER_LIMIT_DEGREES_OF_FREEDOM), is(true));
            FACTORY.instanceOf(LOWER_LIMIT_DEGREES_OF_FREEDOM);
        }

        @Test(expected = None.class)
        public void test_境界内最大値() {
            assertThat(ChiSquaredRnd.acceptsParameter(UPPER_LIMIT_DEGREES_OF_FREEDOM), is(true));
            FACTORY.instanceOf(UPPER_LIMIT_DEGREES_OF_FREEDOM);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最小値外() {
            double k = Math.nextDown(LOWER_LIMIT_DEGREES_OF_FREEDOM);
            assertThat(ChiSquaredRnd.acceptsParameter(k), is(false));
            FACTORY.instanceOf(k);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最大値外() {
            double k = Math.nextUp(UPPER_LIMIT_DEGREES_OF_FREEDOM);
            assertThat(ChiSquaredRnd.acceptsParameter(k), is(false));
            FACTORY.instanceOf(k);
        }
    }

    public static class 自由度1のテスト {

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
         * k=1のカイ二乗乱数発生器の変則型のテスタ. <br>
         * 指数分布に帰着させる. <br>
         * <br>
         * X1,X2がk=1のカイ二乗分布に従うとき, {@literal  Z = X1 + X2}はスケール2の指数分布に従う.
         */
        private static final class TestedAt1 implements TestedFloatingRandomGenerator {

            private final BaseRandom random;
            private final ChiSquaredRnd chiSquaredRnd;

            public TestedAt1(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.chiSquaredRnd = FACTORY.instanceOf(1);
            }

            @Override
            public double newValue() {
                return chiSquaredRnd.nextRandom(random) + chiSquaredRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 1 - Math.exp(-arg / 2);
            }

        }
    }

    public static class 自由度4のテスト {

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
         * k=4のカイ二乗乱数発生器のテスタ. <br>
         * 累積分布関数は, <br>
         * 1 - e^(-x/2) (1 + (1/2)x)
         *
         */
        private static final class TestedAt4 implements TestedFloatingRandomGenerator {

            private final BaseRandom random;
            private final ChiSquaredRnd chiSquaredRnd;

            public TestedAt4(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.chiSquaredRnd = FACTORY.instanceOf(4);
            }

            @Override
            public double newValue() {
                return chiSquaredRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 1 - Math.exp(-arg / 2) * (1 + 0.5 * arg);
            }

        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(4));
            System.out.println();
        }
    }
}
