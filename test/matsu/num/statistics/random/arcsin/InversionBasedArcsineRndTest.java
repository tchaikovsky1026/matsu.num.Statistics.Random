/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.31
 */
package matsu.num.statistics.random.arcsin;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.ArcsineRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;

/**
 * {@link InversionBasedArcsineRnd} クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
final class InversionBasedArcsineRndTest {

    public static final Class<?> TEST_CLASS = InversionBasedArcsineRnd.class;

    private static final ArcsineRnd.Factory FACTORY =
            InversionBasedArcsineRnd.createFactory();

    public static class 乱数テスト {

        @Test
        public void test() {

            FloatingRandomGeneratorTestingFramework framework =
                    FloatingRandomGeneratorTestingFramework.instanceOf(
                            new TestedArcsineRandomGenerator(FACTORY.instance()));
            framework.test();
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
