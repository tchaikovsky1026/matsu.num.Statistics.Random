/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random;

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
 * @author Matsuura Y.
 * @version 20.0
 */
public interface LevyRnd extends FloatingRandomGenerator {

    /**
     * {@link LevyRnd} のファクトリ.
     */
    public static interface Factory extends RandomGeneratorFactory {

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
