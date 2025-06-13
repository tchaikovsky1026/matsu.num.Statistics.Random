/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.8
 */
package matsu.num.statistics.random.tdist;

import matsu.num.statistics.random.TDistributionRnd;

/**
 * {@link TDistributionRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalTDistributionRnd implements TDistributionRnd {

    final double nu;

    /**
     * 唯一のコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないので,
     * このままモジュール外に公開してはいけない.
     * 
     * @param nu 自由度
     */
    SkeletalTDistributionRnd(double nu) {
        super();

        assert TDistributionRnd.acceptsParameter(nu) : "Illegal parameter.";

        this.nu = nu;
    }

    @Override
    public final double degreesOfFreedom() {
        return this.nu;
    }

    @Override
    public String toString() {
        return String.format(
                "TDistRnd(%s)", this.degreesOfFreedom());
    }

    /**
     * {@link matsu.num.statistics.random.TDistributionRnd.Factory} の骨格実装.
     */
    static abstract class Factory implements TDistributionRnd.Factory {

        /**
         * 唯一の外部に公開されないコンストラクタ.
         */
        Factory() {
            super();
        }

        @Override
        public final TDistributionRnd instanceOf(double nu) {
            if (!TDistributionRnd.acceptsParameter(nu)) {
                throw new IllegalArgumentException(
                        String.format(
                                "Illegal parameter: nu = %s", nu));
            }

            return this.createInstanceOf(nu);
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
         * @param nu 自由度
         * @return 自由度が <i>&nu;</i> のStudent-t分布乱数発生器インスタンス
         */
        abstract TDistributionRnd createInstanceOf(double nu);

        @Override
        public String toString() {
            return "TDistRnd.Factory";
        }
    }
}
