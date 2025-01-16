/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.poi;

import java.util.Objects;

import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.PoissonRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * ガンマ分布乱数発生器を利用した, Poisson分布に従う乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 */
public final class GammaHomoProcessBasedPoissonRndFactory extends SkeletalPoissonRndFactory {

    /**
     * 形状パラメータが1, 2, 4, ... のガンマ乱数発生器.
     */
    private final GammaRnd[] gammaRnds;

    private final Exponentiation exponentiation;

    public GammaHomoProcessBasedPoissonRndFactory(Exponentiation exponentiation, GammaRnd.Factory gammaRndFactory) {
        super();

        this.exponentiation = Objects.requireNonNull(exponentiation);
        this.gammaRnds = new GammaRnd[GammaHomoProcessBasedPoissonRnd.GAMMA_RND_BIT + 1];
        int k = 1;
        for (int i = 0; i < GammaHomoProcessBasedPoissonRnd.GAMMA_RND_BIT + 1; i++) {
            gammaRnds[i] = gammaRndFactory.instanceOf(k);
            k = k * 2;
        }
    }

    @Override
    PoissonRnd createInstanceOf(double lambda) {
        return new GammaHomoProcessBasedPoissonRnd(lambda, this.gammaRnds, this.exponentiation);
    }
}
