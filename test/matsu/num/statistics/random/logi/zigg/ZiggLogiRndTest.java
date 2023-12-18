package matsu.num.statistics.random.logi.zigg;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link ZiggLogiRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
public class ZiggLogiRndTest {

    private FloatingRandomGeneratorTestingFramework framework;

    @Before
    public void before() {
        framework = FloatingRandomGeneratorTestingFramework
                .instanceOf(new TestedLogisticRandomGenerator(ThreadLocalRandom.current()));
    }

    @Test
    public void test() {
        framework.test();
    }

    private static final class TestedLogisticRandomGenerator implements TestedFloatingRandomGenerator {

        private final Random random;

        public TestedLogisticRandomGenerator(Random random) {
            this.random = Objects.requireNonNull(random);
        }

        @Override
        public double newValue() {
            return ZiggLogiRnd.instance().nextRandom(random);
        }

        @Override
        public double cumulativeProbability(double arg) {
            return 1 / (1 + Math.exp(-arg));
        }

    }
}
