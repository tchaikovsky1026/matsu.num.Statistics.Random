package matsu.num.statistics.random.logi;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.LogisticRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link ZiggLogiRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
public class ZiggLogiRndTest {

    public static final Class<?> TEST_CLASS = ZiggLogiRnd.class;

    public static class Logistic乱数の生成テスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedLogistic(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        private static final class TestedLogistic implements TestedFloatingRandomGenerator {

            private final Random random;
            private final LogisticRnd logisticRnd = new ZiggLogiRnd();

            public TestedLogistic(Random random) {
                this.random = Objects.requireNonNull(random);
            }

            @Override
            public double newValue() {
                return logisticRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 1 / (1 + Math.exp(-arg));
            }

        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(new ZiggLogiRnd());
            System.out.println();
        }
    }
}
