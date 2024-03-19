package matsu.num.statistics.random.beta;

import org.junit.Ignore;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;

@Ignore
public final class BetaFactoryForTesting {

    public static final BetaRnd.Factory FACTORY =
            new GammaBasedBetaRndFactory(GammaFactoryForTesting.FACTORY);

    private BetaFactoryForTesting() {
    }

}
