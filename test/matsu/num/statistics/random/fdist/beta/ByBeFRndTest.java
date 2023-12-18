package matsu.num.statistics.random.fdist.beta;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FDistributionRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link ByBeFRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class ByBeFRndTest {

    public static class 自由度_分子4_分母2のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework.instanceOf(new TestedF(ThreadLocalRandom.current()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * d1=4,d2=2のF乱数発生器のテスタ. <br>
         * 累積分布関数は {@literal 1 - (x + (1/4))/(x + (1/2))^2}.
         */
        private static final class TestedF implements TestedFloatingRandomGenerator {

            private final Random random;
            private final FDistributionRnd fRnd;

            public TestedF(Random random) {
                this.random = Objects.requireNonNull(random);
                this.fRnd = ByBeFRnd.instanceOf(4, 2);
            }

            @Override
            public double newValue() {
                return fRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 1 - (arg + 0.25) / ((arg + 0.5) * (arg + 0.5));
            }

        }
    }
}
