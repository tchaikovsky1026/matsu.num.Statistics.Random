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
 * {@link MTTypeGammaRndOver1}クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class MTTypeGammaRndOver1Test {

    public static final Class<?> TEST_CLASS = MTTypeGammaRndOver1.class;

    public static class 形状パラメータ4のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedAt4(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * k=4のガンマ乱数発生器の変則型のテスタ. <br>
         * 累積分布関数は, <br>
         * 1 - e^(-x) (1 + x + (1/2) * x^2 + (1/6)x^3)
         *
         */
        private static final class TestedAt4 implements TestedFloatingRandomGenerator {

            private final Random random;
            private final GammaRnd gammaRnd;

            public TestedAt4(Random random) {
                this.random = Objects.requireNonNull(random);
                this.gammaRnd = new MTTypeGammaRndOver1(4);
            }

            @Override
            public double newValue() {
                return gammaRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                double arg2 = arg * arg;
                double arg3 = arg2 * arg;
                return 1 - Math.exp(-arg) * (1 + arg + 0.5 * arg2 + (1.0 / 6.0) * arg3);
            }

        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(new MTTypeGammaRndOver1(4));
            System.out.println();
        }
    }

}
