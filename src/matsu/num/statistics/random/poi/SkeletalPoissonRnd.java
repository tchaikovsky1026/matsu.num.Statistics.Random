/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.3
 */
package matsu.num.statistics.random.poi;

import matsu.num.statistics.random.PoissonRnd;

/**
 * {@link PoissonRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalPoissonRnd implements PoissonRnd {

    final double lambda;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param lambda パラメータ
     */
    SkeletalPoissonRnd(double lambda) {
        super();

        assert PoissonRnd.acceptsParameter(lambda) : "Illegal parameter.";

        this.lambda = lambda;
    }

    @Override
    public final double lambda() {
        return this.lambda;
    }

    @Override
    public String toString() {
        return String.format(
                "PoissonRnd(%s)", this.lambda());
    }

    /**
     * {@link matsu.num.statistics.random.PoissonRnd.Factory} の骨格実装.
     */
    static abstract class Factory implements PoissonRnd.Factory {

        /**
         * 唯一の外部に公開されないコンストラクタ.
         */
        Factory() {
            super();
        }

        @Override
        public final PoissonRnd instanceOf(double lambda) {
            if (!PoissonRnd.acceptsParameter(lambda)) {
                throw new IllegalArgumentException(
                        String.format(
                                "Illegal parameter: lambda = %s", lambda));
            }

            return this.createInstanceOf(lambda);
        }

        /**
         * {@link #instanceOf(double)} で返すインスタンスを生成するための抽象メソッド.
         * 
         * <p>
         * このメソッドは {@link #instanceOf(double)} の内部で呼ばれるために用意されており,
         * 外部から呼ぶことは許されず, 継承先でアクセス修飾子を緩めてはいけない. <br>
         * 内部から呼ばれる場合, 引数は必ず正当である.
         * </p>
         *
         * @param lambda パラメータ
         * @return パラメータが <i>&lambda;</i> のPoisson分布乱数発生器インスタンス
         */
        abstract PoissonRnd createInstanceOf(double lambda);

        @Override
        public String toString() {
            return "PoissonRnd.Factory";
        }
    }

}
