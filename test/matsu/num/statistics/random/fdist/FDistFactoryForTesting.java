package matsu.num.statistics.random.fdist;

import org.junit.Ignore;

import matsu.num.statistics.random.FDistributionRnd;
import matsu.num.statistics.random.beta.BetaFactoryForTesting;

@Ignore
public final class FDistFactoryForTesting {

    public static final FDistributionRnd.Factory FACTORY =
            new BetaBasedFDistributionRndFactory(BetaFactoryForTesting.FACTORY);

    private FDistFactoryForTesting() {
    }
}
