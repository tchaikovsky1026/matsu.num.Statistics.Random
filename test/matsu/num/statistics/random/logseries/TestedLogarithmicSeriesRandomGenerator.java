/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.logseries;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.LogarithmicSeriesRnd;
import matsu.num.statistics.random.TestedIntegerRandomGenerator;

/**
 * 対数級数分布乱数に関する {@link TestedIntegerRandomGenerator} を提供する. <br>
 * コンストラクタで所望の乱数発生器を与えることで動作する.
 * 
 * @author Matsuura Y.
 */
final class TestedLogarithmicSeriesRandomGenerator implements TestedIntegerRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();

    private final LogarithmicSeriesRnd logSeriesRnd;

    /**
     * -p / log(1-p) である.
     */
    private final double coeff;

    /**
     * テストする乱数発生器を与えてインスタンスを構築する.
     * 
     * @param logSeriesRnd テストする乱数発生器
     * @throws NullPointerException 引数がnullの場合
     */
    TestedLogarithmicSeriesRandomGenerator(LogarithmicSeriesRnd logSeriesRnd) {
        super();
        this.logSeriesRnd = logSeriesRnd;

        double x = Math.log1p(-logSeriesRnd.p());
        coeff = x == 0d
                ? 1d
                : -logSeriesRnd.p() / x;
    }

    @Override
    public int newValue() {
        return logSeriesRnd.nextRandom(random);
    }

    @Override
    public double cumulativeProbability(int arg) {
        double sum = 0d;
        double p_pow = 1d;

        // k <= 0 でも成立
        for (int k = 1; k <= arg; k++) {
            sum += p_pow / k;
            p_pow *= logSeriesRnd.p();
        }
        return sum * coeff;
    }
}
