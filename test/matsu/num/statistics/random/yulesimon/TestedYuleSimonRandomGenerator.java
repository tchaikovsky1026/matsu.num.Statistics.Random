/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.yulesimon;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.TestedIntegerRandomGenerator;
import matsu.num.statistics.random.YuleSimonRnd;

/**
 * Yule-Simon 乱数に関する {@link TestedIntegerRandomGenerator} を提供する. <br>
 * コンストラクタで所望の乱数発生器を与えることで動作する.
 * 
 * @author Matsuura Y.
 */
final class TestedYuleSimonRandomGenerator implements TestedIntegerRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();

    private final YuleSimonRnd yuleRnd;

    /**
     * テストする乱数発生器を与えてインスタンスを構築する.
     * 
     * @param yuleRnd テストする乱数発生器
     * @throws NullPointerException 引数がnullの場合
     */
    TestedYuleSimonRandomGenerator(YuleSimonRnd yuleRnd) {

        super();
        this.yuleRnd = yuleRnd;
    }

    @Override
    public int newValue() {
        return yuleRnd.nextRandom(random);
    }

    @Override
    public double cumulativeProbability(int arg) {

        /*
         * (r = rho とする.)
         * 累積分布関数は,
         * F(k) = 1 - k*B(k, r+1) = 1 - C(k, r+1)
         * (C(k, r+1) = k * B(k, r+1) とした.)
         * 
         * C(1, r+1) = 1 / (r + 1)
         * C(j+1, r+1) = (j+1)/(j+r+1) * C(j, r+1)
         */

        if (arg <= 0) {
            return 0d;
        }

        double r = yuleRnd.rho();
        double c = 1d / (r + 1);
        for (int j = 1; j < arg; j++) {
            c *= (j + 1) / (j + r + 1);
        }

        return 1d - c;
    }

    @Override
    public double cumulativeProbabilityOneBelow(int arg) {
        return this.cumulativeProbability(arg - 1);
    }
}
