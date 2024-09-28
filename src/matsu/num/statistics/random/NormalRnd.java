/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.26
 */
package matsu.num.statistics.random;

/**
 * <p>
 * 標準正規分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準正規分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
 * P(<i>x</i>) &prop; exp(-<i>x</i><sup>2</sup> / 2)
 * </p>
 *
 * @author Matsuura Y.
 * @version 21.0
 */
public interface NormalRnd extends FloatingRandomGenerator {

    /**
     * {@link NormalRnd} のファクトリ.
     */
    public static interface Factory {

        /**
         * <p>
         * 標準正規分布乱数発生器インスタンスを返す.
         * </p>
         *
         * @return 標準正規分布乱数発生器インスタンス
         */
        public abstract NormalRnd instance();
    }
}
