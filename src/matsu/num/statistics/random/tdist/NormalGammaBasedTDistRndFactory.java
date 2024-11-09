/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.tdist;

import java.util.Objects;

import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * 正規ガンマタイプのt分布に従う乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 22.2
 */
public final class NormalGammaBasedTDistRndFactory extends SkeletalTDistributionRndFactory {

    private final Exponentiation exponentiation;
    private final NormalRnd.Factory normalRndFactory;
    private final GammaRnd.Factory gammaRndFactory;

    public NormalGammaBasedTDistRndFactory(
            Exponentiation exponentiation,
            NormalRnd.Factory normalRndFactory,
            GammaRnd.Factory gammaRndFactory) {
        super();
        this.exponentiation = Objects.requireNonNull(exponentiation);
        this.normalRndFactory = Objects.requireNonNull(normalRndFactory);
        this.gammaRndFactory = Objects.requireNonNull(gammaRndFactory);
    }

    @Override
    TDistributionRnd createInstanceOf(double nu) {
        return new NormalGammaBasedTDistRnd(nu, this.exponentiation, this.normalRndFactory, this.gammaRndFactory);
    }
}
