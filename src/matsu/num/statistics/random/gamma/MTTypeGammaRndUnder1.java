/**
 * 2024.1.9
 */
package matsu.num.statistics.random.gamma;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.common.Exponentiation;
import matsu.num.statistics.random.common.ExponentiationFactory;
import matsu.num.statistics.random.exp.ExponentialRndFactory;

/**
 * Marsaglia-Tsangの方法に基づく, 形状パラメータが1より小さいときの乱数生成器の実装. <br>
 * X～Gamma(k+1),Y～Beta(k+1,1)のとき(XY)～Gamma(k)となることを利用する.
 * 
 * @author Matsuura Y.
 * @version 17.5
 */
final class MTTypeGammaRndUnder1 implements GammaRnd {

    private final double k;

    //Gamma(k+1)乱数を発生させるためのインスタンス
    private final GammaRnd gammaKPlus1;
    private final double invK;

    private final ExponentialRnd expRnd = ExponentialRndFactory.instance();

    private final Exponentiation exponentiation = ExponentiationFactory.instance();

    /**
     * @param k 0.01<=k<=1
     */
    MTTypeGammaRndUnder1(double k) {
        if (!(GammaRndFactory.LOWER_LIMIT_OF_SHAPE_PARAMETER <= k && k <= 1)) {
            throw new AssertionError("Bug:0.01<=k<=1でない");
        }
        this.k = k;
        this.gammaKPlus1 = new MTTypeGammaRndOver1(k + 1);
        this.invK = 1 / k;
    }

    @Override
    public double shapeParameter() {
        return this.k;
    }

    @Override
    public double nextRandom(Random random) {
        /*
         * Gamma(k+1)乱数とBeta(1,k)乱数からGamma(k)を発生させる.
         * X～Gamma(a), Y～Beta(b,a-b)のとき, Z=XY～Gamma(b)である.
         * よって, a=k+1,b=kとすることで,
         * Z = XY = Random[Gamma(k+1)]*Random[Beta(k,1)]
         * からRandom[Gamma(k)]が得られる.
         * 
         * Beta(k,1)は指数k-1のべき分布であり,
         * 累積分布関数が y^k で書けるので逆関数法が使える.
         */
        return this.gammaKPlus1.nextRandom(random) * exponentiation.exp(
                -this.expRnd.nextRandom(random) * this.invK);
    }

    @Override
    public String toString() {
        return GammaRndToString.toString(this);
    }
}
