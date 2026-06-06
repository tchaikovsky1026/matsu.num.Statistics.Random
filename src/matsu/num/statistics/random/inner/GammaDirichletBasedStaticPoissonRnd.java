/*
 * Copyright © 2026 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.6.6
 */
package matsu.num.statistics.random.inner;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.base.LazyParameterlessRndFactory;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Gamma and Dirichlet 分布乱数を使用した, Poisson 分布に従う乱数発生を扱う. <br>
 * Dirichlet 分布乱数は内部的にガンマ乱数を使用する.
 * 
 * @author Matsuura Y.
 */
public final class GammaDirichletBasedStaticPoissonRnd implements InnerStaticPoissonRnd {

    private final Exponentiation exponentiation;
    private final StaticGammaRnd staticGammaRnd;
    private final InnerStaticBinomialRnd staticBinomialRnd;

    /**
     * 非公開コンストラクタ.
     * 
     * 引数チェックしていない.
     */
    private GammaDirichletBasedStaticPoissonRnd(
            Exponentiation exponentiation,
            StaticGammaRnd.Factory staticGammaRndFactory,
            InnerStaticBinomialRnd.Factory staticBinomialRndFactory) {
        super();
        this.exponentiation = exponentiation;
        this.staticGammaRnd = staticGammaRndFactory.instance();
        this.staticBinomialRnd = staticBinomialRndFactory.instance();
    }

    @Override
    public int next(double lambda, BaseRandom random) {
        Objects.requireNonNull(random);
        if (!InnerStaticPoissonRnd.acceptsParameter(lambda)) {
            throw new IllegalArgumentException(
                    String.format(
                            "Illegal parameter: lambda = %s", lambda));
        }

        throw new AssertionError("TODO");
    }

    /**
     * {@link GammaDirichletBasedStaticPoissonRnd} のファクトリを生成する.
     * 
     * @param exponentiation 指数関数の計算
     * @param staticGammaRndFactory staticガンマ乱数生成器のファクトリ
     * @param staticBinomialRndFactory static二項乱数生成器のファクトリ
     * @return このクラスのファクトリ
     * @throws NullPointerException 引数に null を含む場合
     */
    public static InnerStaticPoissonRnd.Factory createFactory(
            Exponentiation exponentiation,
            StaticGammaRnd.Factory staticGammaRndFactory,
            InnerStaticBinomialRnd.Factory staticBinomialRndFactory) {
        return new Factory(
                Objects.requireNonNull(exponentiation),
                Objects.requireNonNull(staticGammaRndFactory),
                Objects.requireNonNull(staticBinomialRndFactory));
    }

    /** 非公開ファクトリの実装. */
    private static final class Factory extends
            LazyParameterlessRndFactory<InnerStaticPoissonRnd>
            implements InnerStaticPoissonRnd.Factory {

        /**
         * 非公開コンストラクタ.
         * 
         * 引数チェックしていない.
         */
        Factory(Exponentiation exponentiation,
                StaticGammaRnd.Factory staticGammaRndFactory,
                InnerStaticBinomialRnd.Factory staticBinomialRndFactory) {
            super(
                    () -> new GammaDirichletBasedStaticPoissonRnd(
                            exponentiation, staticGammaRndFactory, staticBinomialRndFactory),
                    "InnerStaticPoissonRnd.Factory");
        }
    }
}
