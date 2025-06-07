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
 * 二項分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 二項分布の確率質量関数 P(<i>k</i>) は次のとおりである
 * (<i>n</i> は試行回数, <i>p</i> は成功確率).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>k</i>) =
 * <i>n</i>! / (<i>k</i>! (<i>n</i> - <i>k</i>)!)
 * <i>p</i><sup><i>k</i></sup>
 * (1 - <i>p</i>)<sup><i>n</i> - <i>k</i></sup>
 * &emsp; (<i>k</i> = 0, 1, ..., <i>n</i>)
 * </li>
 * 
 * <li>P(<i>k</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * <p>
 * 扱える試行回数と成功確率は, <br>
 * {@code 0 <= n <= 1_000_000}, <br>
 * {@code 0.0 <= p <= 1.0} <br>
 * である.
 * </p>
 * 
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface BinomialRnd extends IntegerRandomGenerator {

    /**
     * 扱うことができる試行回数の最小値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final int LOWER_LIMIT_TRIALS = 0;

    /**
     * 扱うことができる試行回数の最大値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final int UPPER_LIMIT_TRIALS = 1_000_000;

    /**
     * 扱うことができる成功確率の最小値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final double LOWER_LIMIT_SUCCESS_PROBABILITY = 0d;

    /**
     * 扱うことができる成功確率の最大値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final double UPPER_LIMIT_SUCCESS_PROBABILITY = 1d;

    /**
     * <p>
     * このインスタンスが扱う試行回数の値を返す.
     * </p>
     *
     * @return 試行回数
     */
    public abstract int numberOfTrials();

    /**
     * <p>
     * このインスタンスが扱う成功確率の値を返す.
     * </p>
     *
     * @return 成功確率
     */
    public abstract double successPobability();

    /**
     * <p>
     * 指定したパラメータが乱数発生器に適合するかを判定する.
     * </p>
     * 
     * @param n 試行回数
     * @param p 成功確率
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameters(int n, double p) {
        return LOWER_LIMIT_TRIALS <= n
                && n <= UPPER_LIMIT_TRIALS
                && LOWER_LIMIT_SUCCESS_PROBABILITY <= p
                && p <= UPPER_LIMIT_SUCCESS_PROBABILITY;
    }

    /**
     * {@link BinomialRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends RndFactory {

        /**
         * <p>
         * 指定したパラメータの二項分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameters(int, double)}
         * により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param n 試行回数
         * @param p 成功確率
         * @return 二項分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract BinomialRnd instanceOf(int n, double p);
    }
}
