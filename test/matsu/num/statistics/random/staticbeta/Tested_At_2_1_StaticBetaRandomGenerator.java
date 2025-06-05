/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.5
 */
package matsu.num.statistics.random.staticbeta;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * a=2,b=1のStaticベータ乱数発生器のテスタ. <br>
 * 累積分布関数はx^2
 * 
 * @author Matsuura Y.
 */
final class Tested_At_2_1_StaticBetaRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();
    private final StaticBetaRnd staticBetaRnd;

    private final double a = 2d;
    private final double b = 1d;

    Tested_At_2_1_StaticBetaRandomGenerator(StaticBetaRnd staticBetaRnd) {
        this.staticBetaRnd = Objects.requireNonNull(staticBetaRnd);
    }

    @Override
    public double newValue() {
        return this.staticBetaRnd.nextRandom(random, a, b);
    }

    @Override
    public double cumulativeProbability(double arg) {
        return arg * arg;
    }
}
