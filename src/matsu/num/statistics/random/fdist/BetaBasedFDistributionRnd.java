/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.fdist;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BetaRnd;

/**
 * ベータプライム乱数器を利用した, F分布に従う乱数発生器.
 *
 * @author Matsuura Y.
 * @version 21.0
 */
final class BetaBasedFDistributionRnd extends SkeletalFDistributionRnd {

    private final BetaRnd betaRnd;
    private final double D2OverD1;

    BetaBasedFDistributionRnd(double d1, double d2, BetaRnd.Factory betaRndFactory) {
        super(d1, d2);

        this.betaRnd = betaRndFactory.instanceOf(d1 * 0.5, d2 * 0.5);
        this.D2OverD1 = d2 / d1;
    }

    @Override
    public final double nextRandom(BaseRandom random) {
        double out = this.D2OverD1 * this.betaRnd.nextBetaPrime(random);
        return Double.isFinite(out) ? out : 0;
    }
}
