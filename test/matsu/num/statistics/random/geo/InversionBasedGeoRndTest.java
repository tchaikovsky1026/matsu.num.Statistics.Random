package matsu.num.statistics.random.geo;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.GeometricRnd;
import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TestedIntegerRandomGenerator;

/**
 * {@link InversionBasedGeoRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class InversionBasedGeoRndTest {

    public static final Class<?> TEST_CLASS = InversionBasedGeoRnd.class;

    public static class 成功確率1のテスト {

        private IntegerRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = IntegerRandomGeneratorTestingFramework
                    .instanceOf(new TestedAtP1(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * p=1の幾何乱数発生器の変則型のテスタ.
         */
        private static final class TestedAtP1 implements TestedIntegerRandomGenerator {

            private final Random random;
            private final GeometricRnd geoRnd;

            public TestedAtP1(Random random) {
                this.random = Objects.requireNonNull(random);
                this.geoRnd = new InversionBasedGeoRnd(1);
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
                    .instanceOf(new TestedAtP0_001(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * p=0.001の幾何乱数発生器の変則型のテスタ.
         */
        private static final class TestedAtP0_001 implements TestedIntegerRandomGenerator {

            private final Random random;
            private final GeometricRnd geoRnd;

            public TestedAtP0_001(Random random) {
                this.random = Objects.requireNonNull(random);
                this.geoRnd = new InversionBasedGeoRnd(0.001);
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

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(new InversionBasedGeoRnd(0.001));
            System.out.println();
        }
    }

}
