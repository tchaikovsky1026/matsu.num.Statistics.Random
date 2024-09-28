/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.24
 */
package matsu.num.statistics.random;

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
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 *
 * @author Matsuura Y.
 * @version 21.0
 */
public interface ExponentialRnd extends FloatingRandomGenerator {

    /**
     * {@link ExponentialRnd} のファクトリ.
     */
    public static interface Factory {

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
