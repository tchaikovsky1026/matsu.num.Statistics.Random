/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.cauchy;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * {@link ZiggCauchyRnd} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class ZiggCauchyRndTest {

    public static final Class<?> TEST_CLASS = ZiggCauchyRnd.class;
    private static final CauchyRnd.Factory FACTORY =
            ZiggCauchyRnd.createFactory(ExponentiationForTesting.INSTANCE);

    public static class 乱数のテスト {

        @Test
        public void test() {
            FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedCauchyRandomGenerator(FACTORY.instance()))
                    .test();
        }
    }

    public static class toString表示 {

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instance());
            System.out.println();
        }
    }

}
