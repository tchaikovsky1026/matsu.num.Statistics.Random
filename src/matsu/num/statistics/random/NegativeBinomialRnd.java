/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.24
 */
package matsu.num.statistics.random;

/**
 * <p>
 * 負の二項分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 負の二項分布の確率質量関数 P(<i>k</i>) は次のとおりである
 * (<i>r</i> は成功回数, <i>p</i> は成功確率).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>k</i>) =
 * (<i>k</i> + <i>r</i> - 1)! / (<i>k</i>! (<i>r</i> - 1)!)
 * <i>p</i><sup><i>r</i></sup>
 * (1 - <i>p</i>)<sup><i>k</i></sup>
 * &emsp; (<i>k</i> = 0, 1, ...)
 * </li>
 * 
 * <li>P(<i>k</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * <p>
 * 扱える試行回数と成功確率は, <br>
 * {@code 1 <= r <= 1_000_000}, <br>
 * {@code 1E-7 <= p <= 1.0} <br>
 * {@code r/p <= 1E7} <br>
 * である.
 * </p>
 * 
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface NegativeBinomialRnd extends IntegerRandomGenerator {

    /**
     * 扱うことができる成功回数の最小値.
     */
    public static final int LOWER_LIMIT_SUCCESSES = 1;

    /**
     * 扱うことができる成功回数の最大値.
     */
    public static final int UPPER_LIMIT_SUCCESSES = 1_000_000;

    /**
     * 扱うことができる成功確率の最小値.
     */
    public static final double LOWER_LIMIT_SUCCESS_PROBABILITY = 1E-7;

    /**
     * 扱うことができる成功確率の最大値.
     */
    public static final double UPPER_LIMIT_SUCCESS_PROBABILITY = 1d;

    /**
     * <p>
     * このインスタンスが扱う成功回数の値を返す.
     * </p>
     *
     * @return 成功回数
     */
    public abstract int numberOfSuccesses();

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
     * @param r 成功回数
     * @param p 成功確率
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameters(int r, double p) {
        final double max_r_over_p = 1E7;

        return LOWER_LIMIT_SUCCESSES <= r
                && r <= UPPER_LIMIT_SUCCESSES
                && LOWER_LIMIT_SUCCESS_PROBABILITY <= p
                && p <= UPPER_LIMIT_SUCCESS_PROBABILITY
                && r <= p * max_r_over_p;
    }

    /**
     * {@link NegativeBinomialRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends RndFactory {

        /**
         * <p>
         * 指定したパラメータの負の二項分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameters(int, double)}
         * により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param r 成功回数
         * @param p 成功確率
         * @return 負の二項分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract NegativeBinomialRnd instanceOf(int r, double p);
    }
}
