/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.10
 */
package matsu.num.statistics.random.logseries;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.LogarithmicSeriesRnd;

/**
 * 素朴な実装による {@link LogarithmicSeriesRnd} の実装. <br>
 * 扱うパラメータによっては, 誤差が大きくなる (p が 1 に近い場合が危険).
 * 
 * @author Matsuura Y.
 */
public final class NaiveLogarithmicSeriesRnd extends SkeletalLogarithmicSeriesRnd {

    /**
     * 扱える最大の k
     */
    private static final int MAX_K = 100_000;

    /**
     * -log(1-p) / p である.
     */
    private final double coeff;

    /**
     * 非公開が必須. <br>
     * バリデーションされていない.
     */
    private NaiveLogarithmicSeriesRnd(double p) {
        super(p);

        double x = Math.log1p(-p);
        coeff = x == 0d
                ? 1d
                : -x / p;
    }

    @Override
    public int nextRandom(BaseRandom random) {

        double w = coeff * random.nextDouble();

        double sum = 0d;
        double p_pow = 1d;
        for (int k = 1; k <= MAX_K; k++) {
            sum += p_pow / k;
            if (sum >= w) {
                return k;
            }

            p_pow *= p;
        }

        return MAX_K;
    }

    /**
     * ファクトリを生成して返す.
     * 
     * @return ファクトリ
     */
    public static LogarithmicSeriesRnd.Factory createFactory() {
        return new Factory();
    }

    private static final class Factory extends SkeletalLogarithmicSeriesRnd.Factory {

        /**
         * staticメソッドから呼ばれる.
         */
        Factory() {
            super();
        }

        @Override
        LogarithmicSeriesRnd createInstanceOf(double p) {
            return new NaiveLogarithmicSeriesRnd(p);
        }
    }
}
