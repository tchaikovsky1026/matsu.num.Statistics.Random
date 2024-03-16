package matsu.num.statistics.random.service.cauthy;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.CauthyRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;

/**
 * {@link ZiggCauthyRndFactory}クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class ZiggCauthyRndFactoryTest {

    public static final Class<?> TEST_CLASS = ZiggCauthyRndFactory.class;
    private static final CauthyRnd.Factory FACTORY =
            new ZiggCauthyRndFactory(RandomGeneratorFactoryProvider.byDefaultLib());

    public static class 乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedCauthy(BaseRandom.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        private static final class TestedCauthy implements TestedFloatingRandomGenerator {

            private final BaseRandom random;
            private final CauthyRnd cauthyRnd = FACTORY.instance();

            public TestedCauthy(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
            }

            @Override
            public double newValue() {
                return cauthyRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 0.5 + Math.atan(arg) / Math.PI;
            }

        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instance());
            System.out.println(FACTORY.instance().asTDistributionRnd());
            System.out.println();
        }
    }

}
