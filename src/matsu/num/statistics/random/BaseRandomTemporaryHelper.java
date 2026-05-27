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
 * 次バージョンで削除される可能性がある, 一次的なヘルパ.
 * 
 * <p>
 * {@link ExponentialRnd}, {@link NormalRnd} の削除とともに, このヘルパも削除.
 * </p>
 * 
 * @author Matsuura Y.
 */
@SuppressWarnings({ "removal", "deprecation" })
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
