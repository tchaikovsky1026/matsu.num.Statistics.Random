package matsu.num.statistics.random.gumbel;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link UniZiggGumbelRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class UniZiggGumbelRndTest {

    public static final Class<?> TEST_CLASS = UniZiggGumbelRnd.class;

    public static class Gumbel乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedGumbel(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        private static final class TestedGumbel implements TestedFloatingRandomGenerator {

            private final Random random;
            private final GumbelRnd gumbelRnd = new UniZiggGumbelRnd();

            public TestedGumbel(Random random) {
                this.random = Objects.requireNonNull(random);
            }

            @Override
            public double newValue() {
                return gumbelRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return Math.exp(-Math.exp(-arg));
            }

        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(new UniZiggGumbelRnd());
            System.out.println();
        }
    }

}
