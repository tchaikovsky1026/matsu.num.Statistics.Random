/**
 * 2024.1.9
 */
package matsu.num.statistics.random.gamma;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.common.Exponentiation;
import matsu.num.statistics.random.common.ExponentiationFactory;
import matsu.num.statistics.random.exp.ExponentialRndFactory;
import matsu.num.statistics.random.norm.NormalRndFactory;

/**
 * Marsaglia-Tsangの方法に基づく, ガンマ乱数のstaticな生成器. <br>
 * 扱える形状パラメータkは, {@code 1.0E-2 <= k <= 1.0E+28} である.
 * 
 * @author Matsuura Y.
 * @version 17.5
 */
final class MTTypeStaticGammaRnd implements StaticGammaRnd {

    private static final double LOWER_LIMIT_OF_SHAPE_PARAMETER = 1E-2;
    private static final double UPPER_LIMIT_OF_SHAPE_PARAMETER = 1E28;

    private final ExponentialRnd expRnd = ExponentialRndFactory.instance();
    private final NormalRnd normalRnd = NormalRndFactory.instance();
    
    private final Exponentiation exponentiation = ExponentiationFactory.instance();

    MTTypeStaticGammaRnd() {
        super();
    }

    @Override
    public double nextRandom(Random random, double k) {
        if (!(LOWER_LIMIT_OF_SHAPE_PARAMETER <= k && k <= UPPER_LIMIT_OF_SHAPE_PARAMETER)) {
            throw new IllegalArgumentException(
                    String.format(
                            "パラメータ不正:k=%s", k));
        }

        if (k == 1) {
            return this.expRnd.nextRandom(random);
        }
        if (k > 1) {
            double d = k - (1.0 / 3.0);
            double c = (1.0 / 3.0) / exponentiation.sqrt(d);
            return nextGammaOver1(random, d, c);
        }

        //k<1
        double d = k + (2.0 / 3.0);
        double c = (1.0 / 3.0) / exponentiation.sqrt(d);
        return nextGammaOver1(random, d, c) * exponentiation.exp(-this.expRnd.nextRandom(random) / k);
    }

    private double nextGammaOver1(Random random, double d, double c) {
        while (true) {
            double z = this.normalRnd.nextRandom(random);
            double v = 1 + c * z;
            double w = v * v * v;
            double y = d * w;
            if (y < 0.0) {
                continue;
            }
            double u = random.nextDouble();
            double z2 = z * z;
            if (u > z2 * z2 * 0.0331 || z2 * 0.5 + d * exponentiation.log(w) + d - y > exponentiation.log(1 - u)) {
                return y;
            }
        }
    }
    
    @Override
    public String toString() {
        return "StaticGammaRnd";
    }
}
