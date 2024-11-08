/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.poi;

/**
 * {@link PoissonRndSealed} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.1
 */
abstract class SkeletalPoissonRnd implements PoissonRndSealed {

    protected final double lambda;

    /**
     * 唯一のコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param lambda パラメータ
     */
    protected SkeletalPoissonRnd(double lambda) {
        super();

        assert matsu.num.statistics.random.PoissonRnd.acceptsParameter(lambda) : "パラメータ不正";

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
