/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.3
 */
package matsu.num.statistics.random.zeta;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ZetaRnd;

/**
 * 素朴な逆関数法によるゼータ乱数生成器を扱う.
 * 
 * @author Matsuura Y.
 */
public final class NaiveInversionBasedZetaRnd
        extends SkeletalZetaRnd {

    private final double zetaS;

    /**
     * 非公開のコンストラクタ.
     */
    private NaiveInversionBasedZetaRnd(double s) {
        super(s);

        this.zetaS = RiemannZetaFunction.at(s);
    }

    @Override
    public int nextRandom(BaseRandom random) {
        // sum_{n=1} n^(-s) と比較する, u * zeta(s) の値
        double modifiedU = this.zetaS * random.nextDouble();

        double sum = 0d;

        for (int n = 1; n <= 100_000_000; n++) {
            sum += Math.pow(1d / n, s);
            if (modifiedU <= sum) {
                return n;
            }
        }
        return 1;
    }

    /**
     * {@link matsu.num.statistics.random.ZetaRnd.Factory} を生成する.
     * 
     * @return ゼータ乱数のファクトリ
     */
    public static ZetaRnd.Factory createFactory() {
        return new Factory();
    }

    private static final class Factory extends SkeletalZetaRnd.Factory {

        /**
         * 非公開のコンストラクタ. <br>
         * 生成はstaticメソッドにより行われる.
         */
        Factory() {
            super();
        }

        @Override
        ZetaRnd createInstanceOf(double s) {
            return new NaiveInversionBasedZetaRnd(s);
        }
    }
}
