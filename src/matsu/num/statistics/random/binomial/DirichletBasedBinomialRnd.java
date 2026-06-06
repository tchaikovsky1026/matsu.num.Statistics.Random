/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.6.7
 */
package matsu.num.statistics.random.binomial;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BinomialRnd;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.inner.DirichletBasedStaticBinomialRnd;
import matsu.num.statistics.random.inner.InnerStaticBinomialRnd;

/**
 * Dirichlet 分布乱数を使用した, 二項分布に従う乱数発生を扱う. <br>
 * Dirichlet 分布乱数は内部的にガンマ乱数を使用する.
 * 
 * @author Matsuura Y.
 */
public final class DirichletBasedBinomialRnd extends SkeletalBinomialRnd {

    private final InnerStaticBinomialRnd staticBinomialRnd;

    /**
     * 指定したパラメータの二項分布乱数発生器インスタンスを構築する.
     */
    private DirichletBasedBinomialRnd(
            int n, double p,
            InnerStaticBinomialRnd.Factory staticBinomialRndFactory) {
        super(n, p);
        this.staticBinomialRnd = staticBinomialRndFactory.instance();
    }

    @Override
    public int nextRandom(BaseRandom random) {
        return staticBinomialRnd.next(n, p, random);
    }

    /**
     * Dirichlet 分布乱数に基づく二項乱数発生器のファクトリ.
     * 
     * @param staticGammaRndFactory staticガンマ乱数生成器ファクトリ
     * @return 二項乱数生成器ファクトリ
     * @throws NullPointerException 引数がnullの場合
     */
    public static BinomialRnd.Factory createFactory(StaticGammaRnd.Factory staticGammaRndFactory) {
        //ここでNPExの可能性
        return new Factory(staticGammaRndFactory);
    }

    private static final class Factory extends SkeletalBinomialRnd.Factory {

        private final InnerStaticBinomialRnd.Factory staticBinomialRndFactory;

        Factory(StaticGammaRnd.Factory staticGammaRndFactory) {
            super();
            this.staticBinomialRndFactory =
                    DirichletBasedStaticBinomialRnd.createFactory(staticGammaRndFactory);
        }

        @Override
        BinomialRnd createInstanceOf(int n, double p) {
            return new DirichletBasedBinomialRnd(
                    n, p,
                    staticBinomialRndFactory);
        }
    }
}
