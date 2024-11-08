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

import matsu.num.statistics.random.cauchy.CauchyRndSealed;

/**
 * <p>
 * 標準Cauchy分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準Cauchy分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
 * P(<i>x</i>) &prop;
 * 1 / (1 + <i>x</i><sup><i>2</i></sup>)
 * </p>
 * 
 * <p>
 * 標準Cauchy分布は自由度1のStudent-t分布に一致する.
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
 * @version 22.1
 */
public sealed interface CauchyRnd extends FloatingRandomGenerator permits CauchyRndSealed {

    /**
     * <p>
     * この標準Cauchy分布と同等な, Student-t分布としてのビューを返す.
     * </p>
     * 
     * <p>
     * 戻り値のStudent-t分布の自由度は1である. <br>
     * ビューの変更であるため,
     * {@link CauchyRnd} としての
     * {@link #nextRandom(BaseRandom)}
     * と
     * {@link TDistributionRnd} としての
     * {@link TDistributionRnd#nextRandom(BaseRandom)}
     * とは同一の処理を行う.
     * </p>
     * 
     * @return 標準Cauchy分布と同等な, 自由度1のStudent-t分布
     */
    public abstract TDistributionRnd asTDistributionRnd();

    /**
     * {@link CauchyRnd} のファクトリ.
     */
    public static sealed interface Factory
            extends RndFactory permits CauchyRndSealed.FactorySealed {

        /**
         * <p>
         * 標準Cauchy分布乱数発生器インスタンスを返す.
         * </p>
         *
         * @return 標準Cauchy分布乱数発生器インスタンス
         */
        public abstract CauchyRnd instance();

    }
}
