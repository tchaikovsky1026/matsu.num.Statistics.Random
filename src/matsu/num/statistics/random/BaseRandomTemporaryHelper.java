/*
 * Copyright © 2026 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.5.26
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.exp.LongSubstitutedZiggExponentialRnd;
import matsu.num.statistics.random.lib.Exponentiation;
import matsu.num.statistics.random.norm.LongSubstitutedZiggNormalRnd;

/**
 * {@link BaseRandom#nextExponential()},
 * {@link BaseRandom#nextGaussian()}
 * のデフォルトメソッドを提供するための一次的なヘルパ.
 * 
 * @author Matsuura Y.
 * @deprecated
 *                 v26以降での {@link ExponentialRnd},
 *                 {@link NormalRnd} の削除とともに, このヘルパも削除.
 */
@SuppressWarnings("removal")
@Deprecated
final class BaseRandomTemporaryHelper {

    private BaseRandomTemporaryHelper() {
        // インスタンス化不可
        throw new AssertionError();
    }

    private static final Exponentiation exponentiation = new Exponentiation() {
    };
    private static final ExponentialRnd.Factory expRndFactory = LongSubstitutedZiggExponentialRnd
            .createFactory(exponentiation);

    /** BaseRandomのデフォルト実装で使う指数乱数生成器. */
    static final ExponentialRnd EXPONENTIAL_RND = expRndFactory.instance();

    /** BaseRandomのデフォルト実装で使う正規乱数生成器. */
    static final NormalRnd NORMAL_RND = LongSubstitutedZiggNormalRnd
            .createFactory(exponentiation, expRndFactory).instance();
}
