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
 * 標準Gumbel分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準Gumbel分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
 * P(<i>x</i>) &prop; exp(-<i>x</i> - exp(-<i>x</i>))
 * </p>
 *
 * @author Matsuura Y.
 * @version 20.0
 */
public interface GumbelRnd extends FloatingRandomGenerator {

    /**
     * {@link GumbelRnd} のファクトリ.
     */
    public static interface Factory extends RandomGeneratorFactory {

        /**
         * <p>
         * 標準ガンベル分布乱数発生器インスタンスを返す.
         * </p>
         *
         * @return 標準ガンベル分布乱数発生器インスタンス
         */
        public abstract GumbelRnd instance();
    }
}
