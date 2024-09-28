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
 * 乱数生成のたびにパラメータを指定する標準ガンマ分布乱数発生器
 * (Staticガンマ乱数発生器)
 * を扱う.
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
 * 扱うことができる形状パラメータは, <br>
 * {@code 1.0E-2 <= k <= 1.0E+28} <br>
 * である.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
public interface StaticGammaRnd {

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
     * 指定したパラメータが乱数発生器に適合するかを判定する.
     * </p>
     *
     * @param k 形状パラメータ
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameter(double k) {
        return LOWER_LIMIT_SHAPE_PARAMETER <= k && k <= UPPER_LIMIT_SHAPE_PARAMETER;
    }

    /**
     * <p>
     * 形状パラメータを与えて, 標準ガンマ分布に従う乱数を発生させる.
     * </p>
     * 
     * <p>
     * パラメータの正当性は {@link #acceptsParameter(double)} により検証され,
     * 不適の場合は例外がスローされる.
     * </p>
     *
     * @param random 基本乱数発生器
     * @param k 形状パラメータ
     * @return 形状パラメータが <i>k</i> の標準ガンマ分布に従う乱数の値
     * @throws IllegalArgumentException パラメータがacceptされない場合
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public abstract double nextRandom(BaseRandom random, double k);

    /**
     * {@link StaticGammaRnd} のファクトリ.
     */
    public static interface Factory {

        /**
         * <p>
         * Staticガンマ乱数発生器インスタンスを返す.
         * </p>
         *
         * @return Staticガンマ乱数発生器インスタンス
         */
        public abstract StaticGammaRnd instance();

    }
}
