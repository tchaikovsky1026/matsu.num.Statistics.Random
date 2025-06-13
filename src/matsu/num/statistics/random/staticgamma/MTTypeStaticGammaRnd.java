/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.13
 */
package matsu.num.statistics.random.staticgamma;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Marsaglia-Tsangの方法に基づく, ガンマ乱数のstaticな発生器.
 * 
 * @author Matsuura Y.
 */
public final class MTTypeStaticGammaRnd extends SkeletalStaticGammaRnd {

    private final ExponentialRnd expRnd;
    private final NormalRnd normalRnd;

    private final Exponentiation exponentiation;

    private MTTypeStaticGammaRnd(Exponentiation exponentiation, ExponentialRnd.Factory exponentialRndFactory,
            NormalRnd.Factory normalRndFactory) {
        super();
        this.exponentiation = Objects.requireNonNull(exponentiation);
        this.expRnd = exponentialRndFactory.instance();
        this.normalRnd = normalRndFactory.instance();
    }

    @Override
    double calcNextGamma(BaseRandom random, double k) {
        if (k == 1) {
            return this.expRnd.nextRandom(random);
        }
        if (k > 1) {
            double d = k - (1.0 / 3.0);
            double c = (1.0 / 3.0) / exponentiation.sqrt(d);
            return nextGammaOver1(random, d, c);
        }

        //k<1
        double out;
        do {
            double d = k + (2.0 / 3.0);
            double c = (1.0 / 3.0) / exponentiation.sqrt(d);
            out = nextGammaOver1(random, d, c) *
                    exponentiation.exp(-this.expRnd.nextRandom(random) / k);

            // outが (0 * inf)　となった場合のフォロー
        } while (Double.isNaN(out));

        return out;
    }

    private double nextGammaOver1(BaseRandom random, double d, double c) {
        while (true) {
            double z = this.normalRnd.nextRandom(random);
            double v = 1 + c * z;
            double w = v * v * v;
            double y = d * w;
            if (y < 0.0) {
                continue;
            }
            double u = random.nextDouble();
            double z2 = z * z;
            if (u > z2 * z2 * 0.0331 || z2 * 0.5 + d * exponentiation.log(w) + d - y > exponentiation.log(1 - u)) {
                return y;
            }
        }
    }

    /**
     * {@link matsu.num.statistics.random.StaticGammaRnd.Factory} を生成する.
     * 
     * @param exponentiation 指数関数計算器
     * @param exponentialRndFactory 指数乱数発生器のファクトリ
     * @param normalRndFactory 正規乱数発生器のファクトリ
     * @return StaticGammaRnd乱数のファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static StaticGammaRnd.Factory createFactory(
            Exponentiation exponentiation,
            ExponentialRnd.Factory exponentialRndFactory,
            NormalRnd.Factory normalRndFactory) {

        return new LazyStaticGammaRndFactory(
                () -> new MTTypeStaticGammaRnd(
                        exponentiation, exponentialRndFactory, normalRndFactory));
    }
}
