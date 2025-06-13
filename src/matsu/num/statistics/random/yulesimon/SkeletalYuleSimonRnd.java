/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.3
 */
package matsu.num.statistics.random.yulesimon;

import matsu.num.statistics.random.YuleSimonRnd;

/**
 * {@link YuleSimonRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalYuleSimonRnd implements YuleSimonRnd {

    final double rho;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param rho パラメータ
     */
    SkeletalYuleSimonRnd(double rho) {
        super();
        this.rho = rho;
    }

    @Override
    public final double rho() {
        return this.rho;
    }

    @Override
    public String toString() {
        return String.format(
                "YuleSimonRnd(%s)", this.rho());
    }

    /**
     * {@link matsu.num.statistics.random.YuleSimonRnd.Factory} の骨格実装.
     */
    static abstract class Factory implements YuleSimonRnd.Factory {

        /**
         * 唯一の外部に公開されないコンストラクタ.
         */
        Factory() {
            super();
        }

        @Override
        public final YuleSimonRnd instanceOf(double rho) {
            if (!YuleSimonRnd.acceptsParameter(rho)) {
                throw new IllegalArgumentException(
                        String.format(
                                "Illegal parameter: rho = %s", rho));
            }

            return this.createInstanceOf(rho);
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
         * @param rho パラメータ
         * @return パラメータが <i>&rho;</i> の Yule-Simon 分布乱数発生器インスタンス
         */
        abstract YuleSimonRnd createInstanceOf(double rho);

        @Override
        public String toString() {
            return "YuleSimonRnd.Factory";
        }
    }
}
