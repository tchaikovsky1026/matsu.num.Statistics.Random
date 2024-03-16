/**
 * 2024.2.22
 */
package matsu.num.statistics.random.service.beta;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * ガンマ乱数生成器に基づくベータ乱数生成器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class GammaBasedBetaRndFactory implements BetaRnd.Factory {

    private final GammaRnd.Factory gammaRndFactory;

    public GammaBasedBetaRndFactory(RandomGeneratorFactoryProvider provider) {
        this.gammaRndFactory = provider.get(RandomGeneratorType.GAMMA_RND);
    }

    @Override
    public boolean acceptsParameters(double a, double b) {
        return BetaRnd.LOWER_LIMIT_SHAPE_PARAMETER <= a && a <= BetaRnd.UPPER_LIMIT_SHAPE_PARAMETER
                && BetaRnd.LOWER_LIMIT_SHAPE_PARAMETER <= b && b <= BetaRnd.UPPER_LIMIT_SHAPE_PARAMETER;
    }

    @Override
    public BetaRnd instanceOf(double a, double b) {
        if (!this.acceptsParameters(a, b)) {
            throw new IllegalArgumentException(
                    String.format("パラメータ不正:a=%s, b=%s", a, b));
        }
        return new GammaBasedBetaRnd(a, b, this.gammaRndFactory);
    }

    @Override
    public String toString() {
        return "BetaRnd.Factory";
    }
}
