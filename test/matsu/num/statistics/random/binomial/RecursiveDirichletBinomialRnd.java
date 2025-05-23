/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.21
 */
package matsu.num.statistics.random.binomial;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BinomialRnd;
import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.staticbeta.StaticBetaFactoryForTesting;

/**
 * Dirichlet乱数により再帰的に計算する, 二項乱数発生器. <br>
 * アルゴリズムのテストのための実装であり, プロダクトコードに含める予定はない.
 * 
 * @author Matsuura Y.
 */
final class RecursiveDirichletBinomialRnd extends SkeletalBinomialRnd {

    /**
     * この乱数発生器のファクトリ.
     */
    static final BinomialRnd.Factory FACTORY = new Factory();

    /**
     * 内部で使うベータ乱数発生器(2変数Dirichlet乱数発生器).
     */
    private static final StaticBetaRnd STATIC_BETA_RND = StaticBetaFactoryForTesting.FACTORY.instance();

    private RecursiveDirichletBinomialRnd(int n, double p) {
        super(n, p);
    }

    @Override
    public int nextRandom(BaseRandom random) {
        return binomialRecursive(this.n, this.p, random);
    }

    /**
     * 再帰的に二項乱数を発生させる.
     */
    private static int binomialRecursive(int n, double p, BaseRandom random) {
        if (p == 0d) {
            return 0;
        }
        if (p == 1d) {
            return n;
        }

        if (n <= 10) {
            return binomialNaive(n, p, random);
        }

        int m1 = (n + 1) / 2;
        int m2 = n + 1 - m1;

        double x1 = STATIC_BETA_RND.nextRandom(random, m1, m2);
        return x1 >= p
                ? binomialRecursive(m1 - 1, p / x1, random)
                : m1 + binomialRecursive(m2 - 1, (p - x1) / (1 - x1), random);
    }

    /**
     * 素朴な方法による二項乱数: 再帰の底で呼ばれる.
     */
    private static int binomialNaive(int n, double p, BaseRandom random) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (random.nextDouble() < p) {
                count++;
            }
        }
        return count;
    }

    private static final class Factory extends SkeletalBinomialRnd.Factory {

        Factory() {
            super();
        }

        @Override
        BinomialRnd createInstanceOf(int n, double p) {
            return new RecursiveDirichletBinomialRnd(n, p);
        }
    }
}
