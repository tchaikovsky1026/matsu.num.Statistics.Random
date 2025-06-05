/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.logi;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.LogisticRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * ロジスティック乱数に関する {@link TestedFloatingRandomGenerator} を提供する. <br>
 * コンストラクタで所望の乱数発生器を与えることで動作する.
 * 
 * @author Matsuura Y.
 */
final class TestedLogisticRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();

    private final LogisticRnd logisticRnd;

    /**
     * テストする乱数発生器を与えてインスタンスを構築する.
     * 
     * @param logisticRnd テストする乱数発生器
     * @throws NullPointerException 引数がnullの場合
     */
    TestedLogisticRandomGenerator(LogisticRnd logisticRnd) {
        super();
        this.logisticRnd = Objects.requireNonNull(logisticRnd);
    }

    @Override
    public double newValue() {
        return logisticRnd.nextRandom(random);
    }

    @Override
    public double cumulativeProbability(double arg) {
        return 1 / (1 + Math.exp(-arg));
    }
}
