/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.7
 */
package matsu.num.statistics.random.geo;

import matsu.num.statistics.random.GeometricRnd;

/**
 * {@link GeometricRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalGeometricRnd implements GeometricRnd {

    final double p;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param p 成功確率
     */
    SkeletalGeometricRnd(double p) {
        super();

        assert GeometricRnd.acceptsParameter(p) : "パラメータ不正";

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

    /**
     * {@link matsu.num.statistics.random.GeometricRnd.Factory} の骨格実装
     * 
     * @author Matsuura Y.
     */
    static abstract class Factory implements GeometricRnd.Factory {

        /**
         * 唯一のコンストラクタ.
         */
        Factory() {
            super();
        }

        @Override
        public final GeometricRnd instanceOf(double p) {
            if (!GeometricRnd.acceptsParameter(p)) {
                throw new IllegalArgumentException(String.format("パラメータ不正:p=%s", p));
            }

            return this.createInstanceOf(p);
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
         * @param p 成功確率
         * @return 成功確率がpの幾何分布乱数発生器インスタンス
         */
        abstract GeometricRnd createInstanceOf(double p);

        @Override
        public String toString() {
            return "GeometricRnd.Factory";
        }
    }
}
