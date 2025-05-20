/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.19
 */
package matsu.num.statistics.random.binomial;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BinomialRnd;

/**
 * 独立なBernoulli試行をもとにした, 素朴な二項乱数生成器.
 * 
 * @author Matsuura Y.
 * @deprecated このクラスはプロダクトコード中では使われていない
 */
@Deprecated
final class BernoulliTrialBinomialRnd extends SkeletalBinomialRnd {

    /**
     * 唯一の非公開コンストラクタ.
     */
    private BernoulliTrialBinomialRnd(int n, double p) {
        super(n, p);
    }

    @Override
    public int nextRandom(BaseRandom random) {
        int count = 0;
        for (int i = 0; i < this.n; i++) {
            if (random.nextDouble() < this.p) {
                count++;
            }
        }
        return count;
    }

    /**
     * このクラスのファクトリを返す.
     * 
     * @return ファクトリ
     */
    static final BinomialRnd.Factory factory() {
        return Factory.INSTANCE;
    }

    private static final class Factory extends SkeletalBinomialRnd.Factory {

        static final Factory INSTANCE = new Factory();

        /**
         * 非公開のコンストラクタ.
         * 内部から呼ばれる.
         */
        private Factory() {
            super();
        }

        @Override
        BinomialRnd createInstanceOf(int n, double p) {
            return new BernoulliTrialBinomialRnd(n, p);
        }
    }
}
