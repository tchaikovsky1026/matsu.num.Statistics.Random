/**
 * 2023.3.22
 */
package matsu.num.statistics.random.gamma.mt;

import java.util.Random;

import matsu.num.commons.Exponentiation;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GammaRnd;

/**
 * Marsaglia-Tsangの方法に基づく, 形状パラメータが1より小さいときの乱数生成器の実装. <br>
 * X～Gamma(k+1),Y～Beta(k+1,1)のとき(XY)～Gamma(k)となることを利用する.
 * 
 * @author Matsuura Y.
 * @version 11.0
 */
final class GaRndAtUnder1 implements GammaRnd {

    private final double k;

    //Gamma(k+1)乱数を発生させるためのdとc
    private final GammaRnd gammaKPlus1;

    private final ExponentialRnd expRnd = ExponentialRnd.instance();

    private GaRndAtUnder1(double k) {
        if (!(MarsagliaTsangGaRndFactory.LOWER_LIMIT_OF_SHAPE_PARAMETER <= k && k <= 1)) {
            throw new AssertionError("Bug:0.01<=k<=1でない");
        }
        this.k = k;
        this.gammaKPlus1 = GaRndAtOver1.instanceOf(k + 1);
    }

    @Override
    public double shapeParameter() {
        return this.k;
    }

    @Override
    public double nextRandom(Random random) {
        /*
         Gamma(k+1)乱数とBeta(1,k)乱数からGamma(k)を発生させる.
         X～Gamma(a), Y～Beta(b,a-b)のとき, Z=XY～Gamma(b)である. 
         よって, a=k+1,b=kとすることで, 
         Z = XY = Random[Gamma(k+1)]*Random[Beta(k,1)]
         からRandom[Gamma(k)]が得られる. 
        
         Beta(k,1)は指数k-1のべき分布であり, 
         累積分布関数が y^k で書けるので逆関数法が使える.
         */
        return this.gammaKPlus1.nextRandom(random) * Exponentiation.exp(-expRnd.nextRandom(random) / k);
    }

    /**
     * 
     * @param k 0.01<=k<=1
     * @return インスタンス
     */
    public static GammaRnd instanceOf(double k) {
        return new GaRndAtUnder1(k);
    }

}
