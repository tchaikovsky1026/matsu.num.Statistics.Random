package matsu.num.statistics.random.cauchy;

import org.junit.Ignore;

import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

@Ignore
public final class CauchyFactoryForTesting {
    public static final CauchyRnd.Factory FACTORY =
            ZiggCauchyRnd.createFactory(ExponentiationForTesting.INSTANCE);

    private CauchyFactoryForTesting() {
    }
}
