/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.beta;

import matsu.num.statistics.random.BetaRnd;

/**
 * {@link matsu.num.statistics.random.BetaRnd.Factory} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.2
 */
public abstract non-sealed class SkeletalBetaRndFactory implements BetaRnd.Factory {

    /**
     * 唯一の外部に公開されないコンストラクタ.
     */
    SkeletalBetaRndFactory() {
        super();
    }

    @Override
    public final BetaRnd instanceOf(double a, double b) {
        if (!BetaRnd.acceptsParameters(a, b)) {
            throw new IllegalArgumentException(
                    String.format("パラメータ不正:a=%s, b=%s", a, b));
        }

        return this.createInstanceOf(a, b);
    }

    /**
     * {@link #instanceOf(double, double)} で返すインスタンスを生成するための抽象メソッド.
     * 
     * <p>
     * このメソッドは {@link #instanceOf(double, double)} の内部で呼ばれるために用意されており,
     * 外部から呼ぶことは許されず, 継承先でアクセス修飾子を緩めてはいけない. <br>
     * 内部から呼ばれる場合, 引数は必ず正当である.
     * </p>
     * 
     * @param a 形状パラメータ
     * @param b 形状パラメータ
     * @return 形状パラメータが (<i>a</i>, <i>b</i>) のベータ分布乱数発生器インスタンス
     */
    abstract BetaRnd createInstanceOf(double a, double b);

    @Override
    public String toString() {
        return "BetaRnd.Factory";
    }
}
