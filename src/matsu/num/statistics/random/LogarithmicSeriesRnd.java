/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.10
 */
package matsu.num.statistics.random;

/**
 * <p>
 * 対数級数分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 対数級数分布の確率質量関数 P(<i>k</i>) は次のとおりである
 * (<i>p</i> はパラメータ).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>k</i>) &propto;
 * <i>p</i><sup><i>k</i> - 1</sup> / <i>k</i>
 * &emsp; (<i>k</i> = 1, 2, ...)
 * </li>
 * 
 * <li>P(<i>k</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * <p>
 * 扱うことができるパラメータ <i>p</i> は, {@code 0 <= p <= (1 - 1E-7)} である.
 * </p>
 * 
 * <p>
 * <u>
 * 確率質量関数は一般的には,
 * <i>p</i><sup><i>k</i></sup> / <i>k</i> の形で書かれる. <br>
 * ここでは <i>p</i> = 0 を扱えるようにするため, 指数を <i>k</i> - 1 と書いた. <br>
 * 確率分布としては全く同一である.
 * </u>
 * </p>
 *
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 */
public interface LogarithmicSeriesRnd extends IntegerRandomGenerator {

    /**
     * 扱うことができるパラメータの最小値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final double LOWER_LIMIT_P = 0d;

    /**
     * 扱うことができるパラメータの最大値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final double UPPER_LIMIT_P = 1d - 1E-7d;

    /**
     * <p>
     * このインスタンスが扱うパラメータ <i>p</i> の値を返す.
     * </p>
     *
     * @return パラメータ <i>p</i>
     */
    public abstract double p();

    /**
     * <p>
     * 指定したパラメータが乱数発生器に適合するかを判定する.
     * </p>
     *
     * @param p パラメータ
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameter(double p) {
        return LOWER_LIMIT_P <= p && p <= UPPER_LIMIT_P;
    }

    /**
     * {@link LogarithmicSeriesRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends RndFactory {

        /**
         * <p>
         * 指定したパラメータの対数級数分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameter(double)} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param p パラメータ
         * @return パラメータが <i>p</i> の対数級数分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract LogarithmicSeriesRnd instanceOf(double p);
    }
}
