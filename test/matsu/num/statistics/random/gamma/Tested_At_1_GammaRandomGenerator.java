/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.gamma;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * <p>
 * 形状パラメータが 1 のガンマ乱数 (= 標準指数乱数) に関する,
 * {@link TestedFloatingRandomGenerator} を提供する. <br>
 * コンストラクタでファクトリを与えることで動作する.
 * </p>
 * 
 * @author Matsuura Y.
 */
final class Tested_At_1_GammaRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

    private final GammaRnd gammaRnd_1;

    /**
     * ファクトリを与えてインスタンスを構築する.
     * 
     * @param factory ファクトリ
     * @throws NullPointerException 引数がnullの場合
     */
    Tested_At_1_GammaRandomGenerator(GammaRnd.Factory factory) {
        this.gammaRnd_1 = factory.instanceOf(1);
    }

    @Override
    public double newValue() {
        return gammaRnd_1.nextRandom(baseRandom);
    }

    @Override
    public double cumulativeProbability(double arg) {
        return 1 - Math.exp(-arg);
    }
}
