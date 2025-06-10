/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.10
 */
package matsu.num.statistics.random.logseries;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.LogarithmicSeriesRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * <p>
 * 幾何分布との混合をベースとする, 対数級数分布乱数発生器の実装.
 * </p>
 * 
 * <p>
 * 対数級数分布の確率密度関数
 * P(k) &propto;
 * (p^(k - 1) / k),
 * k = 1, 2, ...
 * を発生させる方法について, 次の戦略を考える.
 * </p>
 * 
 * <ol>
 * <li>y を発生</li>
 * <li>(1-y) を成功確率とする幾何分布から k を発生させる: <br>
 * P(k|y) = y^(k-1) * (1-y)
 * </li>
 * </ol>
 * 
 * <p>
 * 上記戦略より, yが0からpまでとるとして,
 * 1 / (1-y) に比例する確率密度関数によって y を発生させればよいとわかる. <br>
 * F(Y) = log(1 - y) / log(1 - p) より, <br>
 * 標準一様乱数 u により, y = 1 - (1 - p)^u とすればよい.
 * </p>
 * 
 * @author Matsuura Y.
 */
public final class GeometricMixBasedLogarithmicSeriesRnd
        extends SkeletalLogarithmicSeriesRnd {

    private final Exponentiation exponentiation;
    private final ExponentialRnd exponentialRnd;

    private final double log1mp;

    /**
     * 非公開が必須. <br>
     * バリデーションされていない.
     */
    private GeometricMixBasedLogarithmicSeriesRnd(
            double p,
            Exponentiation exponentiation,
            ExponentialRnd.Factory exponentialRndFactory) {
        super(p);

        this.exponentiation = exponentiation;
        this.exponentialRnd = exponentialRndFactory.instance();
        this.log1mp = exponentiation.log1p(-p);
    }

    @Override
    public int nextRandom(BaseRandom random) {

        while (true) {
            double u = random.nextDouble();
            double y = 1 - exponentiation.exp(u * log1mp);
            if (y == 1d) {
                continue;
            }
            if (y == 0d) {
                return 1;
            }

            double e = exponentialRnd.nextRandom(random);
            double w = -e / exponentiation.log(y);
            if (w >= Integer.MAX_VALUE) {
                continue;
            }

            return 1 + ((int) w);
        }
    }

    /**
     * ファクトリを生成して返す.
     * 
     * @param exponentiation 指数関数の計算
     * @param exponentialRndFactory 指数乱数生成器のファクトリ
     * @return ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static LogarithmicSeriesRnd.Factory createFactory(
            Exponentiation exponentiation, ExponentialRnd.Factory exponentialRndFactory) {
        return new Factory(
                Objects.requireNonNull(exponentiation),
                Objects.requireNonNull(exponentialRndFactory));
    }

    private static final class Factory extends SkeletalLogarithmicSeriesRnd.Factory {

        private final Exponentiation exponentiation;
        private final ExponentialRnd.Factory exponentialRndFactory;

        /**
         * staticメソッドから呼ばれる.
         */
        Factory(Exponentiation exponentiation,
                ExponentialRnd.Factory exponentialRndFactory) {
            super();

            this.exponentiation = exponentiation;
            this.exponentialRndFactory = exponentialRndFactory;
        }

        @Override
        LogarithmicSeriesRnd createInstanceOf(double p) {
            return new GeometricMixBasedLogarithmicSeriesRnd(
                    p, exponentiation, exponentialRndFactory);
        }
    }

}
