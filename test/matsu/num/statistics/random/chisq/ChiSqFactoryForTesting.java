package matsu.num.statistics.random.chisq;

import org.junit.Ignore;

import matsu.num.statistics.random.ChiSquaredRnd;
import matsu.num.statistics.random.gamma.GammaFactoryForTesting;

@Ignore
public final class ChiSqFactoryForTesting {

    public static final ChiSquaredRnd.Factory FACTORY =
            new GammaTypeChiSquaredRndFactory(GammaFactoryForTesting.FACTORY);

    private ChiSqFactoryForTesting() {
    }
}
