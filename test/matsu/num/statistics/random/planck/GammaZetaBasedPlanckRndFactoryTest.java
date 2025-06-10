/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.planck;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.PlanckRnd;
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;
import matsu.num.statistics.random.zeta.ZetaRndFactoryForTesting;

/**
 * {@link GammaZetaBasedPlanckRndFactory} クラスのテスト.
 */
@RunWith(Enclosed.class)
final class GammaZetaBasedPlanckRndFactoryTest {

    public static final Class<?> TEST_CLASS = GammaZetaBasedPlanckRndFactory.class;
    private static final PlanckRnd.Factory FACTORY =
            GammaZetaBasedPlanckRndFactory.create(
                    GammaFactoryForTesting.FACTORY,
                    ZetaRndFactoryForTesting.FACTORY);

    public static class toString表示 {

        // 生成に関するテストを兼ねる

        @Test
        public void test_toString() {
            System.out.println(TEST_CLASS.getName());
            System.out.println(FACTORY);
            System.out.println(FACTORY.instanceOf(0.25));
            System.out.println(FACTORY.instanceOf(101));
            System.out.println();
        }
    }
}
