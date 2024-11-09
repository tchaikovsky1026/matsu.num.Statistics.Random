/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.chisq;

import matsu.num.statistics.random.ChiSquaredRnd;

/**
 * {@link ChiSquaredRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.2
 */
public abstract non-sealed class SkeletalChiSquaredRnd implements ChiSquaredRnd {

    final double k;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param k 自由度
     */
    SkeletalChiSquaredRnd(double k) {
        super();

        assert ChiSquaredRnd.acceptsParameter(k) : "パラメータ不正";

        this.k = k;
    }

    @Override
    public final double degreesOfFreedom() {
        return this.k;
    }

    @Override
    public String toString() {
        return String.format(
                "ChiSquaredRnd(%s)", this.degreesOfFreedom());
    }
}
