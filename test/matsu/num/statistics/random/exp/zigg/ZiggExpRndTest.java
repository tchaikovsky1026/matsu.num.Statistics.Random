package matsu.num.statistics.random.exp.zigg;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link ZiggExpRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
public class ZiggExpRndTest {

    private FloatingRandomGeneratorTestingFramework framework;

    @Before
    public void before() {
        framework = FloatingRandomGeneratorTestingFramework
                .instanceOf(new TestedExponentialRandomGenerator(ThreadLocalRandom.current()));
    }

    @Test
    public void test() {
        framework.test();
    }

    private static final class TestedExponentialRandomGenerator implements TestedFloatingRandomGenerator {

        private final Random random;

        public TestedExponentialRandomGenerator(Random random) {
            this.random = Objects.requireNonNull(random);
        }

        @Override
        public double newValue() {
            return ZiggExpRnd.instance().nextRandom(random);
        }

        @Override
        public double cumulativeProbability(double arg) {
            return 1 - Math.exp(-arg);
        }

    }
}
