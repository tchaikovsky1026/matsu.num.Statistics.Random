/**
 * 2024.1.14
 */
package matsu.num.statistics.random.service.weibull;

import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.WeibullRnd;
import matsu.num.statistics.random.service.Exponentiation;

/**
 * 標準Gumbel分布を用いた標準Weibull分布乱数生成器の実装.
 *
 * @author Matsuura Y.
 * @version 18.0
 */
final class GumbelBasedWeibullRnd implements WeibullRnd {

    private final double k;
    private final double invK;

    private final Exponentiation exponentiation;
    private final GumbelRnd gumRnd;

    GumbelBasedWeibullRnd(double k,
            Exponentiation exponentiation, GumbelRnd.Factory gumbelRndFactory) {
        super();
        this.k = k;
        this.invK = 1 / k;
        this.exponentiation = exponentiation;
        this.gumRnd = gumbelRndFactory.instance();
    }

    @Override
    public double shapeParameter() {
        return this.k;
    }

    @Override
    public double nextRandom(BaseRandom random) {
        return exponentiation.exp(-this.gumRnd.nextRandom(random) * this.invK);
    }

    @Override
    public String toString() {
        return String.format(
                "WeibullRnd(%s)", this.shapeParameter());
    }
}
