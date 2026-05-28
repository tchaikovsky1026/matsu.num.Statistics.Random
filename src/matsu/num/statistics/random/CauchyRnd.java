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
import matsu.num.statistics.random.accomp.ParameterlessRndFactory;

/**
 * <p>
 * 標準 Cauchy 分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準 Cauchy 分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
 * (ただし, 境界値が発生する可能性がある.)
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * 1 / (1 + <i>x</i><sup><i>2</i></sup>)
 * &emsp; (-&infin; &lt; <i>x</i> &lt; +&infin;)
 * </li>
 * </ul>
 * 
 * <p>
 * 標準 Cauchy 分布は自由度1の Student-t 分布に一致する.
 * </p>
 * 
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface CauchyRnd extends FloatingRandomGenerator {

    /**
     * {@link CauchyRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends ParameterlessRndFactory<CauchyRnd> {

    }
}
