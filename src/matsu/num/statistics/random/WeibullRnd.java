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

import matsu.num.statistics.random.accomp.FloatingRandomGenerator;

/**
 * <p>
 * 標準 Weibull 分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準 Weibull 分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>k</i> は形状パラメータ). <br>
 * (ただし, 境界値が発生する可能性がある.)
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup><i>k</i> - 1</sup> exp(-<i>x</i><sup><i>k</i></sup>)
 * &emsp; (0 &lt; <i>x</i> &lt; +&infin;)
 * </li>
 * </ul>
 * 
 * <p>
 * 扱える形状パラメータ <i>k</i> は, <br>
 * {@code 1.0E-2 <= k <= 1.0E+14} <br>
 * である.
 * </p>
 * 
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface WeibullRnd extends FloatingRandomGenerator {

    /**
     * 扱うことができる形状パラメータの最小値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final double LOWER_LIMIT_SHAPE_PARAMETER = 1E-2;

    /**
     * 扱うことができる形状パラメータの最大値.
     * 
     * @deprecated
     *                 モジュール外部で直接この定数に依存すべきではない. <br>
     *                 パラメータの正当性は static メソッドにより検証されるべきである.
     */
    @Deprecated
    public static final double UPPER_LIMIT_SHAPE_PARAMETER = 1E14;

    /**
     * <p>
     * このインスタンスが扱う形状パラメータ <i>k</i> の値を返す.
     * </p>
     *
     * @return 形状パラメータ <i>k</i>
     */
    public abstract double shapeParameter();

    /**
     * <p>
     * 指定したパラメータが乱数発生器に適合するかを判定する.
     * </p>
     *
     * @param k 形状パラメータ
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameter(double k) {
        return WeibullRnd.LOWER_LIMIT_SHAPE_PARAMETER <= k
                && k <= WeibullRnd.UPPER_LIMIT_SHAPE_PARAMETER;
    }

    /**
     * {@link WeibullRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends RndFactory {

        /**
         * <p>
         * 指定した形状パラメータの Weibull 分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameter(double)} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param k 形状パラメータ
         * @return 形状パラメータが <i>k</i> の Weibull 分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract WeibullRnd instanceOf(double k);
    }
}
