/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.7
 */
package matsu.num.statistics.random;

/**
 * <p>
 * ゼータ分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * ゼータ分布の確率質量関数 P(<i>k</i>) は次のとおりである
 * (<i>s</i> はパラメータ).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>k</i>) &propto;
 * <i>k</i><sup>-<i>s</i></sup>
 * &emsp; (<i>k</i> = 1, 2, ...)
 * </li>
 * </ul>
 * 
 * <p>
 * 扱うことができるパラメータ <i>s</i> は, {@code 1.25 <= s <= 100.0} である.
 * </p>
 *
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface ZetaRnd extends IntegerRandomGenerator {

    /**
     * 扱うことができるパラメータの最小値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final double LOWER_LIMIT_S = 1.25d;

    /**
     * 扱うことができるパラメータの最大値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final double UPPER_LIMIT_S = 100d;

    /**
     * <p>
     * このインスタンスが扱うパラメータ <i>s</i> の値を返す.
     * </p>
     *
     * @return パラメータ <i>s</i>
     */
    public abstract double s();

    /**
     * <p>
     * 指定したパラメータが乱数発生器に適合するかを判定する.
     * </p>
     *
     * @param s パラメータ
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameter(double s) {
        return LOWER_LIMIT_S <= s && s <= UPPER_LIMIT_S;
    }

    /**
     * {@link ZetaRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends RndFactory {

        /**
         * <p>
         * 指定したパラメータのゼータ分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameter(double)} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param s パラメータ
         * @return パラメータが <i>s</i> のゼータ分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract ZetaRnd instanceOf(double s);
    }
}
