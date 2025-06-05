/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.staticgamma;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * <p>
 * 形状パラメータが 1 のStaticガンマ乱数 (= 標準指数乱数) に関する,
 * {@link TestedFloatingRandomGenerator} を提供する. <br>
 * コンストラクタでファクトリを与えることで動作する.
 * </p>
 * 
 * @author Matsuura Y.
 */
final class Tested_At_1_StaticGammaRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

    private final double k = 1d;
    private final StaticGammaRnd staticGammaRnd;

    /**
     * 乱数発生器を与えてインスタンスを構築する.
     * 
     * @param staticGammaRnd Staticガンマ乱数発生器
     * @throws NullPointerException 引数がnullの場合
     */
    Tested_At_1_StaticGammaRandomGenerator(StaticGammaRnd staticGammaRnd) {
        this.staticGammaRnd = Objects.requireNonNull(staticGammaRnd);
    }

    @Override
    public double newValue() {
        return staticGammaRnd.nextRandom(baseRandom, k);
    }

    @Override
    public double cumulativeProbability(double arg) {
        return 1 - Math.exp(-arg);
    }
}
