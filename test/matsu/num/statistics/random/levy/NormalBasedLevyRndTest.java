/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.levy;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.LevyRnd;
import matsu.num.statistics.random.norm.NormalFactoryForTesting;

/**
 * {@link NormalBasedLevyRnd} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class NormalBasedLevyRndTest {

    public static final Class<?> TEST_CLASS = NormalBasedLevyRnd.class;
    private static final LevyRnd.Factory FACTORY =
            NormalBasedLevyRnd.createFactory(NormalFactoryForTesting.FACTORY);

    public static class 乱数のテスト {

        @Test
        public void test() {
            FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedLevyRandomGenerator(FACTORY.instance()))
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
