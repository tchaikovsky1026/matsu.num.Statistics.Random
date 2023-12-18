package matsu.num.statistics.random.geo.inv;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.GeometricRnd;
import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.TestedIntegerRandomGenerator;

/**
 * {@link InvBasedGeoRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class InvBasedGeoRndTest {

    public static class 成功確率1のテスト {

        private IntegerRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = IntegerRandomGeneratorTestingFramework
                    .instanceOf(new TestedGeneratorAtP1(ThreadLocalRandom.current()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * p=1の幾何乱数発生器の変則型のテスタ.
         */
        private static final class TestedGeneratorAtP1 implements TestedIntegerRandomGenerator {

            private final Random random;
            private final GeometricRnd geoRnd;

            public TestedGeneratorAtP1(Random random) {
                this.random = Objects.requireNonNull(random);
                this.geoRnd = InvBasedGeoRnd.instanceOf(1);
            }

            @Override
            public int newValue() {
                return geoRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(int arg) {
                if (arg <= 0) {
                    return 0;
                }
                return 1 - Math.exp(arg * Math.log1p(-geoRnd.successPobability()));
            }

            @Override
            public double cumulativeProbabilityOneBelow(int arg) {
                if (arg <= 1) {
                    return 0;
                }
                return 1 - Math.exp((arg - 1) * Math.log1p(-geoRnd.successPobability()));
            }

        }
    }

    public static class 成功確率0_001のテスト {

        private IntegerRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = IntegerRandomGeneratorTestingFramework
                    .instanceOf(new TestedGeneratorAtP0_001(ThreadLocalRandom.current()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * p=0.001の幾何乱数発生器の変則型のテスタ.
         */
        private static final class TestedGeneratorAtP0_001 implements TestedIntegerRandomGenerator {

            private final Random random;
            private final GeometricRnd geoRnd;

            public TestedGeneratorAtP0_001(Random random) {
                this.random = Objects.requireNonNull(random);
                this.geoRnd = GeometricRnd.instanceOf(0.001);
            }

            @Override
            public int newValue() {
                return geoRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(int arg) {
                if (arg <= 0) {
                    return 0;
                }
                return 1 - Math.exp(arg * Math.log1p(-geoRnd.successPobability()));
            }

            @Override
            public double cumulativeProbabilityOneBelow(int arg) {
                if (arg <= 1) {
                    return 0;
                }
                return 1 - Math.exp((arg - 1) * Math.log1p(-geoRnd.successPobability()));
            }

        }
    }

}
