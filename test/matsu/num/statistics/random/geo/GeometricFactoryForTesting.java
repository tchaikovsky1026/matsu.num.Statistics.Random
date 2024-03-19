package matsu.num.statistics.random.geo;

import org.junit.Ignore;

import matsu.num.statistics.random.GeometricRnd;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;
import matsu.num.statistics.random.lib.ExponentiationForTesting;

@Ignore
public final class GeometricFactoryForTesting {

    public static final GeometricRnd.Factory FACTORY =
            new InversionBasedGeoRndFactory(
                    ExponentiationForTesting.INSTANCE, ExponentialFactoryForTesting.FACTORY);

    private GeometricFactoryForTesting() {
    }
}
