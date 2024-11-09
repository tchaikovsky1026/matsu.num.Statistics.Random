/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.geo;

import matsu.num.statistics.random.GeometricRnd;

/**
 * {@link GeometricRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.2
 */
public abstract non-sealed class SkeletalGeometricRnd implements GeometricRnd {

    final double p;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param p 成功確率
     */
    SkeletalGeometricRnd(double p) {
        super();

        assert GeometricRnd.acceptsParameter(p) : "パラメータ不正";

        this.p = p;
    }

    @Override
    public final double successPobability() {
        return this.p;
    }

    @Override
    public String toString() {
        return String.format(
                "GeometricRnd(%s)", this.successPobability());
    }
}
