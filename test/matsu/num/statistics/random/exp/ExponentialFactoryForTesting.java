package matsu.num.statistics.random.exp;

import org.junit.Ignore;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

@Ignore
public final class ExponentialFactoryForTesting {

    public static final ExponentialRnd.Factory FACTORY =
            new ZiggExponentialRndFactory(ExponentiationForTesting.INSTANCE);

    private ExponentialFactoryForTesting() {
    }
}
