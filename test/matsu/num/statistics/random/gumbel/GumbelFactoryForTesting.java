package matsu.num.statistics.random.gumbel;

import org.junit.Ignore;

import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

@Ignore
public final class GumbelFactoryForTesting {

    public static final GumbelRnd.Factory FACTORY =
            new UniZiggGumbelRndFactory(
                    ExponentiationForTesting.INSTANCE,
                    ExponentialFactoryForTesting.FACTORY);

    private GumbelFactoryForTesting() {
    }
}
