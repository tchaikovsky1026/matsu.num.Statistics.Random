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
 * モジュール内でのみ使用する, 二項分布に従う乱数発生器を扱う.
 * 
 * <p>
 * 二項分布の確率質量関数 P(<i>k</i>) は次のとおりである
 * (<i>n</i> は試行回数, <i>p</i> は成功確率).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>k</i>) =
 * <i>n</i>! / (<i>k</i>! (<i>n</i> - <i>k</i>)!)
 * <i>p</i><sup><i>k</i></sup>
 * (1 - <i>p</i>)<sup><i>n</i> - <i>k</i></sup>
 * &emsp; (<i>k</i> = 0, 1, ..., <i>n</i>)
 * </li>
 * </ul>
 * 
 * <p>
 * 扱える試行回数と成功確率は, <br>
 * {@code 0 <= n <= 1_000_000_000}, <br>
 * {@code 0.0 <= p <= 1.0} <br>
 * である.
 * </p>
 * 
 * @author Matsuura Y.
 */
public interface InnerStaticBinomialRnd extends Rnd {

    /**
     * 扱うことができる試行回数の最小値.
     * 
     * @deprecated 外部で直接この定数に依存すべきではない.
     */
    @Deprecated
    public static final int LOWER_LIMIT_TRIALS = 0;

    /**
     * 扱うことができる試行回数の最大値.
     * 
     * @deprecated 外部で直接この定数に依存すべきではない.
     */
    @Deprecated
    public static final int UPPER_LIMIT_TRIALS = 1_000_000_000;

    /**
     * 扱うことができる成功確率の最小値.
     * 
     * @deprecated 外部で直接この定数に依存すべきではない.
     */
    @Deprecated
    public static final double LOWER_LIMIT_SUCCESS_PROBABILITY = 0d;

    /**
     * 扱うことができる成功確率の最大値.
     * 
     * @deprecated 外部で直接この定数に依存すべきではない.
     */
    @Deprecated
    public static final double UPPER_LIMIT_SUCCESS_PROBABILITY = 1d;

    /**
     * 指定したパラメータが乱数発生器に適合するかを判定する.
     * 
     * @param n 試行回数
     * @param p 成功確率
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameters(int n, double p) {
        return LOWER_LIMIT_TRIALS <= n
                && n <= UPPER_LIMIT_TRIALS
                && LOWER_LIMIT_SUCCESS_PROBABILITY <= p
                && p <= UPPER_LIMIT_SUCCESS_PROBABILITY;
    }

    /**
     * 与えられた (n,p) に関する Bin(n,p) に従う乱数を生成する.
     * 
     * @param n 試行回数
     * @param p 成功確率
     * @param random 乱数発生器
     * @return 発生した乱数
     * @throws IllegalArgumentException (n,p) が accept されない場合
     * @throws NullPointerException 引数に null を含む場合
     */
    public abstract int next(int n, double p, BaseRandom random);

    /**
     * {@link InnerStaticBinomialRnd} のファクトリ.
     */
    public static interface Factory extends ParameterlessRndFactory<InnerStaticBinomialRnd> {

    }
}
