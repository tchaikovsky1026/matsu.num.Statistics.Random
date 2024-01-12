package matsu.num.statistics.random.beta;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link GammaBasedBetaEnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class GammaBasedBetaEndTest {

    public static final Class<?> TEST_CLASS = GammaBasedBetaEnd.class;

    public static class ベータ乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework =
                    FloatingRandomGeneratorTestingFramework.instanceOf(new TestedBeta(Random.threadSeparatedRandom()));
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

            private final Random random;
            private final BetaRnd betaRnd;

            public TestedBeta(Random random) {
                this.random = Objects.requireNonNull(random);
                this.betaRnd = new GammaBasedBetaEnd(2, 1);
            }

            @Override
            public double newValue() {
                return betaRnd.nextRandom(random);
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
            framework =
                    FloatingRandomGeneratorTestingFramework
                            .instanceOf(new TestedBetaPrime(Random.threadSeparatedRandom()));
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

            private final Random random;
            private final BetaRnd betaRnd;

            public TestedBetaPrime(Random random) {
                this.random = Objects.requireNonNull(random);
                this.betaRnd = new GammaBasedBetaEnd(2, 1);
            }

            @Override
            public double newValue() {
                return betaRnd.nextBetaPrime(random);
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
            System.out.println(new GammaBasedBetaEnd(2, 1));
            System.out.println();
        }
    }
}
