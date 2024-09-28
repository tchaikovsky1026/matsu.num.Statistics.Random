/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.lib;

/**
 * 指数関数, 対数関数を扱う. <br>
 * 外部のモジュールからこのインターフェースのメソッドを呼ぶことは想定されていない.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
public interface Exponentiation {

    /**
     * exp(<i>x</i>) を返す.
     * 
     * <p>
     * このインターフェースが適切に使用される限り,
     * <i>x</i> に極端な与えられることはない.
     * </p>
     *
     * @param x <i>x</i>
     * @return exp(<i>x</i>)
     */
    public abstract double exp(double x);

    /**
     * |<i>x</i>| &ll; 1 で高精度な exp(<i>x</i>) - 1 を返す.
     * 
     * <p>
     * このインターフェースが適切に使用される限り,
     * <i>x</i> に極端な与えられることはない.
     * </p>
     *
     * @param x <i>x</i>
     * @return exp(<i>x</i>) - 1
     */
    public abstract double expm1(double x);

    /**
     * ln(<i>x</i>) (自然対数) を返す.
     * 
     * <p>
     * このインターフェースが適切に使用される限り,
     * <i>x</i> に極端な与えられることはない.
     * </p>
     *
     * @param x <i>x</i>
     * @return ln(<i>x</i>)
     */
    public abstract double log(double x);

    /**
     * ln(1 + <i>x</i>) を返す.
     * 
     * <p>
     * このインターフェースが適切に使用される限り,
     * <i>x</i> に極端な与えられることはない.
     * </p>
     *
     * @param x <i>x</i>
     * @return ln(1 + <i>x</i>)
     */
    public abstract double log1p(double x);

    /**
     * 正の平方根を返す.
     * 
     * <p>
     * このインターフェースが適切に使用される限り,
     * 引数に極端な与えられることはない.
     * </p>
     *
     * @param x 引数
     * @return 平方根
     */
    public abstract double sqrt(double x);
}
