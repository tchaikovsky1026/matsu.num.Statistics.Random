/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random.staticgamma;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.StaticGammaRnd;

/**
 * {@link StaticGammaRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
public abstract class SkeletalStaticGammaRnd implements StaticGammaRnd {

    /**
     * 唯一のコンストラクタ.
     */
    SkeletalStaticGammaRnd() {
        super();
    }

    @Override
    public final double nextRandom(BaseRandom random, double k) {
        Objects.requireNonNull(random);
        if (!matsu.num.statistics.random.StaticGammaRnd.acceptsParameter(k)) {
            throw new IllegalArgumentException(String.format("パラメータ不正:k=%s", k));
        }

        return this.calcNextGamma(random, k);
    }

    /**
     * {@link #nextRandom(BaseRandom, double)} で返す値を計算するための抽象メソッド.
     * 
     * <p>
     * このメソッドは {@link #nextRandom(BaseRandom, double)}
     * の内部で呼ばれるために用意されており,
     * 外部から呼ぶことは許されず, 継承先でアクセス修飾子を緩めてはいけない. <br>
     * 内部から呼ばれる場合, 引数は必ず正当である.
     * </p>
     * 
     * @param random 基本乱数発生器
     * @param k 形状パラメータ
     * @return 形状パラメータが <i>k</i> の標準ガンマ分布に従う乱数の値
     */
    abstract double calcNextGamma(BaseRandom random, double k);

    @Override
    public String toString() {
        return "StaticGammaRnd";
    }
}
