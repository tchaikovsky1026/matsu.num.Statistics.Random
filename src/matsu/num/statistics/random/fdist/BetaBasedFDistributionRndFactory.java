/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.25
 */
package matsu.num.statistics.random.fdist;

import java.util.Objects;

import matsu.num.statistics.random.BetaRnd;

/**
 * このパッケージに用意された {@link FDistributionRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 22.0
 */
public final class BetaBasedFDistributionRndFactory extends SkeletalFDistributionRndFactory {

    private final BetaRnd.Factory betaRndFactory;

    public BetaBasedFDistributionRndFactory(BetaRnd.Factory betaRndFactory) {
        super();
        this.betaRndFactory = Objects.requireNonNull(betaRndFactory);
    }

    @Override
    protected FDistributionRnd createInstanceOf(double d1, double d2) {
        return new BetaBasedFDistributionRnd(d1, d2, this.betaRndFactory);
    }
}
