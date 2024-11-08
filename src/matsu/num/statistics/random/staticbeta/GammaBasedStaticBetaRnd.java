/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.staticbeta;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.StaticGammaRnd;

/**
 * Marsaglia-Tsangの方法に基づく, ガンマ乱数のstaticな発生器.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
public final class GammaBasedStaticBetaRnd extends SkeletalStaticBetaRnd {

    private final StaticGammaRnd staticGammaRnd;

    private GammaBasedStaticBetaRnd(StaticGammaRnd.Factory staticGammaRndFactory) {
        super();
        this.staticGammaRnd = staticGammaRndFactory.instance();
    }

    @Override
    double calcNextBeta(BaseRandom random, double a, double b) {
        double u1 = this.staticGammaRnd.nextRandom(random, a);
        double u2 = this.staticGammaRnd.nextRandom(random, b);
        double out = u1 / (u1 + u2);
        return Double.isFinite(out) ? out : 0;
    }

    @Override
    protected double calcNextBetaPrime(BaseRandom random, double a, double b) {

        double u1 = this.staticGammaRnd.nextRandom(random, a);
        double u2 = this.staticGammaRnd.nextRandom(random, b);
        double out = u1 / u2;
        return Double.isFinite(out) ? out : 0;
    }

    /**
     * {@link StaticBetaRnd} を生成するファクトリを生成する.
     * 
     * @param staticGammaRndFactory StaticGamma乱数のファクトリ
     * @return StaticBeta乱数のファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static StaticBetaRnd.Factory createFactory(StaticGammaRnd.Factory staticGammaRndFactory) {
        return new StaticBetaRndFactory(new GammaBasedStaticBetaRnd(staticGammaRndFactory));
    }
}
