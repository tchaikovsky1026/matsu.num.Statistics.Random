/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.fdist;

import java.util.Objects;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.FDistributionRnd;

/**
 * このパッケージに用意された {@link FDistributionRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 22.2
 */
public final class BetaBasedFDistributionRndFactory extends SkeletalFDistributionRndFactory {

    private final BetaRnd.Factory betaRndFactory;

    public BetaBasedFDistributionRndFactory(BetaRnd.Factory betaRndFactory) {
        super();
        this.betaRndFactory = Objects.requireNonNull(betaRndFactory);
    }

    @Override
    FDistributionRnd createInstanceOf(double d1, double d2) {
        return new BetaBasedFDistributionRnd(d1, d2, this.betaRndFactory);
    }
}
