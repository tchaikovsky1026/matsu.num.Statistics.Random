/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.13
 */
package matsu.num.statistics.random.staticbeta;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.StaticGammaRnd;

/**
 * Marsaglia-Tsangの方法に基づく, ガンマ乱数のstaticな発生器.
 * 
 * @author Matsuura Y.
 */
public final class GammaBasedStaticBetaRnd extends SkeletalStaticBetaRnd {

    private final StaticGammaRnd staticGammaRnd;

    private GammaBasedStaticBetaRnd(StaticGammaRnd.Factory staticGammaRndFactory) {
        super();
        this.staticGammaRnd = staticGammaRndFactory.instance();
    }

    @Override
    double calcNextBeta(BaseRandom random, double a, double b) {
        double out;
        do {
            double u1 = this.staticGammaRnd.nextRandom(random, a);
            double u2 = this.staticGammaRnd.nextRandom(random, b);
            out = u1 / (u1 + u2);

            // u1 == 0 && u2 == 0 の場合に関する分岐
        } while (Double.isNaN(out));

        return out;
    }

    @Override
    double calcNextBetaPrime(BaseRandom random, double a, double b) {
        double out;
        do {
            double u1 = this.staticGammaRnd.nextRandom(random, a);
            double u2 = this.staticGammaRnd.nextRandom(random, b);
            out = u1 / u2;

            // u1 == 0 && u2 == 0 の場合に関する分岐
        } while (Double.isNaN(out));

        return out;
    }

    /**
     * {@link matsu.num.statistics.random.StaticBetaRnd.Factory} を生成する.
     * 
     * @param staticGammaRndFactory StaticGamma乱数のファクトリ
     * @return StaticBeta乱数のファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static StaticBetaRnd.Factory createFactory(StaticGammaRnd.Factory staticGammaRndFactory) {
        return new LazyStaticBetaRndFactory(
                () -> new GammaBasedStaticBetaRnd(staticGammaRndFactory));
    }
}
