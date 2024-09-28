/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.weibull;

import java.util.Objects;

import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.WeibullRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * 標準Gumbelベースの標準Weibull分布乱数生成器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 21.0
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
    protected WeibullRnd createInstanceOf(double k) {
        return new GumbelBasedWeibullRnd(k, this.exponentiation, this.gumbelRndFactory);
    }
}
