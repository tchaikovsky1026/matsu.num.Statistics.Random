package matsu.num.statistics.random.logi;

import org.junit.Ignore;

import matsu.num.statistics.random.LogisticRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

@Ignore
public final class LogisticFactoryForTesting {

    public static final LogisticRnd.Factory FACTORY =
            ZiggLogiRnd.createFactory(
                    ExponentiationForTesting.INSTANCE, ExponentialFactoryForTesting.FACTORY);

    private LogisticFactoryForTesting() {
    }
}
