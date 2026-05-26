/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.5.27
 */
package matsu.num.statistics.random.gamma;

import matsu.num.statistics.random.BaseRandom;

/**
 * 標準指数乱数によって実装された, 形状パラメータが1の乱数発生器.
 * 
 * @author Matsuura Y.
 */
final class ExpBasedGammaRndAt1 extends SkeletalGammaRnd {

    ExpBasedGammaRndAt1() {
        super(1d);
    }

    @Override
    public double nextRandom(BaseRandom random) {
        return random.nextExponential();
    }
}
