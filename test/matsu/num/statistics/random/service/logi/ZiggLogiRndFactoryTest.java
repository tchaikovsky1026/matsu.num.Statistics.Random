package matsu.num.statistics.random.service.logi;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.LogisticRnd;
import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;

/**
 * {@link ZiggLogiRndFactory}クラスのテスト.
 *
 * @author Matsuura Y.
 */
public class ZiggLogiRndFactoryTest {

    public static final Class<?> TEST_CLASS = ZiggLogiRndFactory.class;
    private static final LogisticRnd.Factory FACTORY =
            new ZiggLogiRndFactory(RandomGeneratorFactoryProvider.byDefaultLib());

    public static class Logistic乱数の生成テスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedLogistic(BaseRandom.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        private static final class TestedLogistic implements TestedFloatingRandomGenerator {

            private final BaseRandom random;
            private final LogisticRnd logisticRnd = FACTORY.instance();

            public TestedLogistic(BaseRandom random) {
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
            System.out.println(FACTORY);
            System.out.println(FACTORY.instance());
            System.out.println();
        }
    }
}
