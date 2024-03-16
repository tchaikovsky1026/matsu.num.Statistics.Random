/**
 * 2024.1.12
 */
package matsu.num.statistics.random.service.beta;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.BaseRandom;

/**
 * ガンマ分布乱数器を利用した, ベータ分布に従う乱数発生器.
 *
 * @author Matsuura Y.
 * @version 18.0
 */
final class GammaBasedBetaRnd implements BetaRnd {

    private final GammaRnd gammaRndA;
    private final GammaRnd gammaRndB;

    /**
     * 指定した形状パラメータのベータ分布乱数発生器インスタンスを返す. <br>
     * 形状パラメータ <i>a</i>,<i>b</i> は,
     * {@code 1.0E-2 <= a <= 1.0E+14 && 1.0E-2 <= b <= 1.0E+14} でなければならない.
     *
     * @param a 形状パラメータa
     * @param b 形状パラメータb
     */
    GammaBasedBetaRnd(double a, double b, GammaRnd.Factory gammaRndFactory) {
        super();

        this.gammaRndA = gammaRndFactory.instanceOf(a);
        this.gammaRndB = gammaRndFactory.instanceOf(b);
    }

    @Override
    public final double shapeA() {
        return this.gammaRndA.shapeParameter();
    }

    @Override
    public final double shapeB() {
        return this.gammaRndB.shapeParameter();
    }

    @Override
    public final double nextRandom(BaseRandom random) {
        double u1 = this.gammaRndA.nextRandom(random);
        double u2 = this.gammaRndB.nextRandom(random);
        double out = u1 / (u1 + u2);
        return Double.isFinite(out) ? out : 0;
    }

    @Override
    public final double nextBetaPrime(BaseRandom random) {
        double u1 = this.gammaRndA.nextRandom(random);
        double u2 = this.gammaRndB.nextRandom(random);
        double out = u1 / u2;
        return Double.isFinite(out) ? out : 0;
    }

    @Override
    public String toString() {
        return String.format(
                "BetaRnd(%s, %s)", this.shapeA(), this.shapeB());
    }

}