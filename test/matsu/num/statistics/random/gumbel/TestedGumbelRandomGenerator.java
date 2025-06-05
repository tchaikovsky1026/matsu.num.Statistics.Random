/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.gumbel;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * Gumbel 乱数に関する {@link TestedFloatingRandomGenerator} を提供する. <br>
 * コンストラクタで所望の乱数発生器を与えることで動作する.
 * 
 * @author Matsuura Y.
 */
final class TestedGumbelRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();

    private final GumbelRnd gumbelRnd;

    /**
     * テストする乱数発生器を与えてインスタンスを構築する.
     * 
     * @param cauchyRnd テストする乱数発生器
     * @throws NullPointerException 引数がnullの場合
     */
    TestedGumbelRandomGenerator(GumbelRnd gumbelRnd) {
        super();
        this.gumbelRnd = Objects.requireNonNull(gumbelRnd);
    }

    @Override
    public double newValue() {
        return gumbelRnd.nextRandom(random);
    }

    @Override
    public double cumulativeProbability(double arg) {
        return Math.exp(-Math.exp(-arg));
    }
}
