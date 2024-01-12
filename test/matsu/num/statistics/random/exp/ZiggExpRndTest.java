package matsu.num.statistics.random.exp;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link ZiggExpRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class ZiggExpRndTest {

    public static final Class<?> TEST_CLASS = ZiggExpRnd.class;

    public static class 指数乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedExp(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        private static final class TestedExp implements TestedFloatingRandomGenerator {

            private final Random random;
            private final ExponentialRnd exponentialRnd = new ZiggExpRnd();

            public TestedExp(Random random) {
                this.random = Objects.requireNonNull(random);
            }

            @Override
            public double newValue() {
                return exponentialRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 1 - Math.exp(-arg);
            }

        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(new ZiggExpRnd());
            System.out.println();
        }
    }
}
