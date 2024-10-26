/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.25
 */
package matsu.num.statistics.random.fdist;

/**
 * {@link FDistributionRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.0
 */
abstract class SkeletalFDistributionRnd implements FDistributionRnd {

    protected final double d1;
    protected final double d2;

    /**
     * 唯一のコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param d1 分子自由度
     * @param d2 分母自由度
     */
    public SkeletalFDistributionRnd(double d1, double d2) {
        super();

        assert matsu.num.statistics.random.FDistributionRnd
                .acceptsParameters(d1, d2) : "パラメータ不正";

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
