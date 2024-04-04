/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.fdist;

import java.util.Objects;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.FDistributionRnd;

/**
 * このパッケージに用意された {@link FDistributionRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
public final class BetaBasedFDistributionRndFactory implements FDistributionRnd.Factory {

    private final BetaRnd.Factory betaRndFactory;

    public BetaBasedFDistributionRndFactory(BetaRnd.Factory betaRndFactory) {
        super();
        this.betaRndFactory = Objects.requireNonNull(betaRndFactory);
    }

    @Override
    public boolean acceptsParameters(double d1, double d2) {
        return FDistributionRnd.LOWER_LIMIT_DEGREES_OF_FREEDOM <= d1
                && d1 <= FDistributionRnd.UPPER_LIMIT_DEGREES_OF_FREEDOM
                && FDistributionRnd.LOWER_LIMIT_DEGREES_OF_FREEDOM <= d2
                && d2 <= FDistributionRnd.UPPER_LIMIT_DEGREES_OF_FREEDOM;
    }

    @Override
    public FDistributionRnd instanceOf(double d1, double d2) {
        if (!this.acceptsParameters(d1, d2)) {
            throw new IllegalArgumentException(
                    String.format(
                            "パラメータ不正:d1 = %s, d2 = %s", d1, d2));
        }
        return new BetaBasedFDistributionRnd(d1, d2, this.betaRndFactory);
    }

    @Override
    public String toString() {
        return "FDistRnd.Factory";
    }
}
