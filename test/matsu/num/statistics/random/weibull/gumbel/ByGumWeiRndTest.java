package matsu.num.statistics.random.weibull.gumbel;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.WeibullRnd;

/**
 * {@link ByGumWeiRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class ByGumWeiRndTest {

    public static class パラメータ2のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            double k = 2;
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedWeibullRandomGenerator(ThreadLocalRandom.current(), k));
        }

        @Test
        public void test() {
            framework.test();
        }
    }

    private static final class TestedWeibullRandomGenerator implements TestedFloatingRandomGenerator {

        private final Random random;
        private final WeibullRnd weibullRnd;

        public TestedWeibullRandomGenerator(Random random, double k) {
            this.random = Objects.requireNonNull(random);
            this.weibullRnd = ByGumWeiRnd.instanceOf(k);
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

}
