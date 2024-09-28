/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.gamma;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Marsaglia-Tsangの方法に基づく, 形状パラメータが1より小さいときの乱数生成器の実装. <br>
 * X～Gamma(k+1),Y～Beta(k+1,1)のとき(XY)～Gamma(k)となることを利用する.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
final class MTTypeGammaRndUnder1 extends SkeletalGammaRnd {

    //Gamma(k+1)乱数を発生させるためのインスタンス
    private final GammaRnd gammaKPlus1;
    private final double invK;

    private final ExponentialRnd expRnd;

    private final Exponentiation exponentiation;

    /**
     * @param k 0.01<=k<=1
     */
    MTTypeGammaRndUnder1(double k, Exponentiation exponentiation,
            ExponentialRnd.Factory exponentialRndFactory, NormalRnd.Factory normalRndFactory) {
        super(k);

        assert GammaRnd.LOWER_LIMIT_SHAPE_PARAMETER <= k && k <= 1 : "LL <= k <=1 でない";

        this.exponentiation = exponentiation;
        this.gammaKPlus1 = new MTTypeGammaRndOver1(k + 1, exponentiation, normalRndFactory);
        this.invK = 1 / k;
        this.expRnd = exponentialRndFactory.instance();
    }

    @Override
    public double nextRandom(BaseRandom random) {
        /*
         * Gamma(k+1)乱数とBeta(1,k)乱数からGamma(k)を発生させる.
         * X～Gamma(a), Y～Beta(b,a-b)のとき, Z=XY～Gamma(b)である.
         * よって, a=k+1,b=kとすることで,
         * Z = XY = Random[Gamma(k+1)]*Random[Beta(k,1)]
         * からRandom[Gamma(k)]が得られる.
         * 
         * Beta(k,1)は指数k-1のべき分布であり,
         * 累積分布関数が y^k で書けるので逆関数法が使える.
         */
        return this.gammaKPlus1.nextRandom(random) * exponentiation.exp(
                -this.expRnd.nextRandom(random) * this.invK);
    }
}