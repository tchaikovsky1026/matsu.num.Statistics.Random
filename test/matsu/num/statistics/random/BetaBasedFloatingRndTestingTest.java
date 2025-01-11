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
 * {@link BetaBasedFloatingRndTesting}のテスト.
 *
 * @author Matsuura Y.
 * @version 9.0
 */
@RunWith(Enclosed.class)
public class BetaBasedFloatingRndTestingTest {

    public static class 一様分布を用いたフレームワークテスト {

        private FloatingRandomGeneratorTestingFramework frameWork;

        @Before
        public void before() {
            frameWork = new BetaBasedFloatingRndTesting(new UniformRandomGenerator(ThreadLocalRandom.current()));

        }

        @Test
        public void test() {
            frameWork.test();
        }
    }

    private final static class UniformRandomGenerator implements TestedFloatingRandomGenerator {

        private final Random random;

        public UniformRandomGenerator(Random random) {
            this.random = Objects.requireNonNull(random);
        }

        @Override
        public double newValue() {
            return random.nextDouble();
        }

        @Override
        public double cumulativeProbability(double arg) {
            return arg;
        }

    }
}
