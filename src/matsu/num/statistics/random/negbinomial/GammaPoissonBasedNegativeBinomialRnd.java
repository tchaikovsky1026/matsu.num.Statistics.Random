/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.24
 */
package matsu.num.statistics.random.negbinomial;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.NegativeBinomialRnd;
import matsu.num.statistics.random.lib.Exponentiation;
import matsu.num.statistics.random.util.GammaHomoProcessBasedPoissonRndHelper;

/**
 * ガンマ-Poisson乱数を基にした負の二項乱数発生器を扱う.
 * 
 * @author Matsuura Y.
 */
public final class GammaPoissonBasedNegativeBinomialRnd
        extends SkeletalNegativeBinomialRnd {

    // 初段のガンマ乱数生成に関する
    private final GammaRnd gammaRnd_r;
    private final double coeff;

    private final GammaHomoProcessBasedPoissonRndHelper poissonRndHelper;

    /**
     * 唯一のコンストラクタ, 内部から呼ばれる.
     */
    private GammaPoissonBasedNegativeBinomialRnd(
            int r, double p,
            GammaRnd.Factory gammaRndFactory,
            GammaHomoProcessBasedPoissonRndHelper poissonRndHelper) {
        super(r, p);

        this.gammaRnd_r = gammaRndFactory.instanceOf(r);
        this.coeff = (1 - p) / p;

        this.poissonRndHelper = poissonRndHelper;
    }

    @Override
    public int nextRandom(BaseRandom random) {

        double y = this.gammaRnd_r.nextRandom(random) * this.coeff;
        if (!(y < GammaHomoProcessBasedPoissonRndHelper.MAX_LAMBDA)) {
            y = GammaHomoProcessBasedPoissonRndHelper.MAX_LAMBDA;
        }

        return this.poissonRndHelper.next(y, random);
    }

    /**
     * ガンマ-Poisson乱数を基にした負の二項乱数発生器のファクトリ.
     * 
     * @param exponentiation 指数関数の計算
     * @param gammaRndFactory ガンマ乱数生成器ファクトリ
     * @return 負の二項乱数生成器ファクトリ
     * @throws NullPointerException 引数がnullの場合
     */
    public static NegativeBinomialRnd.Factory createFactory(
            Exponentiation exponentiation, GammaRnd.Factory gammaRndFactory) {

        return new Factory(exponentiation, gammaRndFactory);
    }

    private static final class Factory extends SkeletalNegativeBinomialRnd.Factory {

        private final GammaRnd.Factory gammaRndFactory;
        private final GammaHomoProcessBasedPoissonRndHelper poissonRndHelper;

        /**
         * 唯一のコンストラクタ. 非公開.
         */
        Factory(Exponentiation exponentiation, GammaRnd.Factory gammaRndFactory) {
            super();
            this.poissonRndHelper =
                    new GammaHomoProcessBasedPoissonRndHelper(exponentiation, gammaRndFactory);
            this.gammaRndFactory = gammaRndFactory;
        }

        @Override
        NegativeBinomialRnd createInstanceOf(int r, double p) {
            return new GammaPoissonBasedNegativeBinomialRnd(
                    r, p,
                    this.gammaRndFactory, this.poissonRndHelper);
        }
    }
}
