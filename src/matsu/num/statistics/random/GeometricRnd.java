/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.geo.GeometricRndSealed;

/**
 * <p>
 * 幾何分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 幾何分布の確率質量関数 P(<i>k</i>) は次のとおりである
 * (<i>p</i>は成功確率).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>k</i>) =
 * <i>p</i> (1 - <i>p</i>)<sup><i>k</i> - 1</sup>
 * &emsp; (<i>k</i> &ge; 1)
 * </li>
 * 
 * <li>P(<i>k</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * <p>
 * 扱うことができる成功確率 <i>p</i> は, {@code 1.0E-7 <= p <= 1.0} である.
 * </p>
 *
 *
 * <p>
 * <i>
 * <u>
 * このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 * 外部で実装することは不可.
 * </u>
 * </i>
 * </p>
 * 
 * @author Matsuura Y.
 * @version 22.1
 */
public sealed interface GeometricRnd
        extends IntegerRandomGenerator permits GeometricRndSealed {

    /**
     * 扱うことができる成功確率の最小値.
     */
    public static final double LOWER_LIMIT_SUCCESS_PROBABILITY = 1E-7;

    /**
     * 扱うことができる成功確率の最大値.
     */
    public static final double UPPER_LIMIT_SUCCESS_PROBABILITY = 1;

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
     * @param p 成功確率
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameter(double p) {
        return LOWER_LIMIT_SUCCESS_PROBABILITY <= p
                && p <= UPPER_LIMIT_SUCCESS_PROBABILITY;
    }

    /**
     * {@link GeometricRnd} のファクトリ.
     */
    public static sealed interface Factory
            extends RndFactory permits GeometricRndSealed.FactorySealed {

        /**
         * <p>
         * 指定した成功確率の幾何分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameter(double)} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param p 成功確率
         * @return 成功確率がpの幾何分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public GeometricRnd instanceOf(double p);
    }
}
