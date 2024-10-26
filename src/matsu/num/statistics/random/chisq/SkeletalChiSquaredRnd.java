/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.25
 */
package matsu.num.statistics.random.chisq;

/**
 * {@link ChiSquaredRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.0
 */
abstract class SkeletalChiSquaredRnd implements ChiSquaredRnd {

    protected final double k;

    /**
     * 唯一のコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param k 自由度
     */
    protected SkeletalChiSquaredRnd(double k) {
        super();

        assert matsu.num.statistics.random.ChiSquaredRnd.acceptsParameter(k) : "パラメータ不正";

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
