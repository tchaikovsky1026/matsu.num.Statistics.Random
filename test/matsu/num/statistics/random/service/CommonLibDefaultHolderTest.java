package matsu.num.statistics.random.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.lib.Exponentiation;

/**
 * {@linkplain CommonLibDefaultHolder} クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class CommonLibDefaultHolderTest {

    public static final Class<?> TEST_CLASS = CommonLibDefaultHolder.class;

    public static class Exponentiationテスト {

        private Exponentiation exponentiation;

        @Before
        public void before() {
            exponentiation = CommonLibDefaultHolder.DEFAULT_INSTANCE.exponentiation();
        }

        @Test
        public void test_exp() {
            double x_s = -10d;
            double x_e = 10d;
            double delta_x = 0.01;
            for (double x = x_s; x < x_e; x += delta_x) {
                double f = exponentiation.exp(x);
                double g = Math.exp(x);
                assertThat(f, is(closeTo(g, Math.abs(g) * 1E-12)));
            }
        }

        @Test
        public void test_expm1() {
            double x_s = -10d;
            double x_e = 10d;
            double delta_x = 0.01;
            for (double x = x_s; x < x_e; x += delta_x) {
                double f = exponentiation.expm1(x);
                double g = Math.expm1(x);
                assertThat(f, is(closeTo(g, Math.abs(g) * 1E-12)));
            }
        }

        @Test
        public void test_log() {
            double x_s = 0.01d;
            double x_e = 10d;
            double delta_x = 0.01;
            for (double x = x_s; x < x_e; x += delta_x) {
                double f = exponentiation.log(x);
                double g = Math.log(x);
                assertThat(f, is(closeTo(g, Math.abs(g) * 1E-12)));
            }
        }

        @Test
        public void test_log1p() {
            double x_s = -0.99d;
            double x_e = 10d;
            double delta_x = 0.01;
            for (double x = x_s; x < x_e; x += delta_x) {
                double f = exponentiation.log1p(x);
                double g = Math.log1p(x);
                assertThat(f, is(closeTo(g, Math.abs(g) * 1E-12)));
            }
        }
    }

}
