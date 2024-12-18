/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.12.18
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
 * 
 * <hr>
 * 
 * <h2>使用について</h2>
 * 
 * 
 * <h3>実装規約</h3>
 * 
 * <p>
 * この {@link Exponentiation} インターフェース実行する指数関数, 対数関数, 平方根の計算は,
 * {@link java.lang.Math} に即した結果が返されることが期待される. <br>
 * ただし, 厳密に一致 ({@code ==} 比較が {@code true}) である必要はなく,
 * おおよその倍精度であれば良い. <br>
 * そのため, {@link java.lang.Math} により計算してもよいが,
 * より高速な計算ライブラリを用いてもよい.
 * </p>
 * 
 * <p>
 * 数学関数の計算であるため定義域については厳格であり,
 * {@link Double#NaN} を返さないことは極めて重要である. <br>
 * ただし, 定義域の区間端の極限が有限値 or +&infin; or -&infin; になる場合において,
 * 区間端の付近でそれらを返すことは問題ない.
 * </p>
 * 
 * 
 * <h3><u><i>非推奨：外部からのメソッドコール</i></u></h3>
 * 
 * <p>
 * このインターフェースはインスタンスの注入のための型を定義しているだけであり,
 * 外部のモジュールからこのインターフェースのメソッドをコールすることは想定されていない. <br>
 * これに違反した方法で使用することは, 強く非推奨である.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 23.1
 */
public interface Exponentiation {

    /**
     * exp(<i>x</i>) を返す.
     *
     * @param x <i>x</i>
     * @return exp(<i>x</i>)
     */
    public abstract double exp(double x);

    /**
     * |<i>x</i>| &ll; 1 で高精度な exp(<i>x</i>) - 1 を返す.
     *
     * @param x <i>x</i>
     * @return exp(<i>x</i>) - 1
     */
    public abstract double expm1(double x);

    /**
     * ln(<i>x</i>) (自然対数) を返す.
     *
     * @param x <i>x</i>
     * @return ln(<i>x</i>)
     */
    public abstract double log(double x);

    /**
     * ln(1 + <i>x</i>) を返す.
     *
     * @param x <i>x</i>
     * @return ln(1 + <i>x</i>)
     */
    public abstract double log1p(double x);

    /**
     * 正の平方根を返す.
     *
     * @param x 引数
     * @return 平方根
     */
    public abstract double sqrt(double x);
}
