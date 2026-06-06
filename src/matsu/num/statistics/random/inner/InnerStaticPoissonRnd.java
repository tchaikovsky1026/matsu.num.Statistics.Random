/*
 * Copyright © 2026 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.6.6
 */
package matsu.num.statistics.random.inner;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.Rnd;
import matsu.num.statistics.random.accomp.ParameterlessRndFactory;

/**
 * モジュール内でのみ使用する, Poisson 分布に従う乱数発生器を扱う.
 * 
 * <p>
 * Poisson 分布の確率質量関数 P(<i>k</i>) は次のとおりである
 * (<i>&lambda;</i> はパラメータ).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>k</i>) =
 * <i>e</i><sup>-<i>&lambda;</i></sup>
 * <i>&lambda;</i><sup><i>k</i></sup>
 * /
 * <i>k</i>!
 * &emsp; (<i>k</i> = 0, 1, ...)
 * </li>
 * </ul>
 * 
 * <p>
 * 扱えるパラメータ <i>&lambda;</i> は, <br>
 * {@code 0.0 <= lambda <= 1.0E9} <br>
 * である.
 * </p>
 * 
 * @author Matsuura Y.
 */
public interface InnerStaticPoissonRnd extends Rnd {
    /**
     * 扱うことができるパラメータの最小値.
     * 
     * @deprecated 外部で直接この定数に依存すべきではない.
     */
    @Deprecated
    public static final double LOWER_LIMIT_LAMBDA = 0d;

    /**
     * 扱うことができるパラメータの最大値.
     * 
     * @deprecated 外部で直接この定数に依存すべきではない.
     */
    @Deprecated
    public static final double UPPER_LIMIT_LAMBDA = 1E9;

    /**
     * 扱うことができる試行回数の最小値.
     * 
     * @deprecated 外部で直接この定数に依存すべきではない.
     */
    @Deprecated
    public static final int LOWER_LIMIT_TRIALS = 0;

    /**
     * <p>
     * 指定したパラメータが乱数発生器に適合するかを判定する.
     * </p>
     *
     * @param lambda パラメータ
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameter(double lambda) {
        return LOWER_LIMIT_LAMBDA <= lambda
                && lambda <= UPPER_LIMIT_LAMBDA;
    }

    /**
     * 与えられた (lambda) に関する Bin(lambda) に従う乱数を生成する.
     * 
     * @param lambda parameter lambda
     * @param random 乱数発生器
     * @return 発生した乱数
     * @throws IllegalArgumentException (lambda) が accept されない場合
     * @throws NullPointerException 引数に null を含む場合
     */
    public abstract int next(double lambda, BaseRandom random);

    /**
     * {@link InnerStaticPoissonRnd} のファクトリ.
     */
    public static interface Factory extends ParameterlessRndFactory<InnerStaticPoissonRnd> {

    }
}
