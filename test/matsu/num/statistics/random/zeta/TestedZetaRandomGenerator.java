/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.zeta;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.TestedIntegerRandomGenerator;
import matsu.num.statistics.random.ZetaRnd;

/**
 * Zeta乱数に関する {@link TestedIntegerRandomGenerator} を提供する. <br>
 * コンストラクタで所望の乱数発生器を与えることで動作する.
 * 
 * @author Matsuura Y.
 */
final class TestedZetaRandomGenerator implements TestedIntegerRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();

    private final ZetaRnd zetaRnd;

    /**
     * テストする乱数発生器を与えてインスタンスを構築する.
     * 
     * @param zetaRnd テストする乱数発生器
     * @throws NullPointerException 引数がnullの場合
     */
    TestedZetaRandomGenerator(ZetaRnd zetaRnd) {

        super();
        this.zetaRnd = zetaRnd;
    }

    @Override
    public int newValue() {
        return zetaRnd.nextRandom(random);
    }

    @Override
    public double cumulativeProbability(int arg) {
        double zetaS = RiemannZetaFunction.at(zetaRnd.s());

        double sum = 0d;

        for (int n = 1; n <= arg; n++) {
            sum += Math.pow(1d / n, zetaRnd.s());
        }
        return sum / zetaS;
    }
}
