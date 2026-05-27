/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.5.27
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.accomp.FloatingRandomGenerator;
import matsu.num.statistics.random.accomp.ParameterlessRndFactory;

/**
 * <p>
 * 標準正規分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準正規分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
 * (ただし, 境界値が発生する可能性がある.)
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop; exp(-<i>x</i><sup>2</sup> / 2)
 * &emsp; (-&infin; &lt; <i>x</i> &lt; +&infin;)
 * </li>
 * </ul>
 *
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 * @deprecated
 *                 この乱数発生器は v26 以降に削除される.
 *                 {@link BaseRandom#nextGaussian()}
 *                 や Java の標準 APIである
 *                 {@link java.util.random.RandomGenerator#nextGaussian()}
 *                 を使用すべき.
 */
@Deprecated(forRemoval = true, since = "25.12")
public interface NormalRnd extends FloatingRandomGenerator {

    /**
     * {@link NormalRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     * 
     * @deprecated
     *                 この乱数発生器は v26 以降に削除される
     *                 ({@link ExponentialRnd} の説明の通り).
     */
    @Deprecated(forRemoval = true, since = "25.12")
    public static interface Factory extends ParameterlessRndFactory<NormalRnd> {

    }
}
