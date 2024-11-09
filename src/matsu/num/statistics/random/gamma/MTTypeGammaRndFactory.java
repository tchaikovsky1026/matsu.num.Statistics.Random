/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.gamma;

import java.util.Objects;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Marsaglia-Tsangに基づく, ガンマ乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 22.2
 */
public final class MTTypeGammaRndFactory extends SkeletalGammRndFactory {

    private final GammaRnd gammaRndAt1;

    private final Exponentiation exponentiation;
    private final ExponentialRnd.Factory exponentialRndFactory;
    private final NormalRnd.Factory normalRndFactory;

    public MTTypeGammaRndFactory(
            Exponentiation exponentiation,
            ExponentialRnd.Factory exponentialRndFactory, NormalRnd.Factory normalRndFactory) {

        super();
        this.exponentiation = Objects.requireNonNull(exponentiation);
        this.exponentialRndFactory = Objects.requireNonNull(exponentialRndFactory);
        this.gammaRndAt1 = new ExpBasedGammaRndAt1(this.exponentialRndFactory);
        this.normalRndFactory = Objects.requireNonNull(normalRndFactory);
    }

    @Override
    GammaRnd createInstanceOf(double k) {
        //MarsagliaTsangに基づく乱数発生器
        if (k < 1) {
            return new MTTypeGammaRndUnder1(
                    k, this.exponentiation, this.exponentialRndFactory, this.normalRndFactory);
        }
        if (k == 1) {
            return gammaRndAt1;
        }

        return new MTTypeGammaRndOver1(k, this.exponentiation, this.normalRndFactory);
    }
}
