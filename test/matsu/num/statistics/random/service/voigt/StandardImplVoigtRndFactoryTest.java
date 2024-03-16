package matsu.num.statistics.random.service.voigt;

import static matsu.num.statistics.random.VoigtRnd.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.None;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.VoigtRnd;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;

/**
 * {@link StandardImplVoigtRndFactory}クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class StandardImplVoigtRndFactoryTest {

    public static final Class<?> TEST_CLASS = StandardImplVoigtRndFactory.class;
    private static final VoigtRnd.Factory FACTORY =
            new StandardImplVoigtRndFactory(RandomGeneratorFactoryProvider.byDefaultLib());

    public static class ファクトリの境界値テスト {

        @Test(expected = None.class)
        public void test_境界内最小値() {
            assertThat(FACTORY.acceptsParameter(LOWER_LIMIT_ALPHA), is(true));
            FACTORY.instanceOf(LOWER_LIMIT_ALPHA);
        }

        @Test(expected = None.class)
        public void test_境界内最大値() {
            assertThat(FACTORY.acceptsParameter(UPPER_LIMIT_ALPHA), is(true));
            FACTORY.instanceOf(UPPER_LIMIT_ALPHA);
        }

        @Test
        public void test_境界最小値外() {
            assertThat(FACTORY.acceptsParameter(Math.nextDown(LOWER_LIMIT_ALPHA)), is(false));
        }

        @Test
        public void test_境界最大値外() {
            assertThat(FACTORY.acceptsParameter(Math.nextUp(UPPER_LIMIT_ALPHA)), is(false));
        }
    }
    
    public static class パラメータ取得のテスト {

        @Test
        public void test_パラメータ取得() {
            double alpha = 0.2;
            VoigtRnd voigtRnd = FACTORY.instanceOf(alpha);
            assertThat(voigtRnd.alpha(), is(alpha));
        }
    }

    public static class alpha0のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedAlpha0(BaseRandom.threadSeparatedRandom()));
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

            private final BaseRandom random;
            private final VoigtRnd voigtRnd;

            public TestedAlpha0(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.voigtRnd = FACTORY.instanceOf(0d);
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
                    .instanceOf(new TestedAlpha1(BaseRandom.threadSeparatedRandom()));
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

            private final BaseRandom random;
            private final VoigtRnd voigtRnd;

            public TestedAlpha1(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.voigtRnd = FACTORY.instanceOf(1d);
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
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(1d));
            System.out.println();
        }
    }
}