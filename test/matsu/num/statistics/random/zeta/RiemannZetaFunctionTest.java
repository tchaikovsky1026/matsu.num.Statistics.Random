/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.zeta;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * {@link RiemannZetaFunction} のテスト.
 */
@RunWith(Enclosed.class)
final class RiemannZetaFunctionTest {

    @RunWith(Theories.class)
    public static class ゼータ関数の値のパラメータテスト {

        @DataPoints
        public static double[][] data = {
                { 1.1, 10.58444846495080982639 },
                { 2.0, 1.64493406684822643647 },
                { 4.0, 1.08232323371113819151 },
                { 10, 1.000994575127818085337 },
                { 14.9, 1.000032786826059689914 },
                { 15.1, 1.000028537188737446409 },
                { 30, 1.00000000093132743242 }
        };

        @Theory
        public void test(double[] values) {
            assertThat(
                    RiemannZetaFunction.at(values[0]),
                    is(closeTo(values[1], (values[1] + 1d) * 1E-14)));
        }
    }

    public static class ゼータ関数の値の特殊値のテスト {

        @Test
        public void test_1なら正の無限大() {
            assertThat(RiemannZetaFunction.at(1d), is(Double.POSITIVE_INFINITY));
        }

        @Test
        public void test_正の無限大なら1() {
            assertThat(RiemannZetaFunction.at(Double.POSITIVE_INFINITY), is(1d));
        }
    }
}
