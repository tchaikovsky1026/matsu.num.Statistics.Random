package matsu.num.statistics.random.staticbeta;

import org.junit.Ignore;

import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.staticgamma.StaticGammaFactoryForTesting;

@Ignore
public final class StaticBetaFactoryForTesting {

    public static final StaticBetaRnd.Factory FACTORY =
            GammaBasedStaticBetaRnd.createFactory(StaticGammaFactoryForTesting.FACTORY);

    private StaticBetaFactoryForTesting() {
    }
}
