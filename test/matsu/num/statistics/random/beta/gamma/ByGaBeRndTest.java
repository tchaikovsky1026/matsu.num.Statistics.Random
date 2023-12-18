package matsu.num.statistics.random.beta.gamma;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link ByGaBeRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class ByGaBeRndTest {

    public static class ベータ乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework.instanceOf(new TestedBeta(ThreadLocalRandom.current()));
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
                this.betaRnd = ByGaBeRnd.instanceOf(2, 1);
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
            framework = FloatingRandomGeneratorTestingFramework.instanceOf(new TestedBeta(ThreadLocalRandom.current()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * a=2,b=1のベータプライム乱数発生器のテスタ. <br>
         * 累積分布関数は<br> {@literal 1 - (1+2x)/(1+x)^2}
         */
        private static final class TestedBeta implements TestedFloatingRandomGenerator {

            private final Random random;
            private final BetaRnd betaRnd;

            public TestedBeta(Random random) {
                this.random = Objects.requireNonNull(random);
                this.betaRnd = ByGaBeRnd.instanceOf(2, 1);
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
}
