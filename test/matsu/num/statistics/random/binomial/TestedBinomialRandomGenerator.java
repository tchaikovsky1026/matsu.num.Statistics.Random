/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.binomial;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BinomialRnd;
import matsu.num.statistics.random.TestedIntegerRandomGenerator;

/**
 * 二項乱数に関する {@link TestedIntegerRandomGenerator} を提供する. <br>
 * コンストラクタで所望の二項乱数生成器を与えることで動作する.
 */
final class TestedBinomialRandomGenerator implements TestedIntegerRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();

    private final BinomialRnd binomialRnd;

    TestedBinomialRandomGenerator(BinomialRnd binomialRnd) {
        this.binomialRnd = Objects.requireNonNull(binomialRnd);
    }

    @Override
    public int newValue() {
        return binomialRnd.nextRandom(random);
    }

    @Override
    public double cumulativeProbability(int arg) {
        if (arg < 0) {
            return 0d;
        }
        if (arg > binomialRnd.numberOfTrials()) {
            return 1d;
        }

        double sum = 0d;
        for (int i = 0; i <= arg; i++) {
            sum += this.probabilityMassFunction(i);
        }
        return sum;
    }

    /**
     * 引数には
     * {@literal 0 <= arg <= n}
     * でなければならない.
     */
    private double probabilityMassFunction(int k) {
        int n = binomialRnd.numberOfTrials();
        double p = binomialRnd.successPobability();

        /*
         * オーバーフロー対策のため, 対数により計算する.
         */
        double logP = 0d;
        //係数
        for (int i = 1; i <= Math.min(k, n - k); i++) {
            logP += Math.log(((double) n - i + 1) / i);
        }

        logP += k * Math.log(p)
                + (n - k) * Math.log1p(-p);

        return Math.exp(logP);
    }
}
