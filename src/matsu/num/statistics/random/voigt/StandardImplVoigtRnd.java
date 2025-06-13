/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.13
 */
package matsu.num.statistics.random.voigt;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.VoigtRnd;

/**
 * VoigtRndのスタンダードな実装.
 * 
 * @author Matsuura Y.
 */
public final class StandardImplVoigtRnd extends SkeletalVoigtRnd {

    private final double reflectedAlpha;

    private final NormalRnd normalRnd;
    private final CauchyRnd cauchyRnd;

    private StandardImplVoigtRnd(double alpha,
            NormalRnd.Factory normalRndFactory, CauchyRnd.Factory cauchyRndFactory) {
        super(alpha);

        this.reflectedAlpha = 1 - alpha;
        this.normalRnd = normalRndFactory.instance();
        this.cauchyRnd = cauchyRndFactory.instance();
    }

    @Override
    public double nextRandom(BaseRandom random) {
        double out;
        do {
            out = this.alpha * this.cauchyRnd.nextRandom(random)
                    + this.reflectedAlpha * this.normalRnd.nextRandom(random);

            // outが NaN　となった場合のフォロー
        } while (Double.isNaN(out));

        return out;
    }

    /**
     * スタンダードな {@link VoigtRnd} のファクトリインスタンスを生成する.
     * 
     * @param normalRndFactory 正規乱数生成器のファクトリ
     * @param cauchyRndFactory Cauchy乱数生成器のファクトリ
     * @return 乱数生成器ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static VoigtRnd.Factory createFactory(
            NormalRnd.Factory normalRndFactory, CauchyRnd.Factory cauchyRndFactory) {

        return new Factory(
                Objects.requireNonNull(normalRndFactory),
                Objects.requireNonNull(cauchyRndFactory));
    }

    private static final class Factory extends SkeletalVoigtRnd.Factory {

        private final NormalRnd.Factory normalRndFactory;
        private final CauchyRnd.Factory cauchyRndFactory;

        Factory(NormalRnd.Factory normalRndFactory, CauchyRnd.Factory cauchyRndFactory) {
            super();
            this.normalRndFactory = Objects.requireNonNull(normalRndFactory);
            this.cauchyRndFactory = Objects.requireNonNull(cauchyRndFactory);
        }

        @Override
        VoigtRnd createInstanceOf(double alpha) {
            return new StandardImplVoigtRnd(alpha, this.normalRndFactory, this.cauchyRndFactory);
        }
    }
}
