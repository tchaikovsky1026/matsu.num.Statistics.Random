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
 * 形状パラメータが 4 のガンマ乱数に関する,
 * {@link TestedFloatingRandomGenerator} を提供する. <br>
 * コンストラクタでファクトリを与えることで動作する.
 * </p>
 * 
 * <p>
 * 累積分布関数は, <br>
 * 1 - e^(-x) (1 + x + (1/2) * x^2 + (1/6)x^3)
 * </p>
 * 
 * @author Matsuura Y.
 */
final class Tested_At_4_GammaRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

    private final GammaRnd gammaRnd_4;

    /**
     * ファクトリを与えてインスタンスを構築する.
     * 
     * @param factory ファクトリ
     * @throws NullPointerException 引数がnullの場合
     */
    Tested_At_4_GammaRandomGenerator(GammaRnd.Factory factory) {
        this.gammaRnd_4 = factory.instanceOf(4);
    }

    @Override
    public double newValue() {
        return gammaRnd_4.nextRandom(baseRandom);
    }

    @Override
    public double cumulativeProbability(double arg) {
        double arg2 = arg * arg;
        double arg3 = arg2 * arg;
        return 1 - Math.exp(-arg) * (1 + arg + 0.5 * arg2 + (1.0 / 6.0) * arg3);
    }
}
