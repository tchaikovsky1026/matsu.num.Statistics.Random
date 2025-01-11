/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * {@link BetaBasedIntegerRndTesting}クラスのテスト.
 *
 * @author Matsuura Y.
 * @version 9.0
 */
@RunWith(Enclosed.class)
public class BetaBasedIntegerRndTestingTest {

    public static class 離散一様分布を用いたフレームワークテスト {

        private IntegerRandomGeneratorTestingFramework frameWork;

        @Before
        public void before() {
            frameWork = new BetaBasedIntegerRndTesting(new UniformRandomGenerator_100(ThreadLocalRandom.current()));

        }

        @Test
        public void test() {
            frameWork.test();
        }
    }

    private final static class UniformRandomGenerator_100 implements TestedIntegerRandomGenerator {

        private final Random random;

        public UniformRandomGenerator_100(Random random) {
            this.random = Objects.requireNonNull(random);
        }

        @Override
        public int newValue() {
            return random.nextInt(100);
        }

        @Override
        public double cumulativeProbability(int arg) {
            return (arg + 1) * 0.01;
        }

        @Override
        public double cumulativeProbabilityOneBelow(int arg) {
            return arg * 0.01;
        }

    }
}
