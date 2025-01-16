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
 * {@link WeibullRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
public abstract non-sealed class SkeletalWeibullRnd implements WeibullRnd {

    final double k;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与える形状パラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param k 形状パラメータ
     */
    SkeletalWeibullRnd(double k) {
        super();

        assert WeibullRnd.acceptsParameter(k) : "パラメータ不正";

        this.k = k;
    }

    @Override
    public final double shapeParameter() {
        return this.k;
    }

    @Override
    public String toString() {
        return String.format(
                "WeibullRnd(%s)", this.shapeParameter());
    }
}
