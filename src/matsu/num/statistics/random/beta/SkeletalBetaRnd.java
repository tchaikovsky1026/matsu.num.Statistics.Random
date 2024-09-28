/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.beta;

import matsu.num.statistics.random.BetaRnd;

/**
 * {@link BetaRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
abstract class SkeletalBetaRnd implements BetaRnd {

    protected final double a;
    protected final double b;

    /**
     * 唯一のコンストラクタ. <br>
     * 与える形状パラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param a 形状パラメータa
     * @param b 形状パラメータb
     */
    protected SkeletalBetaRnd(double a, double b) {
        super();

        assert BetaRnd.acceptsParameters(a, b) : "パラメータ不正";

        this.a = a;
        this.b = b;
    }

    @Override
    public final double shapeA() {
        return this.a;
    }

    @Override
    public final double shapeB() {
        return this.b;
    }

    @Override
    public String toString() {
        return String.format(
                "BetaRnd(%s, %s)", this.shapeA(), this.shapeB());
    }
}
