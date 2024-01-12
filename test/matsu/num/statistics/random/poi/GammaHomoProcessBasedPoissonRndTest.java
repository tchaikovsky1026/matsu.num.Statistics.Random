package matsu.num.statistics.random.poi;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.PoissonRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TestedIntegerRandomGenerator;

/**
 * {@link GammaHomoProcessBasedPoissonRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class GammaHomoProcessBasedPoissonRndTest {

    public static final Class<?> TEST_CLASS = GammaHomoProcessBasedPoissonRnd.class;

    public static class Lambda10のテスト {

        private IntegerRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = IntegerRandomGeneratorTestingFramework
                    .instanceOf(new TestedAt100(Random.threadSeparatedRandom(), 10));
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
                    .instanceOf(new TestedAt100(Random.threadSeparatedRandom(), 100));
        }

        @Test
        public void test() {
            framework.test();
        }
    }

    /**
     * Poisson乱数発生器のテスタ.
     */
    private static final class TestedAt100 implements TestedIntegerRandomGenerator {

        private final Random random;
        private final PoissonRnd poiRnd;

        public TestedAt100(Random random, double lambda) {
            this.random = Objects.requireNonNull(random);
            this.poiRnd = new GammaHomoProcessBasedPoissonRnd(lambda);
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

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(new GammaHomoProcessBasedPoissonRnd(100));
            System.out.println();
        }
    }

}
