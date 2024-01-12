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
 * {@link MTTypeGammaRndUnder1}クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class MTTypeGammaRndUnder1Test {

    public static final Class<?> TEST_CLASS = MTTypeGammaRndUnder1.class;

    public static class 形状パラメータ0_25のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedAtQuarter(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * k=0.25のガンマ乱数発生器の変則型のテスタ. <br>
         * 指数分布に帰着させる. <br>
         * <br>
         * X1からX4がk=0.25ガンマ分布に従うとき,
         * {@literal  Z = X1 + X2 + X3 + X4}は標準指数分布に従う.
         */
        private static final class TestedAtQuarter implements TestedFloatingRandomGenerator {

            private final Random random;
            private final GammaRnd gammaRnd;

            public TestedAtQuarter(Random random) {
                this.random = Objects.requireNonNull(random);
                this.gammaRnd = new MTTypeGammaRndUnder1(0.25);
            }

            @Override
            public double newValue() {
                return gammaRnd.nextRandom(random) + gammaRnd.nextRandom(random) + gammaRnd.nextRandom(random)
                        + gammaRnd.nextRandom(random);
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
            System.out.println(new MTTypeGammaRndUnder1(0.25));
            System.out.println();
        }
    }

}
