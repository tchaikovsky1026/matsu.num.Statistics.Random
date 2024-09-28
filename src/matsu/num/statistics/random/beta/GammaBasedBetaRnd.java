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

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GammaRnd;

/**
 * ガンマ分布乱数器を利用した, ベータ分布に従う乱数発生器.
 *
 * @author Matsuura Y.
 * @version 21.0
 */
final class GammaBasedBetaRnd extends SkeletalBetaRnd {

    private final GammaRnd gammaRndA;
    private final GammaRnd gammaRndB;

    /**
     * 指定した形状パラメータのベータ分布乱数発生器インスタンスを返す. <br>
     * 形状パラメータ <i>a</i>,<i>b</i> は,
     * {@code 1.0E-2 <= a <= 1.0E+14 && 1.0E-2 <= b <= 1.0E+14} でなければならない.
     *
     * @param a 形状パラメータa
     * @param b 形状パラメータb
     */
    GammaBasedBetaRnd(double a, double b, GammaRnd.Factory gammaRndFactory) {
        super(a, b);

        this.gammaRndA = gammaRndFactory.instanceOf(a);
        this.gammaRndB = gammaRndFactory.instanceOf(b);
    }

    @Override
    public final double nextRandom(BaseRandom random) {
        double u1 = this.gammaRndA.nextRandom(random);
        double u2 = this.gammaRndB.nextRandom(random);
        double out = u1 / (u1 + u2);
        return Double.isFinite(out) ? out : 0;
    }

    @Override
    public final double nextBetaPrime(BaseRandom random) {
        double u1 = this.gammaRndA.nextRandom(random);
        double u2 = this.gammaRndB.nextRandom(random);
        double out = u1 / u2;
        return Double.isFinite(out) ? out : 0;
    }
}
