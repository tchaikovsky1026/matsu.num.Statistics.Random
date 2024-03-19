package matsu.num.statistics.random.voigt;

import org.junit.Ignore;

import matsu.num.statistics.random.VoigtRnd;
import matsu.num.statistics.random.cauchy.CauchyFactoryForTesting;
import matsu.num.statistics.random.norm.NormalFactoryForTesting;

@Ignore
public final class VoigtFactoryForTesting {

    public static final VoigtRnd.Factory FACTORY =
            new StandardImplVoigtRndFactory(
                    NormalFactoryForTesting.FACTORY, CauchyFactoryForTesting.FACTORY);

    private VoigtFactoryForTesting() {
    }
}
