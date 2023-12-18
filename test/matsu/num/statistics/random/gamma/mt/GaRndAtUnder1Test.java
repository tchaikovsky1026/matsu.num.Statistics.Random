package matsu.num.statistics.random.gamma.mt;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * {@link GaRndAtUnder1}クラスのテスト.
 *  
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class GaRndAtUnder1Test {

    public static class 形状パラメータ0_25のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedGeneratorAtLow(ThreadLocalRandom.current()));
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
        private static final class TestedGeneratorAtLow implements TestedFloatingRandomGenerator {

            private final Random random;
            private final GammaRnd gammaRnd;

            public TestedGeneratorAtLow(Random random) {
                this.random = Objects.requireNonNull(random);
                this.gammaRnd = GaRndAtUnder1.instanceOf(0.25);
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

}
