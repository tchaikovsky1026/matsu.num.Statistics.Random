/**
 * 2024.2.23
 */
package matsu.num.statistics.random.service.fdist;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.FDistributionRnd;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * このパッケージに用意された {@link FDistributionRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class BetaBasedFDistributionRndFactory implements FDistributionRnd.Factory {

    private final BetaRnd.Factory betaRndFactory;

    public BetaBasedFDistributionRndFactory(RandomGeneratorFactoryProvider provider) {
        super();
        this.betaRndFactory = provider.get(RandomGeneratorType.BETA_RND);
    }

    @Override
    public boolean acceptsParameters(double d1, double d2) {
        return FDistributionRnd.LOWER_LIMIT_DEGREES_OF_FREEDOM <= d1
                && d1 <= FDistributionRnd.UPPER_LIMIT_DEGREES_OF_FREEDOM
                && FDistributionRnd.LOWER_LIMIT_DEGREES_OF_FREEDOM <= d2
                && d2 <= FDistributionRnd.UPPER_LIMIT_DEGREES_OF_FREEDOM;
    }

    @Override
    public FDistributionRnd instanceOf(double d1, double d2) {
        if (!this.acceptsParameters(d1, d2)) {
            throw new IllegalArgumentException(
                    String.format(
                            "パラメータ不正:d1 = %s, d2 = %s", d1, d2));
        }
        return new BetaBasedFDistributionRnd(d1, d2, this.betaRndFactory);
    }

    @Override
    public String toString() {
        return "FDistRnd.Factory";
    }
}
