/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.13
 */
package matsu.num.statistics.random.fdist;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.FDistributionRnd;

/**
 * ベータプライム乱数器を利用した, F分布に従う乱数発生器.
 *
 * @author Matsuura Y.
 */
public final class BetaBasedFDistributionRnd extends SkeletalFDistributionRnd {

    private final BetaRnd betaRnd;
    private final double D2OverD1;

    private BetaBasedFDistributionRnd(double d1, double d2, BetaRnd.Factory betaRndFactory) {
        super(d1, d2);

        this.betaRnd = betaRndFactory.instanceOf(d1 * 0.5, d2 * 0.5);
        this.D2OverD1 = d2 / d1;
    }

    @Override
    public final double nextRandom(BaseRandom random) {
        return this.D2OverD1 * this.betaRnd.nextBetaPrime(random);
    }

    /**
     * ベータ分布乱数器を利用した {@link FDistributionRnd} のファクトリインスタンスを生成する.
     * 
     * @param betaRndFactory ベータ乱数生成器のファクトリ
     * @return 乱数生成器ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static FDistributionRnd.Factory createFactory(BetaRnd.Factory betaRndFactory) {
        return new Factory(Objects.requireNonNull(betaRndFactory));
    }

    private static final class Factory extends SkeletalFDistributionRnd.Factory {

        private final BetaRnd.Factory betaRndFactory;

        Factory(BetaRnd.Factory betaRndFactory) {
            super();
            this.betaRndFactory = betaRndFactory;
        }

        @Override
        FDistributionRnd createInstanceOf(double d1, double d2) {
            return new BetaBasedFDistributionRnd(d1, d2, this.betaRndFactory);
        }
    }
}
