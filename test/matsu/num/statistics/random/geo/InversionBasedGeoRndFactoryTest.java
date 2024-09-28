package matsu.num.statistics.random.geo;

import static matsu.num.statistics.random.GeometricRnd.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.None;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GeometricRnd;
import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedIntegerRandomGenerator;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * {@link InversionBasedGeoRndFactory}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
final class InversionBasedGeoRndFactoryTest {

    public static final Class<?> TEST_CLASS = InversionBasedGeoRndFactory.class;
    private static final GeometricRnd.Factory FACTORY =
            new InversionBasedGeoRndFactory(
                    ExponentiationForTesting.INSTANCE, ExponentialFactoryForTesting.FACTORY);

    public static class ファクトリの境界値テスト {

        @Test(expected = None.class)
        public void test_境界内最小値() {
            assertThat(GeometricRnd.acceptsParameter(LOWER_LIMIT_SUCCESS_PROBABILITY), is(true));
            FACTORY.instanceOf(LOWER_LIMIT_SUCCESS_PROBABILITY);
        }

        @Test(expected = None.class)
        public void test_境界内最大値() {
            assertThat(GeometricRnd.acceptsParameter(UPPER_LIMIT_SUCCESS_PROBABILITY), is(true));
            FACTORY.instanceOf(UPPER_LIMIT_SUCCESS_PROBABILITY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最小値外() {
            double p = Math.nextDown(LOWER_LIMIT_SUCCESS_PROBABILITY);
            assertThat(GeometricRnd.acceptsParameter(p), is(false));
            FACTORY.instanceOf(p);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_境界最大値外() {
            double p = Math.nextUp(UPPER_LIMIT_SUCCESS_PROBABILITY);
            assertThat(GeometricRnd.acceptsParameter(p), is(false));
            FACTORY.instanceOf(p);
        }
    }

    public static class 成功確率1のテスト {

        private IntegerRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = IntegerRandomGeneratorTestingFramework
                    .instanceOf(new TestedAtP1(BaseRandom.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * p=1の幾何乱数発生器のテスタ.
         */
        private static final class TestedAtP1 implements TestedIntegerRandomGenerator {

            private final BaseRandom random;
            private final GeometricRnd geoRnd;

            public TestedAtP1(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.geoRnd = FACTORY.instanceOf(1);
            }

            @Override
            public int newValue() {
                return geoRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(int arg) {
                if (arg <= 0) {
                    return 0;
                }
                return 1 - Math.exp(arg * Math.log1p(-geoRnd.successPobability()));
            }

            @Override
            public double cumulativeProbabilityOneBelow(int arg) {
                if (arg <= 1) {
                    return 0;
                }
                return 1 - Math.exp((arg - 1) * Math.log1p(-geoRnd.successPobability()));
            }

        }
    }

    public static class 成功確率0_001のテスト {

        private IntegerRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = IntegerRandomGeneratorTestingFramework
                    .instanceOf(new TestedAtP0_001(BaseRandom.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * p=0.001の幾何乱数発生器の変則型のテスタ.
         */
        private static final class TestedAtP0_001 implements TestedIntegerRandomGenerator {

            private final BaseRandom random;
            private final GeometricRnd geoRnd;

            public TestedAtP0_001(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.geoRnd = FACTORY.instanceOf(0.001);
            }

            @Override
            public int newValue() {
                return geoRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(int arg) {
                if (arg <= 0) {
                    return 0;
                }
                return 1 - Math.exp(arg * Math.log1p(-geoRnd.successPobability()));
            }

            @Override
            public double cumulativeProbabilityOneBelow(int arg) {
                if (arg <= 1) {
                    return 0;
                }
                return 1 - Math.exp((arg - 1) * Math.log1p(-geoRnd.successPobability()));
            }

        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(0.001));
            System.out.println();
        }
    }

}
