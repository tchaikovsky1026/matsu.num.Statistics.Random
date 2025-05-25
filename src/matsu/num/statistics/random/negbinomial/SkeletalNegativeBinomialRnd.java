/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.24
 */
package matsu.num.statistics.random.negbinomial;

import matsu.num.statistics.random.NegativeBinomialRnd;

/**
 * {@link NegativeBinomialRnd} の骨格実装を扱う.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalNegativeBinomialRnd implements NegativeBinomialRnd {

    final int r;
    final double p;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     */
    SkeletalNegativeBinomialRnd(int r, double p) {
        super();
        this.r = r;
        this.p = p;
    }

    @Override
    public final int numberOfSuccesses() {
        return this.r;
    }

    @Override
    public final double successPobability() {
        return this.p;
    }

    @Override
    public String toString() {
        return String.format(
                "NegativeBinomialRnd(r = %s, p = %s)",
                this.numberOfSuccesses(), this.successPobability());
    }

    /**
     * {@link NegativeBinomialRnd.Factory} の骨格実装.
     */
    static abstract class Factory implements NegativeBinomialRnd.Factory {

        /**
         * 唯一の外部に公開されないコンストラクタ.
         */
        Factory() {
            super();
        }

        @Override
        public final NegativeBinomialRnd instanceOf(int r, double p) {
            if (!NegativeBinomialRnd.acceptsParameters(r, p)) {
                throw new IllegalArgumentException(
                        String.format(
                                "パラメータ不正:r=%s, p=%s",
                                r, p));
            }

            return this.createInstanceOf(r, p);
        }

        /**
         * {@link #instanceOf(int, double)} で返すインスタンスを生成するための抽象メソッド.
         * 
         * <p>
         * このメソッドは {@link #instanceOf(int, double)} の内部で呼ばれるために用意されており,
         * 外部から呼ぶことは許されず, 継承先でアクセス修飾子を緩めてはいけない. <br>
         * 内部から呼ばれる場合, 引数は必ず正当である.
         * </p>
         */
        abstract NegativeBinomialRnd createInstanceOf(int r, double p);

        @Override
        public String toString() {
            return "NegativeBinomialRnd.Factory";
        }
    }
}
