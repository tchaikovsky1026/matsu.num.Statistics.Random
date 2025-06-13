/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.10
 */
package matsu.num.statistics.random.logseries;

import matsu.num.statistics.random.LogarithmicSeriesRnd;

/**
 * {@link LogarithmicSeriesRnd} の骨格実装を扱う.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalLogarithmicSeriesRnd implements LogarithmicSeriesRnd {

    final double p;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     */
    SkeletalLogarithmicSeriesRnd(double p) {
        super();
        this.p = p;
    }

    @Override
    public final double p() {
        return this.p;
    }

    @Override
    public String toString() {
        return String.format(
                "LogarithmicSeriesRnd(p = %s)", this.p());
    }

    /**
     * {@link matsu.num.statistics.random.LogarithmicSeriesRnd.Factory} の骨格実装.
     */
    static abstract class Factory implements LogarithmicSeriesRnd.Factory {

        /**
         * 唯一の外部に公開されないコンストラクタ.
         */
        Factory() {
            super();
        }

        @Override
        public final LogarithmicSeriesRnd instanceOf(double p) {
            if (!LogarithmicSeriesRnd.acceptsParameter(p)) {
                throw new IllegalArgumentException(
                        String.format(
                                "Illegal parameter: p = %s", p));
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
         * @param p パラメータ
         * @return パラメータが <i>p</i> の対数級数分布乱数発生器インスタンス
         */
        abstract LogarithmicSeriesRnd createInstanceOf(double p);

        @Override
        public String toString() {
            return "LogarithmicSeriesRnd.Factory";
        }
    }
}
