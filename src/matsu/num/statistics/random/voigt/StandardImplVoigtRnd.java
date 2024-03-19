/**
 * 2024.3.22
 */
package matsu.num.statistics.random.voigt;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.VoigtRnd;

/**
 * VoigtRndのスタンダードな実装.
 * 
 * @author Matsuura Y.
 * @version 19.0
 */
final class StandardImplVoigtRnd implements VoigtRnd {

    private final double alpha;
    private final double reflectedAlpha;

    private final NormalRnd normalRnd;
    private final CauchyRnd cauchyRnd;

    StandardImplVoigtRnd(double alpha,
            NormalRnd.Factory normalRndFactory, CauchyRnd.Factory cauchyRndFactory) {
        super();
        this.alpha = alpha;
        this.reflectedAlpha = 1 - alpha;
        this.normalRnd = normalRndFactory.instance();
        this.cauchyRnd = cauchyRndFactory.instance();
    }

    @Override
    public double alpha() {
        return this.alpha;
    }

    @Override
    public double nextRandom(BaseRandom random) {
        return this.alpha * this.cauchyRnd.nextRandom(random)
                + this.reflectedAlpha * this.normalRnd.nextRandom(random);
    }

    @Override
    public String toString() {
        return String.format("VoigtRnd(%s)", this.alpha);
    }

}
