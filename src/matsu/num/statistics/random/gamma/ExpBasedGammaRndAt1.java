/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.gamma;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GammaRnd;

/**
 * 標準指数乱数によって実装された, 形状パラメータが1の乱数生成器.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
final class ExpBasedGammaRndAt1 implements GammaRnd {

    private final ExponentialRnd expRnd;

    ExpBasedGammaRndAt1(ExponentialRnd.Factory exponentialRndFactory) {
        super();
        this.expRnd = exponentialRndFactory.instance();
    }

    @Override
    public double shapeParameter() {
        return 1d;
    }

    @Override
    public double nextRandom(BaseRandom random) {
        return expRnd.nextRandom(random);
    }

    @Override
    public String toString() {
        return "GammaRnd(Exponential)";
    }
}
