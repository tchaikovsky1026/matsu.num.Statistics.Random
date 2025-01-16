/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.weibull;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * 標準Gumbel分布を用いた標準Weibull分布乱数発生器の実装.
 *
 * @author Matsuura Y.
 */
final class GumbelBasedWeibullRnd extends SkeletalWeibullRnd {

    private final double invK;

    private final Exponentiation exponentiation;
    private final GumbelRnd gumRnd;

    GumbelBasedWeibullRnd(double k,
            Exponentiation exponentiation, GumbelRnd.Factory gumbelRndFactory) {
        super(k);

        this.invK = 1 / k;
        this.exponentiation = exponentiation;
        this.gumRnd = gumbelRndFactory.instance();
    }

    @Override
    public double nextRandom(BaseRandom random) {
        return exponentiation.exp(-this.gumRnd.nextRandom(random) * this.invK);
    }
}
