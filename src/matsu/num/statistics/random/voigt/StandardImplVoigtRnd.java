/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.6.8
 */
package matsu.num.statistics.random.voigt;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.UnexpectedRandomGenerationException;
import matsu.num.statistics.random.VoigtRnd;

/**
 * VoigtRndのスタンダードな実装.
 * 
 * @author Matsuura Y.
 */
public final class StandardImplVoigtRnd extends SkeletalVoigtRnd {

    private final double reflectedAlpha;

    private final CauchyRnd cauchyRnd;

    private StandardImplVoigtRnd(double alpha, CauchyRnd.Factory cauchyRndFactory) {
        super(alpha);

        this.reflectedAlpha = 1 - alpha;
        this.cauchyRnd = cauchyRndFactory.instance();
    }

    @Override
    public double nextRandom(BaseRandom random) {
        double out;

        // 乱数生成異常を検知するためのiterationCount
        int iteCount = 0;
        do {
            iteCount++;
            if (iteCount >= Integer.MAX_VALUE) {
                // 乱数生成の異常
                throw new UnexpectedRandomGenerationException();
            }

            out = this.alpha * this.cauchyRnd.nextRandom(random)
                    + this.reflectedAlpha * random.nextGaussian();

            // outが NaN　となった場合のフォロー
        } while (Double.isNaN(out));
        return out;
    }

    /**
     * スタンダードな {@link VoigtRnd} のファクトリインスタンスを生成する.
     * 
     * @param cauchyRndFactory Cauchy乱数生成器のファクトリ
     * @return 乱数生成器ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static VoigtRnd.Factory createFactory(CauchyRnd.Factory cauchyRndFactory) {

        return new Factory(
                Objects.requireNonNull(cauchyRndFactory));
    }

    private static final class Factory extends SkeletalVoigtRnd.Factory {

        private final CauchyRnd.Factory cauchyRndFactory;

        Factory(CauchyRnd.Factory cauchyRndFactory) {
            super();
            this.cauchyRndFactory = Objects.requireNonNull(cauchyRndFactory);
        }

        @Override
        VoigtRnd createInstanceOf(double alpha) {
            return new StandardImplVoigtRnd(alpha, this.cauchyRndFactory);
        }
    }
}
