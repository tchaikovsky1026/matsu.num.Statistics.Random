/**
 * 2024.1.9
 */
package matsu.num.statistics.random.geo;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GeometricRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.common.Exponentiation;
import matsu.num.statistics.random.common.ExponentiationFactory;
import matsu.num.statistics.random.exp.ExponentialRndFactory;

/**
 * 逆関数法に基づく, 幾何分布に従う乱数発生器を扱う.
 * 
 * <p>
 * 扱える成功確率 <i>p</i> は, {@code 1.0E-7 <= p <= 1.0} である.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 17.5
 */
final class InversionBasedGeoRnd implements GeometricRnd {

    private static final double LOWER_LIMIT_OF_SUCCESS_PROBABILITY = 1E-7;

    private final double coeff;
    private final double p;

    private final ExponentialRnd expRnd = ExponentialRndFactory.instance();

    private final Exponentiation exponentiation = ExponentiationFactory.instance();

    /**
     * @throws IllegalArgumentException パラメータ異常で幾何分布の生成に失敗.
     */
    InversionBasedGeoRnd(double p) {
        if (!(LOWER_LIMIT_OF_SUCCESS_PROBABILITY <= p && p <= 1d)) {
            throw new IllegalArgumentException(
                    String.format(
                            "パラメータ不正:p=%s", p));
        }
        this.p = p;
        this.coeff = -1 / exponentiation.log1p(-p);
    }

    @Override
    public final double successPobability() {
        return p;
    }

    @Override
    public int nextRandom(Random random) {
        while (true) {
            double out = coeff * this.expRnd.nextRandom(random);
            if (out < Integer.MAX_VALUE) {
                return 1 + (int) out;
            }
        }
    }

    @Override
    public String toString() {
        return String.format(
                "GeometricRnd(%s)", this.successPobability());
    }
}
