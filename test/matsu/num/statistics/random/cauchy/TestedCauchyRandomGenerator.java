/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.cauchy;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * Cauchy 乱数に関する {@link TestedFloatingRandomGenerator} を提供する. <br>
 * コンストラクタで所望の乱数発生器を与えることで動作する.
 * 
 * @author Matsuura Y.
 */
final class TestedCauchyRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();

    private final CauchyRnd cauchyRnd;

    /**
     * テストする乱数発生器を与えてインスタンスを構築する.
     * 
     * @param cauchyRnd テストする乱数発生器
     * @throws NullPointerException 引数がnullの場合
     */
    TestedCauchyRandomGenerator(CauchyRnd cauchyRnd) {
        super();
        this.cauchyRnd = Objects.requireNonNull(cauchyRnd);
    }

    @Override
    public double newValue() {
        return cauchyRnd.nextRandom(random);
    }

    @Override
    public double cumulativeProbability(double arg) {
        return 0.5 + Math.atan(arg) / Math.PI;
    }
}
