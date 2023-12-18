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
 * {@link GaRndAtOver1}クラスのテスト.
 *  
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class GaRndAtOver1Test {

    public static class 形状パラメータ4のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedGeneratorAtHigh(ThreadLocalRandom.current()));
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
        private static final class TestedGeneratorAtHigh implements TestedFloatingRandomGenerator {

            private final Random random;
            private final GammaRnd gammaRnd;

            public TestedGeneratorAtHigh(Random random) {
                this.random = Objects.requireNonNull(random);
                this.gammaRnd = GaRndAtOver1.instanceOf(4);
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

}
