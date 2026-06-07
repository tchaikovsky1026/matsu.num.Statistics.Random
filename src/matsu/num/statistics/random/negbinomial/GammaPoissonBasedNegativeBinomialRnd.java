/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.6.7
 */
package matsu.num.statistics.random.negbinomial;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.NegativeBinomialRnd;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.inner.DirichletBasedStaticBinomialRnd;
import matsu.num.statistics.random.inner.GammaDirichletBasedStaticPoissonRnd;
import matsu.num.statistics.random.inner.InnerStaticPoissonRnd;
import matsu.num.statistics.random.lib.Exponentiation;

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

    private final InnerStaticPoissonRnd staticPoissonRnd;

    /**
     * 唯一のコンストラクタ, 内部から呼ばれる.
     */
    private GammaPoissonBasedNegativeBinomialRnd(
            int r, double p,
            GammaRnd.Factory gammaRndFactory,
            InnerStaticPoissonRnd.Factory staticPoissonRndFactory) {
        super(r, p);

        this.gammaRnd_r = gammaRndFactory.instanceOf(r);
        this.coeff = (1 - p) / p;

        this.staticPoissonRnd = staticPoissonRndFactory.instance();
    }

    @Override
    public int nextRandom(BaseRandom random) {
        while (true) {
            double y = this.gammaRnd_r.nextRandom(random) * this.coeff;
            if (!InnerStaticPoissonRnd.acceptsParameter(y)) {
                continue;
            }
            return staticPoissonRnd.next(y, random);
        }
    }

    /**
     * ガンマ-Poisson乱数を基にした負の二項乱数発生器のファクトリ.
     * 
     * @param exponentiation 指数関数の計算
     * @param gammaRndFactory ガンマ乱数生成器ファクトリ
     * @param staticGammaRndFactory
     * @return 負の二項乱数生成器ファクトリ
     * @throws NullPointerException 引数がnullの場合
     */
    public static NegativeBinomialRnd.Factory createFactory(
            Exponentiation exponentiation, GammaRnd.Factory gammaRndFactory,
            StaticGammaRnd.Factory staticGammaRndFactory) {

        return new Factory(
                Objects.requireNonNull(exponentiation),
                Objects.requireNonNull(gammaRndFactory),
                Objects.requireNonNull(staticGammaRndFactory));
    }

    private static final class Factory extends SkeletalNegativeBinomialRnd.Factory {

        private final GammaRnd.Factory gammaRndFactory;
        private final InnerStaticPoissonRnd.Factory staticPoissonRndFactory;

        /**
         * 非公開コンストラクタ.
         * 
         * 引数チェックはされていない.
         */
        Factory(Exponentiation exponentiation, GammaRnd.Factory gammaRndFactory,
                StaticGammaRnd.Factory staticGammaRndFactory) {
            super();
            this.gammaRndFactory = gammaRndFactory;
            this.staticPoissonRndFactory = GammaDirichletBasedStaticPoissonRnd.createFactory(
                    exponentiation, staticGammaRndFactory,
                    DirichletBasedStaticBinomialRnd.createFactory(staticGammaRndFactory));
        }

        @Override
        NegativeBinomialRnd createInstanceOf(int r, double p) {
            return new GammaPoissonBasedNegativeBinomialRnd(
                    r, p,
                    this.gammaRndFactory, this.staticPoissonRndFactory);
        }
    }
}
