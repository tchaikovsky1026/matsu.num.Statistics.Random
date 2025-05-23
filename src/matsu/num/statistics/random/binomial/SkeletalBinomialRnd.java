/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.13
 */
package matsu.num.statistics.random.binomial;

import matsu.num.statistics.random.BinomialRnd;

/**
 * {@link BinomialRnd} の骨格実装を扱う.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalBinomialRnd implements BinomialRnd {

    final int n;
    final double p;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     */
    SkeletalBinomialRnd(int n, double p) {
        super();
        this.n = n;
        this.p = p;
    }

    @Override
    public final int numberOfTrials() {
        return this.n;
    }

    @Override
    public final double successPobability() {
        return this.p;
    }

    @Override
    public String toString() {
        return String.format(
                "BinomialRnd(n = %s, p = %s)",
                this.numberOfTrials(), this.successPobability());
    }

    /**
     * {@link BinomialRnd.Factory} の骨格実装.
     */
    static abstract class Factory implements BinomialRnd.Factory {

        /**
         * 唯一の外部に公開されないコンストラクタ.
         */
        Factory() {
            super();
        }

        @Override
        public final BinomialRnd instanceOf(int n, double p) {
            if (!BinomialRnd.acceptsParameters(n, p)) {
                throw new IllegalArgumentException(
                        String.format(
                                "パラメータ不正:n=%s, p=%s",
                                n, p));
            }

            return this.createInstanceOf(n, p);
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
        abstract BinomialRnd createInstanceOf(int n, double p);

        @Override
        public String toString() {
            return "BinomialRnd.Factory";
        }
    }
}
