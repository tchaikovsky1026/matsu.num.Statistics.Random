package matsu.num.statistics.random.poi.gamma;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.PoissonRnd;
import matsu.num.statistics.random.TestedIntegerRandomGenerator;

/**
 * {@link ByGaHomoProcessBasedPoiRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class ByGaHomoProcessBasedPoiRndTest {

    public static class Lambda10のテスト {

        private IntegerRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = IntegerRandomGeneratorTestingFramework
                    .instanceOf(new TestedGeneratorAtLambda(ThreadLocalRandom.current(), 10));
        }

        @Test
        public void test() {
            framework.test();
        }

    }

    public static class Lambda100のテスト {

        private IntegerRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = IntegerRandomGeneratorTestingFramework
                    .instanceOf(new TestedGeneratorAtLambda(ThreadLocalRandom.current(), 100));
        }

        @Test
        public void test() {
            framework.test();
        }
    }

    /**
     * Poisson乱数発生器のテスタ.
     */
    private static final class TestedGeneratorAtLambda implements TestedIntegerRandomGenerator {

        private final Random random;
        private final PoissonRnd poiRnd;

        public TestedGeneratorAtLambda(Random random, double lambda) {
            this.random = Objects.requireNonNull(random);
            this.poiRnd = ByGaHomoProcessBasedPoiRnd.instanceOf(lambda);
        }

        @Override
        public int newValue() {
            return poiRnd.nextRandom(random);
        }

        @Override
        public double cumulativeProbability(int arg) {
            if (arg < 0) {
                return 0;
            }

            final double lambda = this.poiRnd.lambda();

            double sum = 0;
            double v = 1;
            for (int k = 0; k <= arg; k++) {
                sum += v;
                v *= lambda / (k + 1);
            }
            return sum * Math.exp(-lambda);
        }

        @Override
        public double cumulativeProbabilityOneBelow(int arg) {
            if (arg <= 0) {
                return 0;
            }

            final double lambda = this.poiRnd.lambda();

            double sum = 0;
            double v = 1;
            for (int k = 0; k < arg; k++) {
                sum += v;
                v *= lambda / (k + 1);
            }
            return sum * Math.exp(-lambda);
        }

    }

}
