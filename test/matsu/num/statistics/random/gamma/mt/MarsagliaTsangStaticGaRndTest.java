package matsu.num.statistics.random.gamma.mt;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link MarsagliaTsangStaticGaRnd}クラスのテスト.
 *  
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class MarsagliaTsangStaticGaRndTest {

    public static class 形状パラメータ0_25のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedGeneratorAtLow(ThreadLocalRandom.current()));
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
        private static final class TestedGeneratorAtLow implements TestedFloatingRandomGenerator {

            private final Random random;
            private final double k;

            public TestedGeneratorAtLow(Random random) {
                this.random = Objects.requireNonNull(random);
                k = 0.25;
            }

            @Override
            public double newValue() {
                return MarsagliaTsangStaticGaRnd.nextRandom(random, k)
                        + MarsagliaTsangStaticGaRnd.nextRandom(random, k)
                        + MarsagliaTsangStaticGaRnd.nextRandom(random, k)
                        + MarsagliaTsangStaticGaRnd.nextRandom(random, k);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 1 - Math.exp(-arg);
            }

        }
    }

    public static class 形状パラメータ1のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedGeneratorAt1(ThreadLocalRandom.current()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * k=1のガンマ乱数発生器のテスタ=標準指数分布.
         */
        private static final class TestedGeneratorAt1 implements TestedFloatingRandomGenerator {

            private final Random random;
            private final double k;

            public TestedGeneratorAt1(Random random) {
                this.random = Objects.requireNonNull(random);
                k = 1;
            }

            @Override
            public double newValue() {
                return MarsagliaTsangStaticGaRnd.nextRandom(random, k);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 1 - Math.exp(-arg);
            }

        }
    }

    public static class 形状パラメータ4のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedGeneratorAtHigh(ThreadLocalRandom.current()));
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
        private static final class TestedGeneratorAtHigh implements TestedFloatingRandomGenerator {

            private final Random random;
            private final double k;

            public TestedGeneratorAtHigh(Random random) {
                this.random = Objects.requireNonNull(random);
                k = 4;
            }

            @Override
            public double newValue() {
                return MarsagliaTsangStaticGaRnd.nextRandom(random, k);
            }

            @Override
            public double cumulativeProbability(double arg) {
                double arg2 = arg * arg;
                double arg3 = arg2 * arg;
                return 1 - Math.exp(-arg) * (1 + arg + 0.5 * arg2 + (1.0 / 6.0) * arg3);
            }

        }
    }

}
