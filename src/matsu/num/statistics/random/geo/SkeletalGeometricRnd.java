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

/**
 * {@link GeometricRndSealed} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.1
 */
abstract class SkeletalGeometricRnd implements GeometricRndSealed {

    protected final double p;

    /**
     * 唯一のコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param p 成功確率
     */
    protected SkeletalGeometricRnd(double p) {
        super();

        assert matsu.num.statistics.random.GeometricRnd.acceptsParameter(p) : "パラメータ不正";

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
