/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random.fdist;

import matsu.num.statistics.random.FDistributionRnd;

/**
 * {@link FDistributionRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalFDistributionRnd implements FDistributionRnd {

    final double d1;
    final double d2;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param d1 分子自由度
     * @param d2 分母自由度
     */
    SkeletalFDistributionRnd(double d1, double d2) {
        super();

        assert FDistributionRnd.acceptsParameters(d1, d2) : "パラメータ不正";

        this.d1 = d1;
        this.d2 = d2;
    }

    @Override
    public final double numeratorDegreesOfFreedom() {
        return this.d1;
    }

    @Override
    public final double denominatorDegreesOfFreedom() {
        return this.d2;
    }

    @Override
    public String toString() {
        return String.format(
                "FDistRnd(%s, %s)", this.numeratorDegreesOfFreedom(), this.denominatorDegreesOfFreedom());
    }
}
