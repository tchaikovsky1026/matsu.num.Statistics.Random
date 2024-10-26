/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.25
 */
package matsu.num.statistics.random;

/**
 * <p>
 * 標準ロジスティック分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準ロジスティック分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
 * P(<i>x</i>) &prop;
 * exp(-<i>x</i>)
 * /
 * (1 + exp(-<i>x</i>))<sup>2</sup>
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
 * @version 22.0
 */
public sealed interface LogisticRnd
        extends FloatingRandomGenerator permits matsu.num.statistics.random.logi.LogisticRnd {

    /**
     * {@link LogisticRnd} のファクトリ.
     */
    public static sealed interface Factory permits matsu.num.statistics.random.logi.LogisticRnd.Factory {

        /**
         * <p>
         * 標準ロジスティック分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * @return 標準ロジスティック分布乱数発生器インスタンス
         */
        public abstract LogisticRnd instance();
    }
}
