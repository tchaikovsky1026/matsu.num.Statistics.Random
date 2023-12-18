package matsu.num.statistics.random.norm.zigg;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link ZiggNormRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
public class ZiggNormRndTest {

    private FloatingRandomGeneratorTestingFramework framework;

    @Before
    public void before() {
        framework = FloatingRandomGeneratorTestingFramework
                .instanceOf(new TestedNormalRndModifiedGenerator(ThreadLocalRandom.current()));
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
     * 符号テストも行いたいので, <br> {@literal Z = sig(XY) * (X^2 + Y^2)} <br>
     * とする. <br>
     * Zの累積分布関数は, <br> {@literal (1/2)exp(-|z|/2) (z < 0)} <br>
     * {@literal 1 - (1/2)exp(-|z|/2) (z > 0)} <br>
     */
    private static final class TestedNormalRndModifiedGenerator implements TestedFloatingRandomGenerator {

        private final Random random;

        public TestedNormalRndModifiedGenerator(Random random) {
            this.random = Objects.requireNonNull(random);
        }

        @Override
        public double newValue() {
            double x = ZiggNormRnd.instance().nextRandom(random);
            double y = ZiggNormRnd.instance().nextRandom(random);

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
