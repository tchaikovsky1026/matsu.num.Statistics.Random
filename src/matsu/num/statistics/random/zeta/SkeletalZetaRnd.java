/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.3
 */
package matsu.num.statistics.random.zeta;

import matsu.num.statistics.random.ZetaRnd;

/**
 * {@link ZetaRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalZetaRnd implements ZetaRnd {

    final double s;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param s パラメータ
     */
    SkeletalZetaRnd(double s) {
        super();
        this.s = s;
    }

    @Override
    public final double s() {
        return this.s;
    }

    @Override
    public String toString() {
        return String.format(
                "ZetaRnd(%s)", this.s());
    }

    /**
     * {@link matsu.num.statistics.random.ZetaRnd.Factory} の骨格実装.
     */
    static abstract class Factory implements ZetaRnd.Factory {

        /**
         * 唯一の外部に公開されないコンストラクタ.
         */
        Factory() {
            super();
        }

        @Override
        public final ZetaRnd instanceOf(double s) {
            if (!ZetaRnd.acceptsParameter(s)) {
                throw new IllegalArgumentException(
                        String.format(
                                "Illegal parameter: s = %s", s));
            }

            return this.createInstanceOf(s);
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
         * @param s パラメータ
         * @return パラメータが <i>s</i> のゼータ分布乱数発生器インスタンス
         */
        abstract ZetaRnd createInstanceOf(double s);

        @Override
        public String toString() {
            return "ZetaRnd.Factory";
        }
    }
}
