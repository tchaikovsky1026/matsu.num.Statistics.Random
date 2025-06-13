/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.6
 */
package matsu.num.statistics.random.beta;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.GammaRnd;

/**
 * ガンマ分布乱数器を利用した, ベータ分布に従う乱数発生器.
 *
 * @author Matsuura Y.
 */
public final class GammaBasedBetaRnd extends SkeletalBetaRnd {

    private final GammaRnd gammaRndA;
    private final GammaRnd gammaRndB;

    /**
     * 指定した形状パラメータのベータ分布乱数発生器インスタンスを返す. <br>
     * 形状パラメータ <i>a</i>,<i>b</i> は,
     * {@code 1.0E-2 <= a <= 1.0E+14 && 1.0E-2 <= b <= 1.0E+14} でなければならない.
     *
     * @param a 形状パラメータa
     * @param b 形状パラメータb
     */
    private GammaBasedBetaRnd(double a, double b, GammaRnd.Factory gammaRndFactory) {
        super(a, b);

        this.gammaRndA = gammaRndFactory.instanceOf(a);
        this.gammaRndB = gammaRndFactory.instanceOf(b);
    }

    @Override
    public final double nextRandom(BaseRandom random) {
        double u1 = this.gammaRndA.nextRandom(random);
        double u2 = this.gammaRndB.nextRandom(random);
        double out = u1 / (u1 + u2);

        // u1 == 0 && u2 == 0 の場合に関する分岐
        return Double.isNaN(out) ? 0d : out;
    }

    @Override
    public final double nextBetaPrime(BaseRandom random) {
        double u1 = this.gammaRndA.nextRandom(random);
        double u2 = this.gammaRndB.nextRandom(random);
        double out = u1 / u2;

        // u1 == 0 && u2 == 0 の場合に関する分岐
        return Double.isNaN(out) ? 0d : out;
    }

    /**
     * ガンマ乱数発生器に基づくベータ乱数発生器のファクトリ.
     * 
     * @param gammaRndFactory ガンマ乱数生成器ファクトリ
     * @return ベータ乱数生成器ファクトリ
     * @throws NullPointerException 引数がnullの場合
     */
    public static BetaRnd.Factory createFactory(GammaRnd.Factory gammaRndFactory) {
        return new Factory(Objects.requireNonNull(gammaRndFactory));
    }

    /**
     * ガンマ乱数発生器に基づくベータ乱数発生器のファクトリ.
     */
    private static final class Factory extends SkeletalBetaRnd.Factory {

        private final GammaRnd.Factory gammaRndFactory;

        Factory(GammaRnd.Factory gammaRndFactory) {
            this.gammaRndFactory = gammaRndFactory;
        }

        @Override
        BetaRnd createInstanceOf(double a, double b) {
            return new GammaBasedBetaRnd(a, b, this.gammaRndFactory);
        }
    }
}
