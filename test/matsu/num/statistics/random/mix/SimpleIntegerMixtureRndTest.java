/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.mix;

import java.util.List;
import java.util.function.ToIntFunction;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BoundIntRnd;
import matsu.num.statistics.random.IntegerRandomGeneratorTestingFramework;
import matsu.num.statistics.random.cat.CategoricalFactoryForTesting;

/**
 * {@link SimpleIntegerMixtureRnd} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class SimpleIntegerMixtureRndTest {

    public static final Class<?> TEST_CLASS = SimpleIntegerMixtureRnd.class;

    public static class 乱数のテスト {

        @Test
        public void test() {
            IntegerRandomGeneratorTestingFramework
                    .instanceOf(new TestedCategoricalBasedIntegerFloatingMixtureRnd())
                    .test();
        }
    }

    public static class toString表示 {

        private IntegerMixtureRnd mixtureRnd;

        @Before
        public void before_混合分布の用意() {

            BoundIntRnd selector = CategoricalFactoryForTesting.FACTORY.instanceOf(
                    new double[] { 1, 1 });

            List<ToIntFunction<BaseRandom>> components = List.of(
                    br -> br.nextInt(),
                    br -> br.nextInt() + 1);

            this.mixtureRnd = SimpleIntegerMixtureRnd.createFrom(selector, components);
        }

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(mixtureRnd);
            System.out.println();
        }
    }

}
