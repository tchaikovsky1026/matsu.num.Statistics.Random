/**
 * 2024.2.23
 */
package matsu.num.statistics.random.service.voigt;

import matsu.num.statistics.random.CauthyRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.VoigtRnd;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * VoigtRndのスタンダード実装のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class StandardImplVoigtRndFactory implements VoigtRnd.Factory {

    private final NormalRnd.Factory normalRndFactory;
    private final CauthyRnd.Factory cauthyRndFactory;

    public StandardImplVoigtRndFactory(RandomGeneratorFactoryProvider provider) {
        super();
        this.normalRndFactory = provider.get(RandomGeneratorType.NORMAL_RND);
        this.cauthyRndFactory = provider.get(RandomGeneratorType.CAUTHY_RND);
    }

    @Override
    public boolean acceptsParameter(double alpha) {
        return VoigtRnd.LOWER_LIMIT_ALPHA <= alpha
                && alpha <= VoigtRnd.UPPER_LIMIT_ALPHA;
    }

    @Override
    public VoigtRnd instanceOf(double alpha) {
        if (!this.acceptsParameter(alpha)) {
            throw new IllegalArgumentException(String.format("alphaが不正:%s", alpha));
        }
        return new StandardImplVoigtRnd(alpha, this.normalRndFactory, this.cauthyRndFactory);
    }

    @Override
    public String toString() {
        return "VoigtRnd.Factory";
    }
}
