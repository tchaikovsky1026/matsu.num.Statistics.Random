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
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface NormalRnd extends FloatingRandomGenerator {

    /**
     * {@link NormalRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends ParameterlessRndFactory<NormalRnd> {

    }
}
