/**
 * 2024.1.13
 */
package matsu.num.statistics.random.fdist;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.FDistributionRnd;
import matsu.num.statistics.random.BaseRandom;

/**
 * ベータプライム乱数器を利用した, F分布に従う乱数発生器.
 *
 * @author Matsuura Y.
 * @version 18.0
 */
final class BetaBasedFDistributionRnd implements FDistributionRnd {

    private final BetaRnd betaRnd;
    private final double D2OverD1;

    private final double d1;
    private final double d2;

    BetaBasedFDistributionRnd(double d1, double d2, BetaRnd.Factory betaRndFactory) {
        this.betaRnd = betaRndFactory.instanceOf(d1 * 0.5, d2 * 0.5);
        this.D2OverD1 = d2 / d1;
        this.d1 = d1;
        this.d2 = d2;
    }

    @Override
    public final double numeratorDegreesOfFreedom() {
        return this.d1;
    }

    @Override
    public final double denominatorDegreesOfFreedom() {
        return this.d2;
    }

    @Override
    public final double nextRandom(BaseRandom random) {
        double out = this.D2OverD1 * this.betaRnd.nextBetaPrime(random);
        return Double.isFinite(out) ? out : 0;
    }

    @Override
    public String toString() {
        return String.format(
                "FDistRnd(%s, %s)", this.numeratorDegreesOfFreedom(), this.denominatorDegreesOfFreedom());
    }
}
