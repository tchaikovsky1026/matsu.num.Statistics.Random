/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.weibull;

import java.util.Objects;

import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * 標準Gumbelベースの標準Weibull分布乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 22.1
 */
public final class GumbelBasedWeibullRndFactory extends SkeletalWeibullRndFactory {

    private final Exponentiation exponentiation;
    private final GumbelRnd.Factory gumbelRndFactory;

    public GumbelBasedWeibullRndFactory(Exponentiation exponentiation, GumbelRnd.Factory gumbelRndFactory) {
        super();
        this.exponentiation = Objects.requireNonNull(exponentiation);
        this.gumbelRndFactory = Objects.requireNonNull(gumbelRndFactory);
    }

    @Override
    protected WeibullRndSealed createInstanceOf(double k) {
        return new GumbelBasedWeibullRnd(k, this.exponentiation, this.gumbelRndFactory);
    }
}
