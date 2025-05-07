/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random.fdist;

import matsu.num.statistics.random.FDistributionRnd;

/**
 * {@link FDistributionRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalFDistributionRnd implements FDistributionRnd {

    final double d1;
    final double d2;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param d1 分子自由度
     * @param d2 分母自由度
     */
    SkeletalFDistributionRnd(double d1, double d2) {
        super();

        assert FDistributionRnd.acceptsParameters(d1, d2) : "パラメータ不正";

        this.d1 = d1;
        this.d2 = d2;
    }

    @Override
    public final double numeratorDegreesOfFreedom() {
        return this.d1;
    }

    @Override
    public final double denominatorDegreesOfFreedom() {
        return this.d2;
    }

    @Override
    public String toString() {
        return String.format(
                "FDistRnd(%s, %s)", this.numeratorDegreesOfFreedom(), this.denominatorDegreesOfFreedom());
    }

    /**
     * {@link matsu.num.statistics.random.FDistributionRnd.Factory} の骨格実装.
     */
    static abstract class Factory implements FDistributionRnd.Factory {

        /**
         * 唯一の外部に公開されないコンストラクタ.
         */
        Factory() {
            super();
        }

        @Override
        public final FDistributionRnd instanceOf(double d1, double d2) {
            if (!FDistributionRnd.acceptsParameters(d1, d2)) {
                throw new IllegalArgumentException(
                        String.format(
                                "パラメータ不正:d1 = %s, d2 = %s", d1, d2));
            }

            return this.createInstanceOf(d1, d2);
        }

        /**
         * {@link #instanceOf(double, double)} で返すインスタンスを生成するための抽象メソッド.
         * 
         * <p>
         * このメソッドは {@link #instanceOf(double,double)} の内部で呼ばれるために用意されており,
         * 外部から呼ぶことは許されず, 継承先でアクセス修飾子を緩めてはいけない. <br>
         * 内部から呼ばれる場合, 引数は必ず正当である.
         * </p>
         * 
         * @param d1 分子自由度
         * @param d2 分母自由度
         * @return 自由度が (<i>d</i><sub>1</sub>, <i>d</i><sub>2</sub>)
         *             のF分布乱数発生器インスタンス
         */
        abstract FDistributionRnd createInstanceOf(double d1, double d2);

        @Override
        public String toString() {
            return "FDistRnd.Factory";
        }
    }
}
