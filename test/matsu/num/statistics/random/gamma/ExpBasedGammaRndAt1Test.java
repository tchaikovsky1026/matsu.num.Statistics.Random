package matsu.num.statistics.random.gamma;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link ExpBasedGammaRndAt1}クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class ExpBasedGammaRndAt1Test {

    public static final Class<?> TEST_CLASS = ExpBasedGammaRndAt1.class;

    public static class 形状パラメータ1のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedAt1(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * k=1のガンマ乱数発生器のテスタ=標準指数分布.
         */
        private static final class TestedAt1 implements TestedFloatingRandomGenerator {

            private final Random random;
            private final GammaRnd gammaRnd;

            public TestedAt1(Random random) {
                this.random = Objects.requireNonNull(random);
                this.gammaRnd = new ExpBasedGammaRndAt1();
            }

            @Override
            public double newValue() {
                return gammaRnd.nextRandom(random);
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
            System.out.println(new ExpBasedGammaRndAt1());
            System.out.println();
        }
    }
}
