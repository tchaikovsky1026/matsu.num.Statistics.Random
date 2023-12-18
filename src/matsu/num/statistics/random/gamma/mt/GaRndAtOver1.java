/**
 * 2023.3.22
 */
package matsu.num.statistics.random.gamma.mt;

import java.util.Random;

import matsu.num.commons.Exponentiation;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.NormalRnd;

/**
 * Marsaglia-Tsangの方法に基づく, 形状パラメータが1より大きいときの乱数生成器の実装.
 * 
 * @author Matsuura Y.
 * @version 11.0
 */
final class GaRndAtOver1 implements GammaRnd {

    private final double k;

    //Gamma(k)乱数を発生させるためのdとc
    private final double d;
    private final double c;
    private final NormalRnd normalRnd = NormalRnd.instance();

    private GaRndAtOver1(double k) {
        if (!(1 <= k && k <= MarsagliaTsangGaRndFactory.UPPER_LIMIT_OF_SHAPE_PARAMETER)) {
            throw new AssertionError("Bug:1<=k<=1E28でない");
        }
        this.k = k;
        this.d = k - (1.0 / 3.0);
        this.c = (1.0 / 3.0) / Exponentiation.sqrt(d);
    }

    @Override
    public double shapeParameter() {
        return this.k;
    }

    @Override
    public double nextRandom(Random random) {
        /*
          Marsaglia-Tsangの方法により, {@literal k > 1}のGamma乱数を生成する. 
          ただし, d = k - 1/3, c = 1/sqrt(9d)
         */
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
            if (u > z2 * z2 * 0.0331 || z2 * 0.5 + d * Exponentiation.log(w) + d - y > Exponentiation.log(1 - u)) {
                return y;
            }
        }
    }

    /**
     * 
     * @param k 1<=k<=1E28
     * @return インスタンス
     */
    public static GammaRnd instanceOf(double k) {
        return new GaRndAtOver1(k);
    }

}
