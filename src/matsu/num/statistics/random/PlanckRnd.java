/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.9
 */
package matsu.num.statistics.random;

/**
 * <p>
 * 標準 Planck 分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準 Planck 分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>&alpha;</i> はパラメータ). <br>
 * (ただし, 境界値が発生する可能性がある.)
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup><i>&alpha;</i></sup> / (exp(<i>x</i>) - 1)
 * &emsp; (0 &lt; <i>x</i> &lt; +&infin;)
 * </li>
 * </ul>
 * 
 * <p>
 * 扱える形状パラメータ <i>&alpha;</i> は, <br>
 * {@code 0.25 <= alpha <= 1E+28} <br>
 * である.
 * </p>
 *
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface PlanckRnd extends FloatingRandomGenerator {

    /**
     * 扱うことができるパラメータの最小値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final double LOWER_LIMIT_ALPHA = ZetaRnd.LOWER_LIMIT_S - 1d;

    /**
     * 扱うことができるパラメータの最大値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final double UPPER_LIMIT_ALPHA = GammaRnd.UPPER_LIMIT_SHAPE_PARAMETER;

    /**
     * <p>
     * このインスタンスが扱うパラメータ <i>&alpha;</i> の値を返す.
     * </p>
     *
     * @return パラメータ <i>&alpha;</i>
     */
    public abstract double alpha();

    /**
     * <p>
     * 指定したパラメータが乱数発生器に適合するかを判定する.
     * </p>
     *
     * @param alpha パラメータ
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameter(double alpha) {
        return LOWER_LIMIT_ALPHA <= alpha && alpha <= UPPER_LIMIT_ALPHA;
    }

    /**
     * {@link PlanckRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends RndFactory {

        /**
         * <p>
         * 指定したパラメータの Planck 分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameter(double)} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param alpha パラメータ
         * @return パラメータが <i>&alpha;</i> のガンマ分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract PlanckRnd instanceOf(double alpha);

    }
}
