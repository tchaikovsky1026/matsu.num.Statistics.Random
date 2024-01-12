package matsu.num.statistics.random.weibull;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.WeibullRnd;

/**
 * {@link GumbelBasedWeibullRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class GumbelBasedWeibullRndTest {

    public static final Class<?> TEST_CLASS = GumbelBasedWeibullRnd.class;

    public static class パラメータ2のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            double k = 2;
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedWeibull(Random.threadSeparatedRandom(), k));
        }

        @Test
        public void test() {
            framework.test();
        }
    }

    private static final class TestedWeibull implements TestedFloatingRandomGenerator {

        private final Random random;
        private final WeibullRnd weibullRnd;

        public TestedWeibull(Random random, double k) {
            this.random = Objects.requireNonNull(random);
            this.weibullRnd = new GumbelBasedWeibullRnd(k);
        }

        @Override
        public double newValue() {
            return weibullRnd.nextRandom(random);
        }

        @Override
        public double cumulativeProbability(double arg) {
            double k = weibullRnd.shapeParameter();
            return 1 - Math.exp(-Math.exp(k * Math.log(arg)));
        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(new GumbelBasedWeibullRnd(2));
            System.out.println();
        }
    }

}
