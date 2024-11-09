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

import matsu.num.statistics.random.levy.LevyRndFactory;
import matsu.num.statistics.random.levy.SkeletalLevyRnd;

/**
 * <p>
 * 標準L&eacute;vy分布に従う乱数を生成する.
 * </p>
 * 
 * <p>
 * 標準L&eacute;vy分布の確率密度関数 P(<i>x</i>) は次のとおりである.
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup>-3/2</sup> exp[-1 / (2<i>x</i>)]
 * &emsp; (<i>x</i> &ge; 0)
 * </li>
 * 
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
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
public sealed interface LevyRnd extends FloatingRandomGenerator permits SkeletalLevyRnd {

    /**
     * {@link LevyRnd} のファクトリ.
     */
    public static sealed interface Factory extends RndFactory permits LevyRndFactory {

        /**
         * <p>
         * 標準L&eacute;vy分布乱数発生器インスタンスを返す.
         * </p>
         *
         * @return 標準L&eacute;vy分布乱数発生器インスタンス
         */
        public abstract LevyRnd instance();
    }
}
