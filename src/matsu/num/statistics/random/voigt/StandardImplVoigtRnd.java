/**
 * 2024.1.9
 */
package matsu.num.statistics.random.voigt;

import matsu.num.statistics.random.CauthyRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.VoigtRnd;
import matsu.num.statistics.random.cauthy.CauthyRndFactory;
import matsu.num.statistics.random.norm.NormalRndFactory;

/**
 * {@linkplain VoigtRnd} のスタンダードな実装.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
final class StandardImplVoigtRnd implements VoigtRnd {

    private final double alpha;
    private final double reflectedAlpha;

    private final NormalRnd normalRnd = NormalRndFactory.instance();
    private final CauthyRnd cauthyRnd = CauthyRndFactory.instance();

    /**
     * パラメータ <i>&alpha;</i> を指定してインスタンスを構築する.
     * 
     * @param alpha &alpha;
     * @throws IllegalArgumentException alphaが不正である場合
     */
    StandardImplVoigtRnd(double alpha) {
        if (!(0d <= alpha && alpha <= 1d)) {
            throw new IllegalArgumentException(String.format("alphaが不正:%s", alpha));
        }
        this.alpha = alpha;
        this.reflectedAlpha = 1 - alpha;
    }

    @Override
    public double alpha() {
        return this.alpha;
    }

    @Override
    public double nextRandom(Random random) {
        return this.alpha * this.cauthyRnd.nextRandom(random)
                + this.reflectedAlpha * this.normalRnd.nextRandom(random);
    }

    @Override
    public String toString() {
        return String.format("VoigtRnd(%s)", this.alpha);
    }

}
