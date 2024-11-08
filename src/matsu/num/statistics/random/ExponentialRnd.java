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

import matsu.num.statistics.random.exp.ExponentialRndSealed;

/**
 * <p>
 * 標準指数分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準指数分布の確率密度関数 P(<i>x</i>) は次のとおりである.
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * exp(-<i>x</i>)
 * &emsp; (<i>x</i> &ge; 0)
 * </li>
 * 
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
 * @version 22.1
 */
public sealed interface ExponentialRnd
        extends FloatingRandomGenerator permits ExponentialRndSealed {

    /**
     * {@link ExponentialRnd} のファクトリ.
     */
    public static sealed interface Factory
            extends RndFactory permits ExponentialRndSealed.FactorySealed {

        /**
         * <p>
         * 標準指数分布乱数発生器インスタンスを返す.
         * </p>
         *
         * @return 標準指数分布乱数発生器インスタンス
         */
        public abstract ExponentialRnd instance();

    }
}
