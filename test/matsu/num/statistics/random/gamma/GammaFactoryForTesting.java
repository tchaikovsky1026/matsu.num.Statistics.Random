package matsu.num.statistics.random.gamma;

import org.junit.Ignore;

import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;
import matsu.num.statistics.random.norm.NormalFactoryForTesting;

@Ignore
public final class GammaFactoryForTesting {

    public static final GammaRnd.Factory FACTORY =
            new MTTypeGammaRndFactory(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY, NormalFactoryForTesting.FACTORY);

    private GammaFactoryForTesting() {
    }
}