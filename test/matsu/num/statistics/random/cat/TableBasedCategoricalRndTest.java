package matsu.num.statistics.random.cat;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.CategoricalRnd;
import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TestedIntegerRandomGenerator;

/**
 * {@link TableBasedCategoricalRnd}クラスのテスト.
 *
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public final class TableBasedCategoricalRndTest {

    public static final Class<?> TEST_CLASS = TableBasedCategoricalRnd.class;

    public static class カテゴリ数が1の場合のテスト {

        @Test
        public void test_カテゴリ数1() {
            CategoricalRnd catRnd = TableBasedCategoricalRnd.instanceOfExp(new double[] { 0d });

            for (int i = 0; i < 100; i++) {
                catRnd.nextRandom(Random.threadSeparatedRandom());
            }
        }
    }

    public static class カテゴリの確率がkPlus1に比例する場合でのテスト_カテゴリ数10 {

        private IntegerRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = IntegerRandomGeneratorTestingFramework
                    .instanceOf(new TestedAtN10(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * k=0,...,9について, カテゴリ確率が(k+1)に比例するようなカテゴリカル分布のテスタ.
         */
        private static final class TestedAtN10 implements TestedIntegerRandomGenerator {

            private final Random random;
            private final CategoricalRnd catRnd;

            public TestedAtN10(Random random) {
                this.random = Objects.requireNonNull(random);

                double[] p = {
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10
                };

                this.catRnd = TableBasedCategoricalRnd.instanceOf(p);
            }

            @Override
            public int newValue() {
                return catRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(int arg) {
                if (arg < 0 || arg >= 10) {
                    return 0;
                }
                return ((double) (arg + 1) * (arg + 2)) / 110;
            }

            @Override
            public double cumulativeProbabilityOneBelow(int arg) {
                if (arg <= 0 || arg >= 10) {
                    return 0;
                }
                return ((double) (arg) * (arg + 1)) / 110;
            }

        }
    }

    public static class カテゴリの確率がkPlus1に比例する場合でのテスト_instanceOfExp_カテゴリ数10 {

        private IntegerRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = IntegerRandomGeneratorTestingFramework
                    .instanceOf(new TestedAtN10(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * k=0,...,9について, カテゴリ確率が(k+1)に比例するようなカテゴリカル分布のテスタ.
         */
        private static final class TestedAtN10 implements TestedIntegerRandomGenerator {

            private final Random random;
            private final CategoricalRnd catRnd;

            public TestedAtN10(Random random) {
                this.random = Objects.requireNonNull(random);

                double[] logP = {
                        0.0000000000000000E+00,
                        6.9314718055994500E-01,
                        1.0986122886681100E+00,
                        1.3862943611198900E+00,
                        1.6094379124341000E+00,
                        1.7917594692280500E+00,
                        1.9459101490553100E+00,
                        2.0794415416798400E+00,
                        2.1972245773362200E+00,
                        2.3025850929940500E+00
                };

                this.catRnd = TableBasedCategoricalRnd.instanceOfExp(logP);
            }

            @Override
            public int newValue() {
                return catRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(int arg) {
                if (arg < 0 || arg >= 10) {
                    return 0;
                }
                return ((double) (arg + 1) * (arg + 2)) / 110;
            }

            @Override
            public double cumulativeProbabilityOneBelow(int arg) {
                if (arg <= 0 || arg >= 10) {
                    return 0;
                }
                return ((double) (arg) * (arg + 1)) / 110;
            }

        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(TableBasedCategoricalRnd.instanceOf(new double[] { 1d, 1d }));
            System.out.println();
        }
    }
}
