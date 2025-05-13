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

import java.util.Objects;

import matsu.num.statistics.random.BinomialRnd;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Dirichlet 分布を基にした二項分布に従う乱数発生に関するファクトリ.
 * 
 * @author Matsuura Y.
 */
public final class DirichletBasedBinomialRndFactory extends SkeletalBinomialRnd.Factory {

    private final DirichletBasedBinomialRndOver1.Factory over1Factory;

    /**
     * 唯一の非公開コンストラクタ.
     */
    private DirichletBasedBinomialRndFactory(
            Exponentiation exponentiation, GammaRnd.Factory gammaRndFactory) {
        super();
        this.over1Factory = new DirichletBasedBinomialRndOver1.Factory(
                exponentiation, gammaRndFactory);
    }

    @Override
    BinomialRnd createInstanceOf(int n, double p) {
        return n == 0
                ? new ZeroTrialBinomialRnd(p)
                : this.over1Factory.createOf(n, p);
    }

    /**
     * Dirichlet 分布を基にした,
     * 二項分布に従う乱数発生器のファクトリインスタンスを生成する.
     * 
     * @param exponentiation 指数関数の計算
     * @param gammaRndFactory ガンマ乱数生成器のファクトリ
     * @return 乱数生成器ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static BinomialRnd.Factory create(
            Exponentiation exponentiation, GammaRnd.Factory gammaRndFactory) {

        return new DirichletBasedBinomialRndFactory(
                Objects.requireNonNull(exponentiation),
                Objects.requireNonNull(gammaRndFactory));
    }
}
