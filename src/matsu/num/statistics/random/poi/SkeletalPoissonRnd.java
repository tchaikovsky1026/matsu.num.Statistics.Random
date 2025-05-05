/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random.poi;

import matsu.num.statistics.random.PoissonRnd;

/**
 * {@link PoissonRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
public abstract class SkeletalPoissonRnd implements PoissonRnd {

    final double lambda;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param lambda パラメータ
     */
    SkeletalPoissonRnd(double lambda) {
        super();

        assert PoissonRnd.acceptsParameter(lambda) : "パラメータ不正";

        this.lambda = lambda;
    }

    @Override
    public double lambda() {
        return this.lambda;
    }

    @Override
    public String toString() {
        return String.format(
                "PoissonRnd(%s)", this.lambda());
    }
}
