/**
 * 2024.1.9
 */
package matsu.num.statistics.random.weibull;

import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.WeibullRnd;
import matsu.num.statistics.random.common.Exponentiation;
import matsu.num.statistics.random.common.ExponentiationFactory;
import matsu.num.statistics.random.gumbel.GumbelRndFactory;

/**
 * 標準Gumbel分布を用いた標準Weibull分布乱数生成器の実装.
 * 
 * <p>
 * 扱える形状パラメータ <i>k</i> は, {@code 1.0E-2 <= k <= 1.0E+14} である.
 * </p>
 *
 * @author Matsuura Y.
 * @version 17.5
 */
final class GumbelBasedWeibullRnd implements WeibullRnd {

    /**
     * 形状パラメータの下限.
     */
    private static final double LOWER_LIMIT_OF_SHAPE_PARAMETER = 1E-2;

    /**
     * 形状パラメータの上限.
     */
    private static final double UPPER_LIMIT_OF_SHAPE_PARAMETER = 1E14;

    private final GumbelRnd gumRnd = GumbelRndFactory.instance();
    private final double k;
    private final double invK;

    private final Exponentiation exponentiation = ExponentiationFactory.instance();

    GumbelBasedWeibullRnd(double k) {
        if (!(LOWER_LIMIT_OF_SHAPE_PARAMETER <= k && k <= UPPER_LIMIT_OF_SHAPE_PARAMETER)) {
            throw new IllegalArgumentException(
                    String.format(
                            "パラメータ不正:k=%s", k));
        }
        this.k = k;
        this.invK = 1 / k;
    }

    @Override
    public double shapeParameter() {
        return this.k;
    }

    @Override
    public double nextRandom(Random random) {
        return exponentiation.exp(-this.gumRnd.nextRandom(random) * this.invK);
    }

    @Override
    public String toString() {
        return String.format(
                "WeibullRnd(%s)", this.shapeParameter());
    }
}
