/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.levy;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.FloatingRandomGeneratorTestingFramework;
import matsu.num.statistics.random.LevyRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.norm.NormalFactoryForTesting;

/**
 * {@link NormalBasedLevyRnd}クラスのテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
final class NormalBasedLevyRndTest {

    public static final Class<?> TEST_CLASS = NormalBasedLevyRnd.class;
    private static final LevyRnd.Factory FACTORY =
            NormalBasedLevyRnd.createFactory(NormalFactoryForTesting.FACTORY);

    public static class Levy乱数のテスト {

        private FloatingRandomGeneratorTestingFramework framework;

        @Before
        public void before() {
            framework = FloatingRandomGeneratorTestingFramework
                    .instanceOf(new TestedLevy(BaseRandom.threadSeparatedRandom()));
        }

        @Test
        public void test() {
            framework.test();
        }

        /**
         * 標準Levy乱数発生器の変則型のテスタ. <br>
         * 標準Levy分布は累積分布関数の計算が厄介であるので, ガンマ分布に帰着させる.
         * 
         * <p>
         * 標準Levy乱数はInvGamma(1/2,1/2),
         * すなわち, X &sim; SLevy のとき,
         * 1/(2X) &sim; SGamma(1/2)
         * となる. <br>
         * したがって, X &sim; SLevy, Y &sim; SLevy とすれば,
         * Z = 1/(2X) + 1/(2Y) &sim; SGamma(1)
         * となる.
         * </p>
         */
        private static final class TestedLevy implements TestedFloatingRandomGenerator {

            private final BaseRandom random;
            private final LevyRnd levyRnd;

            public TestedLevy(BaseRandom random) {
                this.random = Objects.requireNonNull(random);
                this.levyRnd = FACTORY.instance();
            }

            @Override
            public double newValue() {
                double x = this.levyRnd.nextRandom(random);
                double y = this.levyRnd.nextRandom(random);

                return 0.5 / x + 0.5 / y;
            }

            @Override
            public double cumulativeProbability(double arg) {
                if (arg < 0) {
                    return 0;
                }
                return 1 - Math.exp(-arg);
            }

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
