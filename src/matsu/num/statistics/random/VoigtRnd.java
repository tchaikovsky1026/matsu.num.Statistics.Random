/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.voigt.SkeletalVoigtRnd;
import matsu.num.statistics.random.voigt.SkeletalVoigtRndFactory;

/**
 * <p>
 * Voigt分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * Voigt分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>&sigma;</i>, <i>&gamma;</i> はパラメータ). <br>
 * 
 * P(<i>x</i>) &prop;
 * &int;<sub>-&infin;</sub><sup>&infin;</sup> d<i>t</i>
 * exp[-<i>t</i><sup>2</sup> / (2<i>&sigma;</i><sup>2</sup>)]
 * / [<i>&gamma;</i><sup>2</sup> + (<i>x</i> - <i>t</i>)<sup>2</sup>]
 * </p>
 * 
 * <p>
 * この {@link VoigtRnd} インターフェースでは,
 * 2個のパラメータ (<i>&sigma;</i>, <i>&gamma;</i>)
 * を単一パラメータ <i>&alpha;</i> で代表して扱う. <br>
 * 
 * <i>&alpha;</i> を0以上1以下として,
 * (<i>&sigma;</i>, <i>&gamma;</i>) を次のように定める.
 * </p>
 * 
 * <ul>
 * <li>
 * <i>&sigma;</i> = 1 - <i>&alpha;</i>
 * </li>
 * <li>
 * <i>&gamma;</i> = <i>&alpha;</i>
 * </li>
 * </ul>
 * 
 * <p>
 * 一般の (<i>&sigma;</i>, <i>&gamma;</i>) についての乱数を得たい場合は, <br>
 * <i>&alpha;</i> = <i>&gamma;</i> / (<i>&sigma;</i> + <i>&gamma;</i>) <br>
 * と定め, 生成された乱数を (<i>&sigma;</i> + <i>&gamma;</i>) 倍すればよい.
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
 * @version 22.2
 */
public sealed interface VoigtRnd
        extends FloatingRandomGenerator permits SkeletalVoigtRnd {

    /**
     * パラメータ <i>&alpha;</i> の最小値.
     */
    public static final double LOWER_LIMIT_ALPHA = 0d;

    /**
     * パラメータ <i>&alpha;</i> の最大値.
     */
    public static final double UPPER_LIMIT_ALPHA = 1d;

    /**
     * <p>
     * このインスタンスのパラメータ <i>&alpha;</i> の値を返す.
     * </p>
     * 
     * @return パラメータ <i>&alpha;</i>
     */
    public abstract double alpha();

    /**
     * <p>
     * 指定したパラメータが乱数発生器に適合するかを判定する.
     * </p>
     *
     * @param alpha パラメータ
     * @return パラメータが適合する場合はtrue
     */
    public static boolean acceptsParameter(double alpha) {
        return VoigtRnd.LOWER_LIMIT_ALPHA <= alpha
                && alpha <= VoigtRnd.UPPER_LIMIT_ALPHA;
    }

    /**
     * {@link VoigtRnd} のファクトリ.
     */
    public static sealed interface Factory
            extends RndFactory permits SkeletalVoigtRndFactory {

        /**
         * <p>
         * 指定したパラメータ <i>&alpha;</i> を持つVoigt分布乱数発生器を返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameter(double)} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         * 
         * @param alpha パラメータ
         * @return パラメータ <i>&alpha;</i> のVoigt分布乱数発生器
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract VoigtRnd instanceOf(double alpha);
    }

}
