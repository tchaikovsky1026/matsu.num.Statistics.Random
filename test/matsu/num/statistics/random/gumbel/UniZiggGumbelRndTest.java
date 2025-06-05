/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.gumbel;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

/**
 * {@link GumbelRndFactory} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class UniZiggGumbelRndTest {

    public static final Class<?> TEST_CLASS = UniZiggGumbelRnd.class;
    private static final GumbelRnd.Factory FACTORY =
            UniZiggGumbelRnd.createFactory(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY);

    public static class 乱数のテスト {

        @Test
        public void test() {
            FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedGumbelRandomGenerator(FACTORY.instance()))
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
