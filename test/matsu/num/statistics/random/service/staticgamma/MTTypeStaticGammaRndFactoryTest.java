package matsu.num.statistics.random.service.staticgamma;

import static matsu.num.statistics.random.StaticGammaRnd.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.None;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;

/**
 * {@link MTTypeStaticGammaRndFactory}クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class MTTypeStaticGammaRndFactoryTest {

    public static final Class<?> TEST_CLASS = MTTypeStaticGammaRndFactory.class;
    private static final StaticGammaRnd.Factory FACTORY =
            new MTTypeStaticGammaRndFactory(RandomGeneratorFactoryProvider.byDefaultLib());

    public static class パラメータの境界値テスト {

        private StaticGammaRnd staticGammaRnd;
        private BaseRandom random;

        @Before
        public void before() {
            staticGammaRnd = FACTORY.instance();
            random = BaseRandom.threadSeparatedRandom();
        }

        @Test(expected = None.class)
        public void test_境界内最小値() {
            assertThat(staticGammaRnd.acceptsParameter(LOWER_LIMIT_SHAPE_PARAMETER), is(true));
            staticGammaRnd.nextRandom(random, LOWER_LIMIT_SHAPE_PARAMETER);
        }

        @Test(expected = None.class)
        public void test_境界内最大値() {
            assertThat(staticGammaRnd.acceptsParameter(UPPER_LIMIT_SHAPE_PARAMETER), is(true));
            staticGammaRnd.nextRandom(random, UPPER_LIMIT_SHAPE_PARAMETER);
        }

        @Test
        public void test_境界最小値外() {
            assertThat(staticGammaRnd.acceptsParameter(Math.nextDown(LOWER_LIMIT_SHAPE_PARAMETER)), is(false));
        }

        @Test
        public void test_境界最大値外() {
            assertThat(staticGammaRnd.acceptsParameter(Math.nextUp(UPPER_LIMIT_SHAPE_PARAMETER)), is(false));
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

            private final double k = 4d;
            private final BaseRandom random;
            private final StaticGammaRnd gammaRnd;

            public TestedAt4(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.gammaRnd = FACTORY.instance();
            }

            @Override
            public double newValue() {
                return gammaRnd.nextRandom(random, k);
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

            private final double k = 1d;
            private final BaseRandom random;
            private final StaticGammaRnd gammaRnd;

            public TestedAt1(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.gammaRnd = FACTORY.instance();
            }

            @Override
            public double newValue() {
                return gammaRnd.nextRandom(random, k);
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

            private final double k = 0.25d;
            private final BaseRandom random;
            private final StaticGammaRnd gammaRnd;

            public TestedAtQuarter(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.gammaRnd = FACTORY.instance();
            }

            @Override
            public double newValue() {
                return gammaRnd.nextRandom(random, k)
                        + gammaRnd.nextRandom(random, k)
                        + gammaRnd.nextRandom(random, k)
                        + gammaRnd.nextRandom(random, k);
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
            System.out.println(FACTORY.instance());
            System.out.println();
        }
    }

}
