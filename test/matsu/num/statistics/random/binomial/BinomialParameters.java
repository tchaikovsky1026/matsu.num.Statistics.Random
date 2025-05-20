/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.binomial;

import matsu.num.statistics.random.BinomialRnd;

/**
 * 二項分布のパラメータを表現する.
 */
final class BinomialParameters {

    final int n;
    final double p;

    BinomialParameters(int n, double p) {
        super();
        this.n = n;
        this.p = p;

        if (!BinomialRnd.acceptsParameters(n, p)) {
            throw new IllegalArgumentException(
                    "パラメータ不正: n=%s, p=%s".formatted(n, p));
        }
    }

    /**
     * ファクトリから二項乱数生成器を生成して返す.
     */
    BinomialRnd createFrom(BinomialRnd.Factory factory) {
        return factory.instanceOf(n, p);
    }
}
