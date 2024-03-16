package matsu.num.statistics.random.service.exp;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;

/**
 * {@link ZiggExponentialRndFactory}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class ZiggExponentialRndFactoryTest {

    public static final Class<?> TEST_CLASS = ZiggExponentialRndFactory.class;
    private static final ExponentialRnd.Factory FACTORY =
            new ZiggExponentialRndFactory(RandomGeneratorFactoryProvider.byDefaultLib());

    public static class 指数乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedExp(BaseRandom.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        private static final class TestedExp implements TestedFloatingRandomGenerator {

            private final BaseRandom random;
            private final ExponentialRnd exponentialRnd = FACTORY.instance();

            public TestedExp(BaseRandom random) {
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
            System.out.println(FACTORY);
            System.out.println(FACTORY.instance());
            System.out.println();
        }
    }
}
