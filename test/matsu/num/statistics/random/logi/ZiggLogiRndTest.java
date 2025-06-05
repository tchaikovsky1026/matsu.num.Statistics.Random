/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.logi;

import org.junit.Test;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.LogisticRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * {@link ZiggLogiRnd} クラスのテスト.
 */
final class ZiggLogiRndTest {

    public static final Class<?> TEST_CLASS = ZiggLogiRnd.class;
    private static final LogisticRnd.Factory FACTORY =
            ZiggLogiRnd.createFactory(ExponentiationForTesting.INSTANCE, ExponentialFactoryForTesting.FACTORY);

    public static class Logistic乱数の生成テスト {

        @Test
        public void test() {
            FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedLogisticRandomGenerator(FACTORY.instance()))
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
