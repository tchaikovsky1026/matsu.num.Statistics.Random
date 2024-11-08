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
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Marsaglia-Tsangの方法に基づく, 形状パラメータが1より大きいときの乱数発生器の実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
final class MTTypeGammaRndOver1 extends SkeletalGammaRnd {

    //Gamma(k)乱数を発生させるためのdとc
    private final double d;
    private final double c;
    private final NormalRnd normalRnd;

    private final Exponentiation exponentiation;

    /**
     * @param k 1<=k<=1E28
     */
    MTTypeGammaRndOver1(double k, Exponentiation exponentiation, NormalRnd.Factory normalRndFactory) {
        super(k);

        assert 1 <= k && k <= GammaRnd.UPPER_LIMIT_SHAPE_PARAMETER : "1 <= k <= UL でない";

        this.normalRnd = normalRndFactory.instance();
        this.exponentiation = exponentiation;
        this.d = k - (1.0 / 3.0);
        this.c = (1.0 / 3.0) / exponentiation.sqrt(d);
    }

    @Override
    public double nextRandom(BaseRandom random) {
        /*
         * Marsaglia-Tsangの方法により, {@literal k > 1}のGamma乱数を生成する.
         * ただし, d = k - 1/3, c = 1/sqrt(9d)
         */
        while (true) {
            double z = this.normalRnd.nextRandom(random);
            double v = 1 + c * z;
            double w = v * v * v;
            double y = d * w;
            if (y < 0.0) {
                continue;
            }
            double u = random.nextDouble();
            double z2 = z * z;
            if (u > z2 * z2 * 0.0331 || z2 * 0.5 + d * exponentiation.log(w) + d - y > exponentiation.log(1 - u)) {
                return y;
            }
        }
    }
}
