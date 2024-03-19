package matsu.num.statistics.random.cat;

import org.junit.Ignore;

import matsu.num.statistics.random.CategoricalRnd;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

@Ignore
public final class CategoricalFactoryForTesting {

    public static final CategoricalRnd.Factory FACTORY =
            new TableBasedCategoricalRndFactory(ExponentiationForTesting.INSTANCE);

    private CategoricalFactoryForTesting() {
    }
}
