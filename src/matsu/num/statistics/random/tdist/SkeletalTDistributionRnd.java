/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.26
 */
package matsu.num.statistics.random.tdist;

/**
 * {@link TDistributionRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.0
 */
public abstract class SkeletalTDistributionRnd implements TDistributionRnd {

    protected final double nu;

    /**
     * 唯一のコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param nu 自由度
     */
    protected SkeletalTDistributionRnd(double nu) {
        super();

        assert matsu.num.statistics.random.TDistributionRnd.acceptsParameter(nu) : "パラメータ不正";

        this.nu = nu;
    }

    @Override
    public final double degreesOfFreedom() {
        return this.nu;
    }

    @Override
    public String toString() {
        return String.format(
                "TDistRnd(%s)", this.degreesOfFreedom());
    }
}
