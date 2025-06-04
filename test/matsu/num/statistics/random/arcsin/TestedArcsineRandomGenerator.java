/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.arcsin;

import java.util.Objects;

import matsu.num.statistics.random.ArcsineRnd;
import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * Arcsine乱数に関する {@link TestedFloatingRandomGenerator} を提供する. <br>
 * コンストラクタで所望の乱数発生器を与えることで動作する.
 * 
 * @author Matsuura Y.
 */
final class TestedArcsineRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();

    private final ArcsineRnd arcsineRnd;

    /**
     * テストする乱数発生器を与えてインスタンスを構築する.
     * 
     * @param arcsineRnd テストする乱数発生器
     * @throws NullPointerException 引数がnullの場合
     */
    TestedArcsineRandomGenerator(ArcsineRnd arcsineRnd) {
        super();
        this.arcsineRnd = Objects.requireNonNull(arcsineRnd);
    }

    @Override
    public double newValue() {
        return arcsineRnd.nextRandom(random);
    }

    @Override
    public double cumulativeProbability(double arg) {
        if (arg <= 0d) {
            return 0d;
        }
        if (arg >= 1d) {
            return 1d;
        }

        return 0.5 + Math.asin(2 * arg - 1d) / Math.PI;
    }
}
