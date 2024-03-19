package matsu.num.statistics.random.weibull;

import org.junit.Ignore;

import matsu.num.statistics.random.WeibullRnd;
import matsu.num.statistics.random.gumbel.GumbelFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

@Ignore
public final class WeibullFactoryForTesting {

    public static final WeibullRnd.Factory FACTORY =
            new GumbelBasedWeibullRndFactory(
                    ExponentiationForTesting.INSTANCE, GumbelFactoryForTesting.FACTORY);

    private WeibullFactoryForTesting() {
    }
}
