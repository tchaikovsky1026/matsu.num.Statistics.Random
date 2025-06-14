/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random.gamma;

import matsu.num.statistics.random.GammaRnd;

/**
 * {@link GammaRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalGammaRnd implements GammaRnd {

    final double k;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与える形状パラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param k 形状パラメータ
     */
    SkeletalGammaRnd(double k) {
        super();

        assert GammaRnd.acceptsParameter(k) : "Illegal parameter.";

        this.k = k;
    }

    @Override
    public final double shapeParameter() {
        return this.k;
    }

    @Override
    public String toString() {
        return String.format(
                "GammaRnd(k = %s)", this.shapeParameter());
    }

    /**
     * {@link matsu.num.statistics.random.GammaRnd.Factory} の骨格実装.
     */
    static abstract class Factory implements GammaRnd.Factory {

        /**
         * 唯一の外部に公開されないコンストラクタ.
         */
        Factory() {
            super();
        }

        @Override
        public final GammaRnd instanceOf(double k) {
            if (!GammaRnd.acceptsParameter(k)) {
                throw new IllegalArgumentException(
                        String.format(
                                "Illegal parameter: k = %s", k));
            }

            return this.createInstanceOf(k);
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
         * @param k 形状パラメータ
         * @return 形状パラメータが <i>k</i> のガンマ分布乱数発生器インスタンス
         */
        abstract GammaRnd createInstanceOf(double k);

        @Override
        public String toString() {
            return "GammaRnd.Factory";
        }
    }
}
