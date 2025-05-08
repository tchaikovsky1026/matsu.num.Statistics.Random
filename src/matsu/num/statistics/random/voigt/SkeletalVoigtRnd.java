/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.8
 */
package matsu.num.statistics.random.voigt;

import matsu.num.statistics.random.VoigtRnd;

/**
 * {@link VoigtRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalVoigtRnd implements VoigtRnd {

    final double alpha;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param alpha パラメータ
     */
    SkeletalVoigtRnd(double alpha) {
        super();

        assert VoigtRnd.acceptsParameter(alpha) : "パラメータ不正";

        this.alpha = alpha;
    }

    @Override
    public final double alpha() {
        return this.alpha;
    }

    @Override
    public String toString() {
        return String.format("VoigtRnd(%s)", this.alpha());
    }

    /**
     * {@link matsu.num.statistics.random.VoigtRnd.Factory} クラスの骨格実装.
     */
    static abstract class Factory implements VoigtRnd.Factory {

        /**
         * 唯一の外部に公開されないコンストラクタ.
         */
        Factory() {
            super();
        }

        @Override
        public final VoigtRnd instanceOf(double alpha) {
            if (!VoigtRnd.acceptsParameter(alpha)) {
                throw new IllegalArgumentException(String.format("alphaが不正:%s", alpha));
            }

            return this.createInstanceOf(alpha);
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
         * @param alpha パラメータ
         * @return パラメータ <i>&alpha;</i> のVoigt分布乱数発生器
         */
        abstract VoigtRnd createInstanceOf(double alpha);

        @Override
        public String toString() {
            return "VoigtRnd.Factory";
        }
    }
}
