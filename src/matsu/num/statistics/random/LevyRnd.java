/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random;

/**
 * <p>
 * 標準 L&eacute;vy 分布に従う乱数を生成する.
 * </p>
 * 
 * <p>
 * 標準 L&eacute;vy 分布の確率密度関数 P(<i>x</i>) は次のとおりである.
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
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface LevyRnd extends FloatingRandomGenerator {

    /**
     * {@link LevyRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends RndFactory {

        /**
         * <p>
         * 標準 L&eacute;vy 分布乱数発生器インスタンスを返す.
         * </p>
         *
         * @return 標準 L&eacute;vy 分布乱数発生器インスタンス
         */
        public abstract LevyRnd instance();
    }
}
