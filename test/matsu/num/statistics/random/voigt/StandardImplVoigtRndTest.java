package matsu.num.statistics.random.voigt;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.None;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.VoigtRnd;

/**
 * {@link StandardImplVoigtRnd}クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class StandardImplVoigtRndTest {

    public static final Class<?> TEST_CLASS = StandardImplVoigtRnd.class;

    public static class 生成とパラメータ取得のテスト {

        @Test(expected = IllegalArgumentException.class)
        public void test_0未満は不正() {
            new StandardImplVoigtRnd(-Double.MIN_VALUE);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test_1超過は不正() {
            new StandardImplVoigtRnd(Math.nextUp(1d));
        }

        @Test(expected = None.class)
        public void test_0は正常() {
            new StandardImplVoigtRnd(-0d);
        }

        @Test(expected = None.class)
        public void test_1は正常() {
            new StandardImplVoigtRnd(1d);
        }

        @Test
        public void test_パラメータ取得() {
            double alpha = 0.2;
            VoigtRnd voigtRnd = new StandardImplVoigtRnd(alpha);
            assertThat(voigtRnd.alpha(), is(alpha));
        }
    }

    public static class alpha0のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedAlpha0(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * <p>
         * alpha = 0に関するテスタ: 標準正規分布. <br>
         * カイ二乗分布に帰着させる.
         * </p>
         * 
         * <p>
         * X,Yが標準正規分布に従うとき, {@literal  Z = X^2 + Y^2}は自由度2のカイ二乗分布に従う. <br>
         * 符号テストも行いたいので, <br>
         * {@literal Z = sig(XY) * (X^2 + Y^2)} <br>
         * とする. <br>
         * Zの累積分布関数は, <br>
         * {@literal (1/2)exp(-|z|/2) (z < 0)} <br>
         * {@literal 1 - (1/2)exp(-|z|/2) (z > 0)} <br>
         * </p>
         */
        private static final class TestedAlpha0 implements TestedFloatingRandomGenerator {

            private final Random random;
            private final VoigtRnd voigtRnd;

            public TestedAlpha0(Random random) {
                this.random = Objects.requireNonNull(random);
                this.voigtRnd = new StandardImplVoigtRnd(0d);
            }

            @Override
            public double newValue() {
                double x = voigtRnd.nextRandom(random);
                double y = voigtRnd.nextRandom(random);

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

    public static class alpha1のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedAlpha1(Random.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * <p>
         * alpha = 1に関するテスタ: コーシー分布.
         * </p>
         */
        private static final class TestedAlpha1 implements TestedFloatingRandomGenerator {

            private final Random random;
            private final VoigtRnd voigtRnd;

            public TestedAlpha1(Random random) {
                this.random = Objects.requireNonNull(random);
                this.voigtRnd = new StandardImplVoigtRnd(1d);
            }

            @Override
            public double newValue() {
                return voigtRnd.nextRandom(random);
            }

            @Override
            public double cumulativeProbability(double arg) {
                return 0.5 + Math.atan(arg) / Math.PI;
            }

        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(new StandardImplVoigtRnd(1d));
            System.out.println();
        }
    }
}
