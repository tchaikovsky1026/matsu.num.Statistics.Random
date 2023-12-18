package matsu.num.statistics.random.gumbel.zigg;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link ZiggGumbelRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
public class ZiggGumbelRndTest {

    private FloatingRandomGeneratorTestingFramework framework;

    @Before
    public void before() {
        framework = FloatingRandomGeneratorTestingFramework
                .instanceOf(new TestedGumbelRandomGenerator(ThreadLocalRandom.current()));
    }

    @Test
    public void test() {
        framework.test();
    }

    private static final class TestedGumbelRandomGenerator implements TestedFloatingRandomGenerator {

        private final Random random;

        public TestedGumbelRandomGenerator(Random random) {
            this.random = Objects.requireNonNull(random);
        }

        @Override
        public double newValue() {
            return ZiggGumbelRnd.instance().nextRandom(random);
        }

        @Override
        public double cumulativeProbability(double arg) {
            return Math.exp(-Math.exp(-arg));
        }

    }
}
