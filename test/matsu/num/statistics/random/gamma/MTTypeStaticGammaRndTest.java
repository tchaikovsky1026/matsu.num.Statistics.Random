package matsu.num.statistics.random.gamma;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link MTTypeStaticGammaRnd}クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class MTTypeStaticGammaRndTest {

    public static final Class<?> TEST_CLASS = MTTypeStaticGammaRnd.class;

    public static class 形状パラメータ4のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedAt4(Random.threadSeparatedRandom()));
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
            private final Random random;
            private final StaticGammaRnd gammaRnd;

            public TestedAt4(Random random) {
                this.random = Objects.requireNonNull(random);
                this.gammaRnd = new MTTypeStaticGammaRnd();
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
                    .instanceOf(new TestedAt1(Random.threadSeparatedRandom()));
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
            private final Random random;
            private final StaticGammaRnd gammaRnd;

            public TestedAt1(Random random) {
                this.random = Objects.requireNonNull(random);
                this.gammaRnd = new MTTypeStaticGammaRnd();
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
                    .instanceOf(new TestedAtQuarter(Random.threadSeparatedRandom()));
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
            private final Random random;
            private final StaticGammaRnd gammaRnd;

            public TestedAtQuarter(Random random) {
                this.random = Objects.requireNonNull(random);
                this.gammaRnd = new MTTypeStaticGammaRnd();
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
            System.out.println(new MTTypeStaticGammaRnd());
            System.out.println();
        }
    }

}
