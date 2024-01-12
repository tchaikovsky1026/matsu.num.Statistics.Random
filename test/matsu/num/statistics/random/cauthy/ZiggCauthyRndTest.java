package matsu.num.statistics.random.cauthy;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.CauthyRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link ZiggCauthyRnd}クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class ZiggCauthyRndTest {

    public static final Class<?> TEST_CLASS = ZiggCauthyRnd.class;

    public static class 乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedCauthy(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        private static final class TestedCauthy implements TestedFloatingRandomGenerator {

            private final Random random;
            private final CauthyRnd cauthyRnd = new ZiggCauthyRnd();

            public TestedCauthy(Random random) {
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
            System.out.println(new ZiggCauthyRnd());
            System.out.println(new ZiggCauthyRnd().asTDistributionRnd());
            System.out.println();
        }
    }

}
