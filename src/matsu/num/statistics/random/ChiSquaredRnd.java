/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.24
 */
package matsu.num.statistics.random;

/**
 * <p>
 * カイ二乗分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * カイ二乗分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>k</i> は自由度).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup><i>k</i> / 2 - 1</sup>
 * exp(-<i>x</i> / 2)
 * &emsp; (<i>x</i> &ge; 0)
 * </li>
 * 
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * <p>
 * 扱える自由度 <i>k</i> は, <br>
 * {@code 2.0E-2 <= k <= 2.0E+28} <br>
 * である.
 * </p>
 *
 * @author Matsuura Y.
 * @version 21.0
 */
public interface ChiSquaredRnd extends FloatingRandomGenerator {

    /**
     * 扱うことができる自由度の最小値.
     */
    public static final double LOWER_LIMIT_DEGREES_OF_FREEDOM = 2E-2;

    /**
     * 扱うことができる自由度の最大値.
     */
    public static final double UPPER_LIMIT_DEGREES_OF_FREEDOM = 2E28;

    /**
     * <p>
     * このインスタンスが扱う自由度 <i>k</i> の値を返す.
     * </p>
     *
     * @return 自由度 <i>k</i>
     */
    public abstract double degreesOfFreedom();

    /**
     * <p>
     * 指定したパラメータが乱数発生器に適合するかを判定する.
     * </p>
     *
     * @param k 自由度
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameter(double k) {
        return ChiSquaredRnd.LOWER_LIMIT_DEGREES_OF_FREEDOM <= k
                && k <= ChiSquaredRnd.UPPER_LIMIT_DEGREES_OF_FREEDOM;
    }

    /**
     * {@link ChiSquaredRnd} のファクトリ.
     */
    public static interface Factory {

        /**
         * <p>
         * 指定した自由度のカイ二乗分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameter(double)} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param k 自由度
         * @return 自由度が <i>k</i> のカイ二乗分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract ChiSquaredRnd instanceOf(double k);

    }
}
