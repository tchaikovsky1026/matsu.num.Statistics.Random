/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.gamma;

/**
 * {@link GammaRndSealed} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.1
 */
abstract class SkeletalGammaRnd implements GammaRndSealed {

    protected final double k;

    /**
     * 唯一のコンストラクタ. <br>
     * 与える形状パラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param k 形状パラメータ
     */
    protected SkeletalGammaRnd(double k) {
        super();

        assert matsu.num.statistics.random.GammaRnd.acceptsParameter(k) : "パラメータ不正";

        this.k = k;
    }

    @Override
    public final double shapeParameter() {
        return this.k;
    }

    @Override
    public String toString() {
        return String.format(
                "GammaRnd(%s)", this.shapeParameter());
    }
}
