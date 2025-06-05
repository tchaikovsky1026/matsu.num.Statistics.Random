/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.negbinomial;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.NegativeBinomialRnd;
import matsu.num.statistics.random.TestedIntegerRandomGenerator;

/**
 * 負の二項乱数に関する {@link TestedIntegerRandomGenerator} を提供する. <br>
 * コンストラクタで所望の負の二項乱数生成器を与えることで動作する.
 */
final class TestedNegativeBinomialRandomGenerator implements TestedIntegerRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();

    private final NegativeBinomialRnd negativeBinomialRnd;

    TestedNegativeBinomialRandomGenerator(NegativeBinomialRnd negativeBinomialRnd) {
        this.negativeBinomialRnd = Objects.requireNonNull(negativeBinomialRnd);
    }

    @Override
    public int newValue() {
        return negativeBinomialRnd.nextRandom(random);
    }

    @Override
    public double cumulativeProbability(int arg) {
        if (arg < 0) {
            return 0d;
        }

        double sum = 0d;
        for (int i = 0; i <= arg; i++) {
            sum += this.probabilityMassFunction(i);
        }
        return sum;
    }

    /**
     * 引数には
     * {@literal 0 <= arg}
     * でなければならない.
     */
    private double probabilityMassFunction(int k) {
        int r = negativeBinomialRnd.numberOfSuccesses();
        double p = negativeBinomialRnd.successPobability();

        if (p == 1d) {
            return k == 0 ? 1d : 0d;
        }

        /*
         * オーバーフロー対策のため, 対数により計算する.
         */
        double logP = 0d;
        //係数
        for (int i = 1; i <= k; i++) {
            logP += Math.log(((double) r + i - 1) / i);
        }

        logP += r * Math.log(p)
                + k * Math.log1p(-p);

        return Math.exp(logP);
    }
}
