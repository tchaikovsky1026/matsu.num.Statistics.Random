package matsu.num.statistics.random.fdist;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FDistributionRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link BetaBasedFDistRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class BetaBasedFDistRndTest {

    public static final Class<?> TEST_CLASS = BetaBasedFDistRnd.class;

    public static class 自由度_分子4_分母2のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedAt4and2(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * d1=4,d2=2のF乱数発生器のテスタ. <br>
         * 累積分布関数は {@literal 1 - (x + (1/4))/(x + (1/2))^2}.
         */
        private static final class TestedAt4and2 implements TestedFloatingRandomGenerator {

            private final Random random;
            private final FDistributionRnd fRnd;

            public TestedAt4and2(Random random) {
                this.random = Objects.requireNonNull(random);
                this.fRnd = new BetaBasedFDistRnd(4, 2);
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

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(new BetaBasedFDistRnd(4, 2));
            System.out.println();
        }
    }
}
