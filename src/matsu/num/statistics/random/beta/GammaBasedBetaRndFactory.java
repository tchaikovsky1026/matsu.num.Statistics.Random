/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.24
 */
package matsu.num.statistics.random.beta;

import java.util.Objects;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.GammaRnd;

/**
 * ガンマ乱数発生器に基づくベータ乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
public final class GammaBasedBetaRndFactory extends SkeletalBetaRndFactory {

    private final GammaRnd.Factory gammaRndFactory;

    public GammaBasedBetaRndFactory(GammaRnd.Factory gammaRndFactory) {
        this.gammaRndFactory = Objects.requireNonNull(gammaRndFactory);
    }

    @Override
    protected BetaRnd createInstanceOf(double a, double b) {
        return new GammaBasedBetaRnd(a, b, this.gammaRndFactory);
    }
}
