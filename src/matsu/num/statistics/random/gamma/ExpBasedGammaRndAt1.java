/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.gamma;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;

/**
 * 標準指数乱数によって実装された, 形状パラメータが1の乱数発生器.
 * 
 * @author Matsuura Y.
 */
final class ExpBasedGammaRndAt1 extends SkeletalGammaRnd {

    private final ExponentialRnd expRnd;

    ExpBasedGammaRndAt1(ExponentialRnd.Factory exponentialRndFactory) {
        super(1d);
        this.expRnd = exponentialRndFactory.instance();
    }

    @Override
    public double nextRandom(BaseRandom random) {
        return expRnd.nextRandom(random);
    }
}
