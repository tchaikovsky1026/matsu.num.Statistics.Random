package matsu.num.statistics.random.norm;

import org.junit.Ignore;

import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

@Ignore
public final class NormalFactoryForTesting {

    public static final NormalRnd.Factory FACTORY =
            ZiggNormalRnd.createFactory(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY);

    private NormalFactoryForTesting() {
    }
}
