/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.levy;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.LevyRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * <p>
 * 標準Levy乱数発生器に関する, 変則型の {@link TestedFloatingRandomGenerator} を提供する. <br>
 * コンストラクタで所望の乱数発生器を与えることで動作する. <br>
 * 変則型については, 以下の説明を参照.
 * </p>
 * 
 * <p>
 * 標準Levy乱数はInvGamma(1/2,1/2),
 * すなわち, X &sim; SLevy のとき,
 * 1/(2X) &sim; SGamma(1/2)
 * となる. <br>
 * したがって, X &sim; SLevy, Y &sim; SLevy とすれば,
 * Z = 1/(2X) + 1/(2Y) &sim; SGamma(1)
 * となる.
 * </p>
 * 
 * @author Matsuura Y.
 */
final class TestedLevyRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();

    private final LevyRnd levyRnd;

    /**
     * テストする乱数発生器を与えてインスタンスを構築する.
     * 
     * @param levyRnd テストする乱数発生器
     * @throws NullPointerException 引数がnullの場合
     */
    TestedLevyRandomGenerator(LevyRnd levyRnd) {
        super();
        this.levyRnd = levyRnd;
    }

    @Override
    public double newValue() {
        double x = this.levyRnd.nextRandom(random);
        double y = this.levyRnd.nextRandom(random);

        return 0.5 / x + 0.5 / y;
    }

    @Override
    public double cumulativeProbability(double arg) {
        if (arg < 0) {
            return 0;
        }
        return 1 - Math.exp(-arg);
    }

}
