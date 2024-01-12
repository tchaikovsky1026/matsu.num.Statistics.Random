package matsu.num.statistics.random.norm;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link ZiggNormRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class ZiggNormRndTest {

    public static final Class<?> TEST_CLASS = ZiggNormRnd.class;

    public static class 正規乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedNormal(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * 正規乱数発生器の変則型のテスタ. <br>
         * 正規分布は累積分布関数の計算が厄介であるので, カイ二乗分布に帰着させる. <br>
         * <br>
         * X,Yが標準正規分布に従うとき, {@literal  Z = X^2 + Y^2}は自由度2のカイ二乗分布に従う. <br>
         * 符号テストも行いたいので, <br>
         * {@literal Z = sig(XY) * (X^2 + Y^2)} <br>
         * とする. <br>
         * Zの累積分布関数は, <br>
         * {@literal (1/2)exp(-|z|/2) (z < 0)} <br>
         * {@literal 1 - (1/2)exp(-|z|/2) (z > 0)} <br>
         */
        private static final class TestedNormal implements TestedFloatingRandomGenerator {

            private final Random random;
            private final NormalRnd normalRnd = new ZiggNormRnd();

            public TestedNormal(Random random) {
                this.random = Objects.requireNonNull(random);
            }

            @Override
            public double newValue() {
                double x = normalRnd.nextRandom(random);
                double y = normalRnd.nextRandom(random);

                return Math.signum(x * y) * (x * x + y * y);
            }

            @Override
            public double cumulativeProbability(double arg) {
                if (arg < 0) {
                    return 0.5 * Math.exp(-Math.abs(arg) * 0.5);
                }
                return 1 - 0.5 * Math.exp(-Math.abs(arg) * 0.5);
            }

        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(new ZiggNormRnd());
            System.out.println();
        }
    }
}
