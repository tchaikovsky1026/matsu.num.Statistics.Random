/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.6
 */
package matsu.num.statistics.random.beta;

import matsu.num.statistics.random.BetaRnd;

/**
 * {@link BetaRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalBetaRnd implements BetaRnd {

    final double a;
    final double b;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与える形状パラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param a 形状パラメータa
     * @param b 形状パラメータb
     */
    SkeletalBetaRnd(double a, double b) {
        super();

        assert BetaRnd.acceptsParameters(a, b) : "Illegal parameter.";

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

    /**
     * {@link matsu.num.statistics.random.BetaRnd.Factory} の骨格実装.
     */
    static abstract class Factory implements BetaRnd.Factory {

        /**
         * 唯一の外部に公開されないコンストラクタ.
         */
        Factory() {
            super();
        }

        @Override
        public final BetaRnd instanceOf(double a, double b) {
            if (!BetaRnd.acceptsParameters(a, b)) {
                throw new IllegalArgumentException(
                        String.format("Illegal parameter: a = %s, b = %s", a, b));
            }

            return this.createInstanceOf(a, b);
        }

        /**
         * {@link #instanceOf(double, double)} で返すインスタンスを生成するための抽象メソッド.
         * 
         * <p>
         * このメソッドは {@link #instanceOf(double, double)} の内部で呼ばれるために用意されており,
         * 外部から呼ぶことは許されず, 継承先でアクセス修飾子を緩めてはいけない. <br>
         * 内部から呼ばれる場合, 引数は必ず正当である.
         * </p>
         * 
         * @param a 形状パラメータ
         * @param b 形状パラメータ
         * @return 形状パラメータが (<i>a</i>, <i>b</i>) のベータ分布乱数発生器インスタンス
         */
        abstract BetaRnd createInstanceOf(double a, double b);

        @Override
        public String toString() {
            return "BetaRnd.Factory";
        }
    }
}
