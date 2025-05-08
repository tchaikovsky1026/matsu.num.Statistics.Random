/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.8
 */
package matsu.num.statistics.random.weibull;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.WeibullRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * 標準Gumbel分布を用いた標準Weibull分布乱数発生器の実装.
 *
 * @author Matsuura Y.
 */
public final class GumbelBasedWeibullRnd extends SkeletalWeibullRnd {

    private final double invK;

    private final Exponentiation exponentiation;
    private final GumbelRnd gumRnd;

    private GumbelBasedWeibullRnd(double k,
            Exponentiation exponentiation, GumbelRnd.Factory gumbelRndFactory) {
        super(k);

        this.invK = 1 / k;
        this.exponentiation = exponentiation;
        this.gumRnd = gumbelRndFactory.instance();
    }

    @Override
    public double nextRandom(BaseRandom random) {
        return exponentiation.exp(-this.gumRnd.nextRandom(random) * this.invK);
    }

    /**
     * 標準Gumbelベースの {@link WeibullRnd} のファクトリインスタンスを生成する.
     * 
     * @param exponentiation 指数関数の計算
     * @param gumbelRndFactory Gumbel乱数生成器のファクトリ
     * @return 乱数生成器ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static WeibullRnd.Factory createFactory(
            Exponentiation exponentiation, GumbelRnd.Factory gumbelRndFactory) {

        return new Factory(
                Objects.requireNonNull(exponentiation),
                Objects.requireNonNull(gumbelRndFactory));
    }

    private static final class Factory extends SkeletalWeibullRnd.Factory {

        private final Exponentiation exponentiation;
        private final GumbelRnd.Factory gumbelRndFactory;

        Factory(Exponentiation exponentiation, GumbelRnd.Factory gumbelRndFactory) {
            super();
            this.exponentiation = Objects.requireNonNull(exponentiation);
            this.gumbelRndFactory = Objects.requireNonNull(gumbelRndFactory);
        }

        @Override
        WeibullRnd createInstanceOf(double k) {
            return new GumbelBasedWeibullRnd(k, this.exponentiation, this.gumbelRndFactory);
        }
    }
}
