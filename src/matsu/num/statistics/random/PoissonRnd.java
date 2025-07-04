/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.7
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.accomp.IntegerRandomGenerator;

/**
 * <p>
 * Poisson 分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * Poisson 分布の確率質量関数 P(<i>k</i>) は次のとおりである
 * (<i>&lambda;</i> はパラメータ).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>k</i>) =
 * <i>e</i><sup>-<i>&lambda;</i></sup>
 * <i>&lambda;</i><sup><i>k</i></sup>
 * /
 * <i>k</i>!
 * &emsp; (<i>k</i> = 0, 1, ...)
 * </li>
 * </ul>
 * 
 * <p>
 * 扱えるパラメータ <i>&lambda;</i> は, <br>
 * {@code 0.0 <= lambda <= 1.0E6} <br>
 * である.
 * </p>
 * 
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface PoissonRnd extends IntegerRandomGenerator {

    /**
     * 扱うことができるパラメータの最小値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final double LOWER_LIMIT_LAMBDA = 0d;

    /**
     * 扱うことができるパラメータの最大値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final double UPPER_LIMIT_LAMBDA = 1E6;

    /**
     * <p>
     * このインスタンスが扱うパラメータ <i>&lambda;</i> の値を返す.
     * </p>
     *
     * @return パラメータ <i>&lambda;</i>
     */
    public abstract double lambda();

    /**
     * <p>
     * 指定したパラメータが乱数発生器に適合するかを判定する.
     * </p>
     *
     * @param lambda パラメータ
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameter(double lambda) {
        return PoissonRnd.LOWER_LIMIT_LAMBDA <= lambda
                && lambda <= PoissonRnd.UPPER_LIMIT_LAMBDA;
    }

    /**
     * {@link PoissonRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends RndFactory {

        /**
         * <p>
         * 指定したパラメータのPoisson分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameter(double)} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param lambda パラメータ
         * @return パラメータが <i>&lambda;</i> のPoisson分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract PoissonRnd instanceOf(double lambda);
    }
}
