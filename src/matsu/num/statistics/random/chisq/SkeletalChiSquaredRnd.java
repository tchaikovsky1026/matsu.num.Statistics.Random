/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random.chisq;

import matsu.num.statistics.random.ChiSquaredRnd;

/**
 * {@link ChiSquaredRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalChiSquaredRnd implements ChiSquaredRnd {

    final double k;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param k 自由度
     */
    SkeletalChiSquaredRnd(double k) {
        super();

        assert ChiSquaredRnd.acceptsParameter(k) : "パラメータ不正";

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

    /**
     * {@link matsu.num.statistics.random.ChiSquaredRnd.Factory} の骨格実装.
     * 
     * @author Matsuura Y.
     */
    static abstract class Factory implements ChiSquaredRnd.Factory {

        /**
         * 唯一の外部に公開されないコンストラクタ.
         */
        protected Factory() {
            super();
        }

        @Override
        public final ChiSquaredRnd instanceOf(double k) {
            if (!ChiSquaredRnd.acceptsParameter(k)) {
                throw new IllegalArgumentException(
                        String.format("パラメータ不正:k=%s", k));
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
         * @param k 自由度
         * @return 自由度が <i>k</i> のカイ二乗分布乱数発生器インスタンス
         */
        abstract ChiSquaredRnd createInstanceOf(double k);

        @Override
        public String toString() {
            return "ChiSquaredRnd.Factory";
        }
    }
}
