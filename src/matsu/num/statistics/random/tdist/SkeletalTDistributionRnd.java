/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.tdist;

/**
 * {@link TDistributionRndSealed} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.1
 */
public abstract class SkeletalTDistributionRnd implements TDistributionRndSealed {

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
