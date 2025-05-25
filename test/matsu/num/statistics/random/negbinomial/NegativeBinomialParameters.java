/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.negbinomial;

import matsu.num.statistics.random.NegativeBinomialRnd;

/**
 * パラメータを表現する.
 */
final class NegativeBinomialParameters {

    final int r;
    final double p;

    NegativeBinomialParameters(int r, double p) {
        super();
        this.r = r;
        this.p = p;

        if (!NegativeBinomialRnd.acceptsParameters(r, p)) {
            throw new IllegalArgumentException(
                    "パラメータ不正: r=%s, p=%s".formatted(r, p));
        }
    }

    /**
     * ファクトリから乱数生成器を生成して返す.
     */
    NegativeBinomialRnd createFrom(NegativeBinomialRnd.Factory factory) {
        return factory.instanceOf(r, p);
    }

    @Override
    public String toString() {
        return "NegativeBinomialParameter(r = %s, p = %s)".formatted(r, p);
    }
}
