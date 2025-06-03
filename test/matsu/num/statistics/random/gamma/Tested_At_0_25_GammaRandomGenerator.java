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
 * 形状パラメータが 0.25 のガンマ乱数に関する,
 * 変則型の {@link TestedFloatingRandomGenerator} を提供する. <br>
 * コンストラクタでファクトリを与えることで動作する.
 * </p>
 * 
 * <p>
 * k = 0.25 の累積分布関数を計算するのは難しい. <br>
 * そこで, 4つの乱数を発生させ, その和の累積分布を考える. <br>
 * ガンマ分布の再生性により, これは k = 1 に一致し,
 * 標準指数分布となる.
 * </p>
 * 
 * @author Matsuura Y.
 */
final class Tested_At_0_25_GammaRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

    private final GammaRnd gammaRnd_0_25;

    /**
     * ファクトリを与えてインスタンスを構築する.
     * 
     * @param factory ファクトリ
     * @throws NullPointerException 引数がnullの場合
     */
    Tested_At_0_25_GammaRandomGenerator(GammaRnd.Factory factory) {
        this.gammaRnd_0_25 = factory.instanceOf(0.25);
    }

    @Override
    public double newValue() {
        return gammaRnd_0_25.nextRandom(baseRandom)
                + gammaRnd_0_25.nextRandom(baseRandom)
                + gammaRnd_0_25.nextRandom(baseRandom)
                + gammaRnd_0_25.nextRandom(baseRandom);
    }

    @Override
    public double cumulativeProbability(double arg) {
        return 1 - Math.exp(-arg);
    }
}
