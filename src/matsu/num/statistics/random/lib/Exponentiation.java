/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.9
 */
package matsu.num.statistics.random.lib;

/**
 * 指数関数, 対数関数, べき根の計算ライブラリを表す.
 * 
 * <p>
 * このインターフェースは, この乱数生成モジュール内で必要となる計算ロジックを外部から注入するために実装される. <br>
 * インターフェースを実装した具象クラスのインスタンスは, 最終的に
 * {@link matsu.num.statistics.random.service.RandomGeneratorFactoryProvider}
 * に注入され, このモジュール内の基本計算を担う.
 * </p>
 * 
 * <p>
 * <u><i>
 * このインターフェースはインスタンスの注入のための型を定義しているだけであり,
 * 外部のモジュールからこのインターフェースのメソッドをコールすることは想定されていない.
 * これに違反した方法で使用することは, 強く非推奨である.
 * </i></u>
 * </p>
 * 
 * @implSpec
 *               <p>
 *               この {@link Exponentiation} インターフェース実行する指数関数, 対数関数, 平方根の計算は,
 *               {@link java.lang.Math} に即した結果が返されることが期待される. <br>
 *               ただし, 厳密に一致 (「{@code ==}」比較が {@code true}) である必要はなく,
 *               おおよその倍精度であれば良い. <br>
 *               そのため, {@link java.lang.Math} により計算してもよいが,
 *               より高速な計算ライブラリを用いてもよい.
 *               </p>
 * 
 *               <p>
 *               数学関数の計算であるため定義域については厳格であり,
 *               {@link Double#NaN} を返さないことは極めて重要である. <br>
 *               定義域の区間端の極限が有限値 or 無限大になる場合において,
 *               端の付近でその値 （有限値 or 無限大） を返すことは問題ない.
 *               </p>
 * 
 * @author Matsuura Y.
 */
public interface Exponentiation {

    /**
     * exp(<i>x</i>) を返す.
     *
     * @param x <i>x</i>
     * @return exp(<i>x</i>)
     */
    public default double exp(double x) {
        return Math.exp(x);
    }

    /**
     * |<i>x</i>| &ll; 1 で高精度な exp(<i>x</i>) - 1 を返す.
     *
     * @param x <i>x</i>
     * @return exp(<i>x</i>) - 1
     */
    public default double expm1(double x) {
        return Math.expm1(x);
    }

    /**
     * ln(<i>x</i>) (自然対数) を返す.
     *
     * @param x <i>x</i>
     * @return ln(<i>x</i>)
     */
    public default double log(double x) {
        return Math.log(x);
    }

    /**
     * ln(1 + <i>x</i>) を返す.
     *
     * @param x <i>x</i>
     * @return ln(1 + <i>x</i>)
     */
    public default double log1p(double x) {
        return Math.log1p(x);
    }

    /**
     * <i>a</i> の <i>b</i> 乗
     * (<i>a</i><sup><i>b</i></sup>) を返す.
     * 
     * @param a 底
     * @param b 指数
     * @return <i>a</i><sup><i>b</i></sup>
     */
    public default double pow(double a, double b) {
        return Math.pow(a, b);
    }

    /**
     * 正の平方根を返す.
     *
     * @param x 引数
     * @return 平方根
     */
    public default double sqrt(double x) {
        return Math.sqrt(x);
    }
}
