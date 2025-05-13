/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.13
 */
package matsu.num.statistics.random.binomial;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BinomialRnd;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Dirichlet 分布乱数を使用した, 二項分布に従う乱数発生を扱う. <br>
 * Dirichlet 分布乱数は内部的にガンマ乱数を使用する.
 * 
 * @author Matsuura Y.
 */
final class DirichletBasedBinomialRndOver1 extends SkeletalBinomialRnd {

    /**
     * lambdaの範囲を網羅できるための, ガンマ乱数の形状パラメータのビット数. <br>
     * 1,2,4,...,2^20までで, 20.
     */
    private static final int GAMMA_RND_BIT = 20;

    /**
     * 形状パラメータが1, 2, 4, ... のガンマ乱数発生器. <br>
     * 最大は 2^20 = 1_048_576 (GAMMA_RND_BIT = 20)
     */
    private final GammaRnd[] gammaRnds;

    private final Exponentiation exponentiation;

    /**
     * 指定したパラメータの二項分布乱数発生器インスタンスを構築する.
     */
    private DirichletBasedBinomialRndOver1(
            int n, double p, GammaRnd[] gammaRnds, Exponentiation exponentiation) {
        super(n, p);

        this.gammaRnds = gammaRnds;
        this.exponentiation = exponentiation;
    }

    @Override
    public int nextRandom(BaseRandom random) {
        throw new AssertionError("TODO");
    }

    static final class Factory {

        /**
         * 形状パラメータが1, 2, 4, ... のガンマ乱数発生器.
         */
        private final GammaRnd[] gammaRnds;

        private final Exponentiation exponentiation;

        Factory(Exponentiation exponentiation, GammaRnd.Factory gammaRndFactory) {
            super();

            this.exponentiation = exponentiation;
            this.gammaRnds = new GammaRnd[GAMMA_RND_BIT + 1];
            int k = 1;
            for (int i = 0; i < GAMMA_RND_BIT + 1; i++) {
                gammaRnds[i] = gammaRndFactory.instanceOf(k);
                k = k * 2;
            }
        }

        /**
         * 乱数発生器を生成する. <br>
         * バリデーションされていないので公開してはならない.
         */
        BinomialRnd createOf(int n, double p) {
            return new DirichletBasedBinomialRndOver1(n, p, gammaRnds, exponentiation);
        }
    }
}
