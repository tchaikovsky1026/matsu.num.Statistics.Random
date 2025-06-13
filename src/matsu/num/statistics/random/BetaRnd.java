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

/**
 * <p>
 * ベータ分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * ベータ分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>a</i>, <i>b</i> は形状パラメータ). <br>
 * (ただし, 境界値が発生する可能性がある.)
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup><i>a</i> - 1</sup>
 * (1 - <i>x</i>)<sup><i>b</i> - 1</sup>
 * &emsp; (0 &lt; <i>x</i> &lt; 1)
 * </li>
 * </ul>
 * 
 * <p>
 * このインターフェースではベータ分布に加えて, ベータプライム分布に従う乱数生成も扱う
 * ({@link #nextBetaPrime(BaseRandom)}). <br>
 * ベータプライム分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
 * (ただし, 境界値が発生する可能性がある.)
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup><i>a</i> - 1</sup>
 * /
 * (1 + <i>x</i>)<sup><i>a</i> + <i>b</i></sup>
 * &emsp; (0 &lt; <i>x</i> &lt; +&infin;)
 * </li>
 * </ul>
 * 
 * <p>
 * 扱える形状パラメータ <i>a</i>, <i>b</i> は, <br>
 * {@code 1.0E-2 <= (a, b) <= 1.0E+14} <br>
 * である.
 * </p>
 *
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 *
 * @author Matsuura Y.
 */
public interface BetaRnd extends FloatingRandomGenerator {

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
     * このインスタンスが扱う形状パラメータ <i>a</i> の値を返す.
     * </p>
     *
     * @return 形状パラメータ <i>a</i>
     */
    public abstract double shapeA();

    /**
     * <p>
     * このインスタンスが扱う形状パラメータ <i>b</i> の値を返す.
     * </p>
     *
     * @return 形状パラメータ <i>b</i>
     */
    public abstract double shapeB();

    /**
     * <p>
     * 与えられた基本乱数発生器を用いて, ベータプライム分布に従う乱数を発生させる.
     * </p>
     * 
     * @param random 基本乱数発生器
     * @return ベータプライム分布に従う乱数の値
     * @throws NullPointerException 引数がnullの場合
     */
    public abstract double nextBetaPrime(BaseRandom random);

    /**
     * <p>
     * 指定したパラメータが乱数発生器に適合するかを判定する.
     * </p>
     *
     * @param a 形状パラメータ
     * @param b 形状パラメータ
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameters(double a, double b) {
        return BetaRnd.LOWER_LIMIT_SHAPE_PARAMETER <= a && a <= BetaRnd.UPPER_LIMIT_SHAPE_PARAMETER
                && BetaRnd.LOWER_LIMIT_SHAPE_PARAMETER <= b && b <= BetaRnd.UPPER_LIMIT_SHAPE_PARAMETER;
    }

    /**
     * {@link BetaRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends RndFactory {

        /**
         * <p>
         * 指定した形状パラメータのベータ分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameters(double, double)} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param a 形状パラメータ
         * @param b 形状パラメータ
         * @return 形状パラメータが (<i>a</i>, <i>b</i>) のベータ分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract BetaRnd instanceOf(double a, double b);
    }
}
