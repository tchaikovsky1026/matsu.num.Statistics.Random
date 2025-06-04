/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.exp;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * Exp乱数に関する {@link TestedFloatingRandomGenerator} を提供する. <br>
 * コンストラクタで所望の乱数発生器を与えることで動作する.
 * 
 * @author Matsuura Y.
 */
final class TestedExponentialRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();
    private final ExponentialRnd exponentialRnd;

    /**
     * テストする乱数発生器を与えてインスタンスを構築する.
     * 
     * @param exponentialRnd テストする乱数発生器
     * @throws NullPointerException 引数がnullの場合
     */
    TestedExponentialRandomGenerator(ExponentialRnd exponentialRnd) {
        super();
        this.exponentialRnd = Objects.requireNonNull(exponentialRnd);
    }

    @Override
    public double newValue() {
        return exponentialRnd.nextRandom(random);
    }

    @Override
    public double cumulativeProbability(double arg) {
        return 1 - Math.exp(-arg);
    }
}
