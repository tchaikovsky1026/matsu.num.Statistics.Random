package matsu.num.statistics.random.levy;

import org.junit.Ignore;

import matsu.num.statistics.random.LevyRnd;
import matsu.num.statistics.random.norm.NormalFactoryForTesting;

@Ignore
public final class LevyFactoryForTesting {

    public static final LevyRnd.Factory FACTORY =
            NormalBasedLevyRnd.createFactory(NormalFactoryForTesting.FACTORY);

    private LevyFactoryForTesting() {
    }
}
