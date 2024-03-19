package matsu.num.statistics.random.gumbel;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * {@link UniZiggGumbelRndFactory}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
final class UniZiggGumbelRndFactoryTest {

    public static final Class<?> TEST_CLASS = UniZiggGumbelRndFactory.class;
    private static final GumbelRnd.Factory FACTORY =
            new UniZiggGumbelRndFactory(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY);

    public static class Gumbel乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedGumbel(BaseRandom.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        private static final class TestedGumbel implements TestedFloatingRandomGenerator {

            private final BaseRandom random;
            private final GumbelRnd gumbelRnd = FACTORY.instance();

            public TestedGumbel(BaseRandom random) {
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
            System.out.println(FACTORY);
            System.out.println(FACTORY.instance());
            System.out.println();
        }
    }

}
