/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.6
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.accomp.FloatingRandomGenerator;

/**
 * <p>
 * 標準Gumbel分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準Gumbel分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
 * (ただし, 境界値が発生する可能性がある.)
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop; exp(-<i>x</i> - exp(-<i>x</i>))
 * &emsp; (-&infin; &lt; <i>x</i> &lt; +&infin;)
 * </li>
 * </ul>
 *
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface GumbelRnd extends FloatingRandomGenerator {

    /**
     * {@link GumbelRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends ParameterlessRndFactory<GumbelRnd> {

    }
}
