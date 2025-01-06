/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.voigt;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.NormalRnd;

/**
 * VoigtRndのスタンダードな実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
final class StandardImplVoigtRnd extends SkeletalVoigtRnd {

    private final double reflectedAlpha;

    private final NormalRnd normalRnd;
    private final CauchyRnd cauchyRnd;

    StandardImplVoigtRnd(double alpha,
            NormalRnd.Factory normalRndFactory, CauchyRnd.Factory cauchyRndFactory) {
        super(alpha);

        this.reflectedAlpha = 1 - alpha;
        this.normalRnd = normalRndFactory.instance();
        this.cauchyRnd = cauchyRndFactory.instance();
    }

    @Override
    public double nextRandom(BaseRandom random) {
        return this.alpha * this.cauchyRnd.nextRandom(random)
                + this.reflectedAlpha * this.normalRnd.nextRandom(random);
    }
}
