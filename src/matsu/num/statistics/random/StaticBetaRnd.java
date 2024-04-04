/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random;

/**
 * <p>
 * 乱数生成のたびにパラメータを指定するベータ分布乱数発生器
 * (Staticベータ乱数発生器)
 * を扱う.
 * </p>
 * 
 * <p>
 * ベータ分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>a</i>, <i>b</i> は形状パラメータ).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup><i>a</i> - 1</sup>
 * (1 - <i>x</i>)<sup><i>b</i> - 1</sup>
 * &emsp; (0 &le; <i>x</i> &le; 1)
 * </li>
 * 
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * <p>
 * このインターフェースではベータ分布に加えて, ベータプライム分布に従う乱数生成も扱う. <br>
 * ベータプライム分布の確率密度関数 P(<i>x</i>) は次のとおりである.
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup><i>a</i> - 1</sup>
 * /
 * (1 + <i>x</i>)<sup><i>a</i> + <i>b</i></sup>
 * &emsp; (<i>x</i> &ge; 0)
 * </li>
 * 
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * <p>
 * 扱うことができる形状パラメータは, <br>
 * {@code 1.0E-2 <= (a, b) <= 1.0E+14}
 * である.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
public interface StaticBetaRnd {

    /**
     * 扱うことができる形状パラメータの最小値.
     */
    public static final double LOWER_LIMIT_SHAPE_PARAMETER = 1E-2;

    /**
     * 扱うことができる形状パラメータの最大値.
     */
    public static final double UPPER_LIMIT_SHAPE_PARAMETER = 1E14;

    /**
     * <p>
     * 指定したパラメータが乱数発生器に適合するかを判定する.
     * </p>
     *
     * @param a 形状パラメータ
     * @param b 形状パラメータ
     * @return パラメータが適合する場合はtrue
     */
    public abstract boolean acceptsParameters(double a, double b);

    /**
     * <p>
     * 形状パラメータを与えて, ベータ分布に従う乱数を発生させる.
     * </p>
     * 
     * <p>
     * パラメータの正当性は {@link #acceptsParameters(double, double)} により検証され,
     * 不適の場合は例外がスローされる.
     * </p>
     *
     * @param random 基本乱数発生器
     * @param a 形状パラメータ
     * @param b 形状パラメータ
     * @return 形状パラメータが (<i>a</i>, <i>b</i>) のベータ分布に従う乱数の値
     * @throws IllegalArgumentException パラメータがacceptされない場合
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public abstract double nextRandom(BaseRandom random, double a, double b);

    /**
     * <p>
     * 形状パラメータを与えて, ベータプライム分布に従う乱数を発生させる.
     * </p>
     * 
     * <p>
     * パラメータの正当性は {@link #acceptsParameters(double, double)} により検証され,
     * 不適の場合は例外がスローされる.
     * </p>
     *
     * @param random 基本乱数発生器
     * @param a 形状パラメータ
     * @param b 形状パラメータ
     * @return 形状パラメータが (<i>a</i>, <i>b</i>) のベータプライム分布に従う乱数の値
     * @throws IllegalArgumentException パラメータがacceptされない場合
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public abstract double nextBetaPrime(BaseRandom random, double a, double b);

    /**
     * {@link StaticBetaRnd} のファクトリ.
     */
    public static interface Factory extends RandomGeneratorFactory {

        /**
         * <p>
         * Staticベータ乱数発生器インスタンスを返す.
         * </p>
         *
         * @return Staticベータ乱数発生器インスタンス
         */
        public abstract StaticBetaRnd instance();

    }

}
