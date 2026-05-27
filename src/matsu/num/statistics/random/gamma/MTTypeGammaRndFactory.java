/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.5.27
 */
package matsu.num.statistics.random.gamma;

import java.util.Objects;

import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Marsaglia-Tsangに基づく, ガンマ乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 */
public final class MTTypeGammaRndFactory extends SkeletalGammaRnd.Factory {

    private final GammaRnd gammaRndAt1;

    private final Exponentiation exponentiation;

    private MTTypeGammaRndFactory(Exponentiation exponentiation) {

        super();
        this.exponentiation = Objects.requireNonNull(exponentiation);
        this.gammaRndAt1 = new ExpBasedGammaRndAt1();
    }

    @Override
    GammaRnd createInstanceOf(double k) {
        //MarsagliaTsangに基づく乱数発生器
        if (k < 1) {
            return new MTTypeGammaRndUnder1(k, this.exponentiation);
        }
        if (k == 1) {
            return gammaRndAt1;
        }

        return new MTTypeGammaRndOver1(k, this.exponentiation);
    }

    /**
     * Marsaglia-Tsangに基づく {@link GammaRnd} のファクトリインスタンスを生成する.
     * 
     * @param exponentiation 指数関数の計算
     * @return 乱数生成器ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static GammaRnd.Factory create(Exponentiation exponentiation) {
        return new MTTypeGammaRndFactory(exponentiation);
    }
}
