/*
 * Copyright © 2026 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.inner;

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

        if (!InnerStaticBinomialRnd.acceptsParameters(n, p)) {
            throw new IllegalArgumentException(
                    "illegal parameter: n=%s, p=%s".formatted(n, p));
        }
    }
}
