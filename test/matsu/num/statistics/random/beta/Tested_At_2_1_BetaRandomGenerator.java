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
 * a=2,b=1のベータ乱数発生器のテスタ. <br>
 * 累積分布関数はx^2
 * 
 * @author Matsuura Y.
 */
final class Tested_At_2_1_BetaRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();
    private final BetaRnd betaRnd;

    Tested_At_2_1_BetaRandomGenerator(BetaRnd.Factory factory) {
        this.betaRnd = factory.instanceOf(2d, 1d);
    }

    @Override
    public double newValue() {
        return this.betaRnd.nextRandom(random);
    }

    @Override
    public double cumulativeProbability(double arg) {
        return arg * arg;
    }
}
