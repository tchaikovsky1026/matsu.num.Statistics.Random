/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.13
 */
package matsu.num.statistics.random.binomial;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;

/**
 * 試行回数が0である二項分布乱数発生器を表現する.
 * 
 * @author Matsuura Y.
 */
final class ZeroTrialBinomialRnd extends SkeletalBinomialRnd {

    /**
     * 唯一のコンストラクタ. <br>
     * 引数がチェックされていないので, 公開してはならない.
     */
    ZeroTrialBinomialRnd(double p) {
        super(0, p);
    }

    @Override
    public int nextRandom(BaseRandom random) {
        Objects.requireNonNull(random);
        return 0;
    }
}
