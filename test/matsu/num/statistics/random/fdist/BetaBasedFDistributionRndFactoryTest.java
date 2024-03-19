package matsu.num.statistics.random.fdist;

import static matsu.num.statistics.random.FDistributionRnd.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.None;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.FDistributionRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.beta.BetaFactoryForTesting;

/**
 * {@link BetaBasedFDistributionRndFactory}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
final class BetaBasedFDistributionRndFactoryTest {

    public static final Class<?> TEST_CLASS = BetaBasedFDistributionRndFactory.class;
    private static final FDistributionRnd.Factory FACTORY =
            new BetaBasedFDistributionRndFactory(BetaFactoryForTesting.FACTORY);

    public static class ファクトリの境界値テスト {

        @Test(expected = None.class)
        public void test_境界内最小値() {
            assertThat(
                    FACTORY.acceptsParameters(LOWER_LIMIT_DEGREES_OF_FREEDOM, LOWER_LIMIT_DEGREES_OF_FREEDOM),
                    is(true));
            FACTORY.instanceOf(LOWER_LIMIT_DEGREES_OF_FREEDOM, LOWER_LIMIT_DEGREES_OF_FREEDOM);
        }

        @Test(expected = None.class)
        public void test_境界内最大値() {
            assertThat(
                    FACTORY.acceptsParameters(UPPER_LIMIT_DEGREES_OF_FREEDOM, UPPER_LIMIT_DEGREES_OF_FREEDOM),
                    is(true));
            FACTORY.instanceOf(UPPER_LIMIT_DEGREES_OF_FREEDOM, UPPER_LIMIT_DEGREES_OF_FREEDOM);
        }

        @Test
        public void test_境界最小値外() {
            assertThat(
                    FACTORY.acceptsParameters(
                            Math.nextDown(LOWER_LIMIT_DEGREES_OF_FREEDOM), LOWER_LIMIT_DEGREES_OF_FREEDOM),
                    is(false));
            assertThat(
                    FACTORY.acceptsParameters(
                            LOWER_LIMIT_DEGREES_OF_FREEDOM, Math.nextDown(LOWER_LIMIT_DEGREES_OF_FREEDOM)),
                    is(false));
        }

        @Test
        public void test_境界最大値外() {
            assertThat(
                    FACTORY.acceptsParameters(
                            Math.nextUp(UPPER_LIMIT_DEGREES_OF_FREEDOM), UPPER_LIMIT_DEGREES_OF_FREEDOM),
                    is(false));
            assertThat(
                    FACTORY.acceptsParameters(
                            UPPER_LIMIT_DEGREES_OF_FREEDOM, Math.nextUp(UPPER_LIMIT_DEGREES_OF_FREEDOM)),
                    is(false));
        }
    }

    public static class 自由度_分子4_分母2のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedAt4and2(BaseRandom.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * d1=4,d2=2のF乱数発生器のテスタ. <br>
         * 累積分布関数は {@literal 1 - (x + (1/4))/(x + (1/2))^2}.
         */
        private static final class TestedAt4and2 implements TestedFloatingRandomGenerator {

            private final BaseRandom random;
            private final FDistributionRnd fRnd;

            public TestedAt4and2(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.fRnd = FACTORY.instanceOf(4, 2);
            }

            @Override
            public double newValue() {
                return fRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 1 - (arg + 0.25) / ((arg + 0.5) * (arg + 0.5));
            }

        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(4, 2));
            System.out.println();
        }
    }
}
