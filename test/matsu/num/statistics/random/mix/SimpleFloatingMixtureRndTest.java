/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.mix;

import java.util.List;
import java.util.function.ToDoubleFunction;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BoundIntRnd;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.cat.CategoricalFactoryForTesting;

/**
 * {@link SimpleFloatingMixtureRnd} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class SimpleFloatingMixtureRndTest {

    public static final Class<?> TEST_CLASS = SimpleFloatingMixtureRnd.class;

    public static class 乱数のテスト {

        @Test
        public void test() {
            FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedCategoricalBasedFloatingMixtureRnd())
                    .test();
        }
    }

    public static class toString表示 {

        private FloatingMixtureRnd mixtureRnd;

        @Before
        public void before_混合分布の用意() {

            BoundIntRnd selector = CategoricalFactoryForTesting.FACTORY.instanceOf(
                    new double[] { 1, 1 });

            List<ToDoubleFunction<BaseRandom>> components = List.of(
                    br -> br.nextDouble(),
                    br -> br.nextDouble() + 1d);

            this.mixtureRnd = SimpleFloatingMixtureRnd.createFrom(selector, components);
        }

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(mixtureRnd);
            System.out.println();
        }
    }
}
