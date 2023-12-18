package matsu.num.statistics.random.chisq.gamma;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.ChiSquaredRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link ByGaChiSqRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class ByGaChiSqRndTest {

    public static class 自由度1のテスト {

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
         * k=1のカイ二乗乱数発生器の変則型のテスタ. <br>
         * 指数分布に帰着させる. <br>
         * <br>
         * X1,X2がk=1のカイ二乗分布に従うとき, {@literal  Z = X1 + X2}はスケール2の指数分布に従う.
         */
        private static final class TestedGeneratorAt1 implements TestedFloatingRandomGenerator {

            private final Random random;
            private final ChiSquaredRnd chiSquaredRnd;

            public TestedGeneratorAt1(Random random) {
                this.random = Objects.requireNonNull(random);
                this.chiSquaredRnd = ByGaChiSqRnd.instanceOf(1);
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
                    .instanceOf(new TestedGeneratorAt4(ThreadLocalRandom.current()));
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
        private static final class TestedGeneratorAt4 implements TestedFloatingRandomGenerator {

            private final Random random;
            private final ChiSquaredRnd chiSquaredRnd;

            public TestedGeneratorAt4(Random random) {
                this.random = Objects.requireNonNull(random);
                this.chiSquaredRnd = ByGaChiSqRnd.instanceOf(4);
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
}
