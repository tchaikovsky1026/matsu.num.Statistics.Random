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
 * Yule-Simon 分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * Yule-Simon 分布の確率質量関数 P(<i>k</i>) は次のとおりである
 * (<i>&rho;</i> はパラメータ, B( &middot; , &middot; ) はベータ関数).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>k</i>) &propto;
 * B(<i>k</i>, <i>&rho;</i> + 1)
 * &emsp; (<i>k</i> = 1, 2, ...)
 * </li>
 * </ul>
 * 
 * <p>
 * 扱うことができるパラメータ <i>&rho;</i> は, {@code 0.25 <= rho <= 100.0} である.
 * </p>
 *
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface YuleSimonRnd extends IntegerRandomGenerator {

    /**
     * 扱うことができるパラメータの最小値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final double LOWER_LIMIT_RHO = 0.25d;

    /**
     * 扱うことができるパラメータの最大値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final double UPPER_LIMIT_RHO = 100d;

    /**
     * <p>
     * このインスタンスが扱うパラメータ <i>&rho;</i> の値を返す.
     * </p>
     *
     * @return パラメータ <i>&rho;</i>
     */
    public abstract double rho();

    /**
     * <p>
     * 指定したパラメータが乱数発生器に適合するかを判定する.
     * </p>
     *
     * @param rho パラメータ
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameter(double rho) {
        return LOWER_LIMIT_RHO <= rho && rho <= UPPER_LIMIT_RHO;
    }

    /**
     * {@link YuleSimonRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends RndFactory {

        /**
         * <p>
         * 指定したパラメータの Yule-Simon 分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameter(double)} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param rho パラメータ
         * @return パラメータが <i>&rho;</i> の Yule-Simon 分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract YuleSimonRnd instanceOf(double rho);
    }
}
