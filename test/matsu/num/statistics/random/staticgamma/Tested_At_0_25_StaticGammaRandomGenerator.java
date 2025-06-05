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
 * 形状パラメータが 0.25 のStaticガンマ乱数に関する,
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
final class Tested_At_0_25_StaticGammaRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

    private final double k = 0.25d;
    private final StaticGammaRnd staticGammaRnd;

    /**
     * 乱数発生器を与えてインスタンスを構築する.
     * 
     * @param staticGammaRnd Staticガンマ乱数発生器
     * @throws NullPointerException 引数がnullの場合
     */
    Tested_At_0_25_StaticGammaRandomGenerator(StaticGammaRnd staticGammaRnd) {
        this.staticGammaRnd = Objects.requireNonNull(staticGammaRnd);
    }

    @Override
    public double newValue() {
        return staticGammaRnd.nextRandom(baseRandom, k)
                + staticGammaRnd.nextRandom(baseRandom, k)
                + staticGammaRnd.nextRandom(baseRandom, k)
                + staticGammaRnd.nextRandom(baseRandom, k);
    }

    @Override
    public double cumulativeProbability(double arg) {
        return 1 - Math.exp(-arg);
    }
}
