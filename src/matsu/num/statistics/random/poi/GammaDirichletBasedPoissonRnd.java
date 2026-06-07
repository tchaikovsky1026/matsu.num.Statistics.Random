/*
 * Copyright © 2026 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2026.6.7
 */
package matsu.num.statistics.random.poi;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.PoissonRnd;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.inner.DirichletBasedStaticBinomialRnd;
import matsu.num.statistics.random.inner.GammaDirichletBasedStaticPoissonRnd;
import matsu.num.statistics.random.inner.InnerStaticPoissonRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Gamma and Dirichlet 分布乱数発生器を利用した, Poisson分布に従う乱数発生器を扱う.
 *
 * @author Matsuura Y.
 */
public final class GammaDirichletBasedPoissonRnd extends SkeletalPoissonRnd {

    private final InnerStaticPoissonRnd staticPoissonRnd;

    /**
     * 非公開の唯一のコンストラクタ. <br>
     * 引数チェックがされていないので, 公開してはならない.
     */
    private GammaDirichletBasedPoissonRnd(
            double lambda,
            InnerStaticPoissonRnd.Factory staticPoissonRndFactory) {
        super(lambda);

        this.staticPoissonRnd = staticPoissonRndFactory.instance();
    }

    @Override
    public int nextRandom(BaseRandom random) {
        return staticPoissonRnd.next(this.lambda, random);
    }

    /**
     * ガンマ分布乱数発生器を利用した {@link PoissonRnd} のファクトリインスタンスを生成する.
     * 
     * @param exponentiation 指数関数の計算
     * @param staticGammaRndFactory staticガンマ乱数生成器のファクトリ
     * @return 乱数生成器ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static PoissonRnd.Factory createFactory(
            Exponentiation exponentiation, StaticGammaRnd.Factory staticGammaRndFactory) {

        return new Factory(
                Objects.requireNonNull(exponentiation),
                Objects.requireNonNull(staticGammaRndFactory));
    }

    private static final class Factory extends SkeletalPoissonRnd.Factory {

        private final InnerStaticPoissonRnd.Factory staticPoissonRndFactory;

        Factory(Exponentiation exponentiation, StaticGammaRnd.Factory staticGammaRndFactory) {
            super();

            this.staticPoissonRndFactory =
                    GammaDirichletBasedStaticPoissonRnd.createFactory(
                            exponentiation, staticGammaRndFactory,
                            DirichletBasedStaticBinomialRnd.createFactory(staticGammaRndFactory));
        }

        @Override
        PoissonRnd createInstanceOf(double lambda) {
            return new GammaDirichletBasedPoissonRnd(
                    lambda, staticPoissonRndFactory);
        }
    }
}
