/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.weibull;

import matsu.num.statistics.random.WeibullRnd;

/**
 * {@link matsu.num.statistics.random.WeibullRnd.Factory} の骨格実装.
 * 
 * @author Matsuura Y.
 */
public abstract non-sealed class SkeletalWeibullRndFactory implements WeibullRnd.Factory {

    /**
     * 唯一の外部に公開されないコンストラクタ.
     */
    SkeletalWeibullRndFactory() {
        super();
    }

    @Override
    public final WeibullRnd instanceOf(double k) {
        if (!matsu.num.statistics.random.WeibullRnd.acceptsParameter(k)) {
            throw new IllegalArgumentException(
                    String.format(
                            "パラメータ不正:k=%s", k));
        }

        return this.createInstanceOf(k);
    }

    /**
     * {@link #instanceOf(double)} で返すインスタンスを生成するための抽象メソッド.
     * 
     * <p>
     * このメソッドは {@link #instanceOf(double)} の内部で呼ばれるために用意されており,
     * 外部から呼ぶことは許されず, 継承先でアクセス修飾子を緩めてはいけない. <br>
     * 内部から呼ばれる場合, 引数は必ず正当である.
     * </p>
     * 
     * @param k 形状パラメータ
     * @return 形状パラメータが <i>k</i> のWeibull分布乱数発生器インスタンス
     */
    abstract WeibullRnd createInstanceOf(double k);

    @Override
    public String toString() {
        return "WeibullRnd.Factory";
    }
}
