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

import matsu.num.statistics.random.gamma.GammaRndSealed;

/**
 * <p>
 * 標準ガンマ分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準ガンマ分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>k</i> は形状パラメータ).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup><i>k</i> - 1</sup> exp(-<i>x</i>)
 * &emsp; (<i>x</i> &ge; 0)
 * </li>
 * 
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * <p>
 * 扱える形状パラメータ <i>k</i> は, <br>
 * {@code 1.0E-2 <= k <= 1.0E+28} <br>
 * である.
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
public sealed interface GammaRnd
        extends FloatingRandomGenerator permits GammaRndSealed {

    /**
     * 扱うことができる形状パラメータの最小値.
     */
    public static final double LOWER_LIMIT_SHAPE_PARAMETER = 1E-2;

    /**
     * 扱うことができる形状パラメータの最大値.
     */
    public static final double UPPER_LIMIT_SHAPE_PARAMETER = 1E28;

    /**
     * <p>
     * このインスタンスが扱う形状パラメータの値を返す.
     * </p>
     *
     * @return 形状パラメータ
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
        return GammaRnd.LOWER_LIMIT_SHAPE_PARAMETER <= k
                && k <= GammaRnd.UPPER_LIMIT_SHAPE_PARAMETER;
    }

    /**
     * {@link GammaRnd} のファクトリ.
     */
    public static sealed interface Factory
            extends RndFactory permits GammaRndSealed.FactorySealed {

        /**
         * <p>
         * 指定した形状パラメータのガンマ分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameter(double)} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param k 形状パラメータ
         * @return 形状パラメータが <i>k</i> のガンマ分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract GammaRnd instanceOf(double k);

    }
}
