/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.staticbeta;

import java.util.Objects;
import java.util.function.BiFunction;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.StaticBetaRnd;

/**
 * {@link StaticBetaRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
abstract class SkeletalStaticBetaRnd implements StaticBetaRnd {

    private static final BiFunction<Double, Double, IllegalArgumentException> exceptionGetter =
            (a, b) -> new IllegalArgumentException(String.format("パラメータ不正:a=%s, b=%s", a, b));

    /**
     * 唯一のコンストラクタ.
     */
    protected SkeletalStaticBetaRnd() {
        super();
    }

    @Override
    public final double nextRandom(BaseRandom random, double a, double b) {
        Objects.requireNonNull(random);
        if (!StaticBetaRnd.acceptsParameters(a, b)) {
            throw exceptionGetter.apply(a, b);
        }

        return this.calcNextBeta(random, a, b);
    }

    /**
     * {@link #nextRandom(BaseRandom, double, double)} で返す値を計算するための抽象メソッド.
     * 
     * <p>
     * このメソッドは {@link #nextRandom(BaseRandom, double, double)}
     * の内部で呼ばれるために用意されており,
     * 外部から呼ぶことは許されず, 継承先でアクセス修飾子を緩めてはいけない. <br>
     * 内部から呼ばれる場合, 引数は必ず正当である.
     * </p>
     * 
     * @param random 基本乱数発生器
     * @param a 形状パラメータ
     * @param b 形状パラメータ
     * @return 形状パラメータが (<i>a</i>, <i>b</i>) のベータ分布に従う乱数の値
     */
    abstract double calcNextBeta(BaseRandom random, double a, double b);

    @Override
    public final double nextBetaPrime(BaseRandom random, double a, double b) {
        Objects.requireNonNull(random);
        if (!StaticBetaRnd.acceptsParameters(a, b)) {
            throw exceptionGetter.apply(a, b);
        }

        return this.calcNextBetaPrime(random, a, b);
    }

    /**
     * {@link #nextBetaPrime(BaseRandom, double, double)} で返す値を計算するための抽象メソッド.
     * 
     * <p>
     * このメソッドは {@link #nextBetaPrime(BaseRandom, double, double)}
     * の内部で呼ばれるために用意されており,
     * 外部から呼ぶことは許されず, 継承先でアクセス修飾子を緩めてはいけない. <br>
     * 内部から呼ばれる場合, 引数は必ず正当である.
     * </p>
     * 
     * @param random 基本乱数発生器
     * @param a 形状パラメータ
     * @param b 形状パラメータ
     * @return 形状パラメータが (<i>a</i>, <i>b</i>) のベータプライム分布に従う乱数の値
     */
    protected abstract double calcNextBetaPrime(BaseRandom random, double a, double b);

    @Override
    public String toString() {
        return "StaticBetaRnd";
    }
}
