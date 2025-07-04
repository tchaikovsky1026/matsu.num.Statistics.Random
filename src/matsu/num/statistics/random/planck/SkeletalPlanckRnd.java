/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.9
 */
package matsu.num.statistics.random.planck;

import matsu.num.statistics.random.PlanckRnd;

/**
 * {@link PlanckRnd} の骨格実装を扱う.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalPlanckRnd implements PlanckRnd {

    final double alpha;

    /**
     * 唯一のコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないので,
     * このままモジュール外に公開してはいけない.
     */
    SkeletalPlanckRnd(double alpha) {
        super();
        this.alpha = alpha;
    }

    @Override
    public final double alpha() {
        return this.alpha;
    }

    @Override
    public String toString() {
        return String.format(
                "PlanckRnd(alpha = %s)", this.alpha());
    }

    /**
     * {@link matsu.num.statistics.random.PlanckRnd.Factory} の骨格実装.
     */
    static abstract class Factory implements PlanckRnd.Factory {

        /**
         * 唯一の外部に公開されないコンストラクタ.
         */
        Factory() {
            super();
        }

        @Override
        public final PlanckRnd instanceOf(double alpha) {
            if (!PlanckRnd.acceptsParameter(alpha)) {
                throw new IllegalArgumentException(
                        String.format(
                                "Illegal parameter: alpha = %s", alpha));
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
         * @return Planck 分布乱数発生器インスタンス
         */
        abstract PlanckRnd createInstanceOf(double alpha);

        @Override
        public String toString() {
            return "PlanckRnd.Factory";
        }
    }
}
