package matsu.num.statistics.random.tdist.normgamma;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link ByNormGaTRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class ByNormGaTRndTest {

    public static class 自由度2のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedGeneratorAt2(ThreadLocalRandom.current()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * nu=2のt乱数発生器のテスタ. <br>
         * 累積分布関数は, <br>
         * (1/2)[1 + t/sqrt(2 + t^2)]
         *
         */
        private static final class TestedGeneratorAt2 implements TestedFloatingRandomGenerator {

            private final Random random;
            private final TDistributionRnd tRnd;

            public TestedGeneratorAt2(Random random) {
                this.random = Objects.requireNonNull(random);
                this.tRnd = ByNormGaTRnd.instanceOf(2);
            }

            @Override
            public double newValue() {
                return tRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 0.5 * (1 + arg / Math.sqrt(2 + arg * arg));
            }

        }
    }
}
