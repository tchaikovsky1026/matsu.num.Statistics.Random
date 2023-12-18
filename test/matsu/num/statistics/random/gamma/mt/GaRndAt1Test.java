package matsu.num.statistics.random.gamma.mt;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link GaRndAt1}クラスのテスト.
 *  
 * @author Matsuura Y.
 */
public class GaRndAt1Test {

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
        private final GammaRnd gammaRnd;

        public TestedGeneratorAt1(Random random) {
            this.random = Objects.requireNonNull(random);
            this.gammaRnd = GaRndAt1.instance();
        }

        @Override
        public double newValue() {
            return gammaRnd.nextRandom(random);
        }

        @Override
        public double cumulativeProbability(double arg) {
            return 1 - Math.exp(-arg);
        }
    }
}
