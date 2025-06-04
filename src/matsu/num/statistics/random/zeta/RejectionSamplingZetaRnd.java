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

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.ZetaRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * <p>
 * 棄却法によるゼータ乱数発生器を扱う.
 * </p>
 * 
 * <hr>
 * 
 * <p>
 * <b>
 * 技術 &middot; 実装メモ
 * </b>
 * </p>
 * 
 * <p>
 * ゼータ分布は確率質量関数が n^(-s) であり,
 * 累積分布関数がおよそ, [1 - n^(-s+1)] である. <br>
 * そこで, Z を1以上の連続値をとる確率変数で,
 * 累積分布関数が <br>
 * F_Z(z) = 1 - z^(-s+1) <br>
 * であるとする. <br>
 * そして, X = floor(Z) により, 正整数をとる確率変数 X を定める. <br>
 * X の確率質量関数は, <br>
 * P(X = n) = n^(-s+1) - (n+1)^(-s+1) <br>
 * である. <br>
 * これを提案分布とすることで, 効果的な棄却法が実現する.
 * </p>
 * 
 * @author Matsuura Y.
 */
public final class RejectionSamplingZetaRnd extends SkeletalZetaRnd {

    private static final double LN_0_5 = Math.log(0.5);

    private final Exponentiation exponentiation;
    private final ExponentialRnd exponentialRnd;

    private final double s_minus_1;
    private final double inv_s_minus_1;

    private final double c;

    /**
     * 公開されないコンストラクタ. <br>
     * 引数チェックは行われていないので注意.
     */
    private RejectionSamplingZetaRnd(double s,
            Exponentiation exponentiation,
            ExponentialRnd.Factory exponentialRndFactory) {
        super(s);
        this.exponentiation = exponentiation;
        this.exponentialRnd = exponentialRndFactory.instance();

        this.s_minus_1 = s - 1;
        this.inv_s_minus_1 = 1d / this.s_minus_1;
        this.c = 1 - exponentiation.exp(LN_0_5 * s_minus_1);
    }

    @Override
    public int nextRandom(BaseRandom random) {
        while (true) {
            double z = exponentiation.exp(exponentialRnd.nextRandom(random) * inv_s_minus_1);
            if (z > Integer.MAX_VALUE) {
                continue;
            }

            int y = (int) z;
            double w = 1d / y;
            double cw = c * w;

            double u = random.nextDouble();

            if (u <= cw ||
                    u * (1 - exponentiation.pow(1 + w, -s_minus_1)) <= cw) {
                return y;
            }
        }
    }

    /**
     * {@link matsu.num.statistics.random.ZetaRnd.Factory} を生成する.
     * 
     * @param exponentiation 指数関数の計算
     * @param exponentialRndFactory 指数乱数発生器のファクトリ
     * @return ゼータ乱数のファクトリ
     * @throws NullPointerException 引数がnullの場合
     */
    public static ZetaRnd.Factory createFactory(Exponentiation exponentiation,
            ExponentialRnd.Factory exponentialRndFactory) {
        return new Factory(
                Objects.requireNonNull(exponentiation),
                Objects.requireNonNull(exponentialRndFactory));
    }

    private static final class Factory extends SkeletalZetaRnd.Factory {

        private final Exponentiation exponentiation;
        private final ExponentialRnd.Factory exponentialRndFactory;

        /**
         * 非公開のコンストラクタ. <br>
         * 生成はstaticメソッドにより行われる.
         */
        Factory(Exponentiation exponentiation, ExponentialRnd.Factory exponentialRndFactory) {
            super();
            this.exponentiation = exponentiation;
            this.exponentialRndFactory = exponentialRndFactory;
        }

        @Override
        ZetaRnd createInstanceOf(double s) {
            return new RejectionSamplingZetaRnd(s, exponentiation, exponentialRndFactory);
        }
    }
}
