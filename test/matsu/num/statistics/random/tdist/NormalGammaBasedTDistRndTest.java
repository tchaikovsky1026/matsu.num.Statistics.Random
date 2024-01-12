package matsu.num.statistics.random.tdist;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link NormalGammaBasedTDistRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class NormalGammaBasedTDistRndTest {

    public static final Class<?> TEST_CLASS = NormalGammaBasedTDistRnd.class;

    public static class 自由度2のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedAt2(Random.threadSeparatedRandom()));
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
        private static final class TestedAt2 implements TestedFloatingRandomGenerator {

            private final Random random;
            private final TDistributionRnd tRnd;

            public TestedAt2(Random random) {
                this.random = Objects.requireNonNull(random);
                this.tRnd = new NormalGammaBasedTDistRnd(2);
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

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(new NormalGammaBasedTDistRnd(2));
            System.out.println();
        }
    }
}
