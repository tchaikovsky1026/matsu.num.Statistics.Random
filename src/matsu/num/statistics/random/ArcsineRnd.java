/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.6
 */
package matsu.num.statistics.random;

/**
 * <p>
 * 逆正弦 (Arcsine) 分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 逆正弦分布の確率密度関数 P(<i>x</i>) は次のとおりである.
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * 1 / &radic;(<i>x</i>(1 - <i>x</i>))
 * &emsp; (0 &le; <i>x</i> &le; 1)
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
public interface ArcsineRnd extends FloatingRandomGenerator {

    /**
     * {@link ArcsineRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends ParameterlessRndFactory<ArcsineRnd> {

    }
}
