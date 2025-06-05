/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.5
 */
package matsu.num.statistics.random.beta;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * a=2,b=1のベータプライム乱数発生器のテスタ. <br>
 * 累積分布関数は <br>
 * {@literal 1 - (1+2x)/(1+x)^2}
 * 
 * @author Matsuura Y.
 */
final class Tested_At_2_1_BetaPrimeRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();
    private final BetaRnd betaRnd;

    Tested_At_2_1_BetaPrimeRandomGenerator(BetaRnd.Factory factory) {
        this.betaRnd = factory.instanceOf(2d, 1d);
    }

    @Override
    public double newValue() {
        return this.betaRnd.nextBetaPrime(random);
    }

    @Override
    public double cumulativeProbability(double arg) {
        return 1 - (1 + 2 * arg) / ((1 + arg) * (1 + arg));
    }
}
