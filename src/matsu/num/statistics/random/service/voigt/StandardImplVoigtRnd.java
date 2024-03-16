/**
 * 2024.1.14
 */
package matsu.num.statistics.random.service.voigt;

import matsu.num.statistics.random.CauthyRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.VoigtRnd;

/**
 * VoigtRndのスタンダードな実装.
 * 
 * @author Matsuura Y.
 * @version 18.0
 */
final class StandardImplVoigtRnd implements VoigtRnd {

    private final double alpha;
    private final double reflectedAlpha;

    private final NormalRnd normalRnd;
    private final CauthyRnd cauthyRnd;

    StandardImplVoigtRnd(double alpha,
            NormalRnd.Factory normalRndFactory, CauthyRnd.Factory cauthyRndFactory) {
        super();
        this.alpha = alpha;
        this.reflectedAlpha = 1 - alpha;
        this.normalRnd = normalRndFactory.instance();
        this.cauthyRnd = cauthyRndFactory.instance();
    }

    @Override
    public double alpha() {
        return this.alpha;
    }

    @Override
    public double nextRandom(BaseRandom random) {
        return this.alpha * this.cauthyRnd.nextRandom(random)
                + this.reflectedAlpha * this.normalRnd.nextRandom(random);
    }

    @Override
    public String toString() {
        return String.format("VoigtRnd(%s)", this.alpha);
    }

}
