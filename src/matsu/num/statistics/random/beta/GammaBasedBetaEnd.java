/**
 * 2024.1.8
 */
package matsu.num.statistics.random.beta;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.gamma.GammaRndFactory;

/**
 * ガンマ分布乱数器を利用した, ベータ分布に従う乱数発生器.
 *
 * @author Matsuura Y.
 * @version 17.4
 */
final class GammaBasedBetaEnd implements BetaRnd {

    /**
     * 形状パラメータの下限.
     */
    private static final double LOWER_LIMIT_OF_SHAPE_PARAMETER = 1E-2;

    /**
     * 形状パラメータの上限.
     */
    private static final double UPPER_LIMIT_OF_SHAPE_PARAMETER = 1E14;

    private final GammaRnd gammaRndA;
    private final GammaRnd gammaRndB;

    /**
     * 指定した形状パラメータのベータ分布乱数発生器インスタンスを返す. <br>
     * 形状パラメータ <i>a</i>,<i>b</i> は,
     * {@code 1.0E-2 <= a <= 1.0E+14 && 1.0E-2 <= b <= 1.0E+14} でなければならない.
     *
     * @param a 形状パラメータa
     * @param b 形状パラメータb
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    GammaBasedBetaEnd(double a, double b) {
        super();
        
        if (!(LOWER_LIMIT_OF_SHAPE_PARAMETER <= a && a <= UPPER_LIMIT_OF_SHAPE_PARAMETER
                && LOWER_LIMIT_OF_SHAPE_PARAMETER <= b && b <= UPPER_LIMIT_OF_SHAPE_PARAMETER)) {
            throw new IllegalArgumentException(
                    String.format(
                            "パラメータ不正:a = %s, b = %s", a, b));
        }
        this.gammaRndA = GammaRndFactory.instanceOf(a);
        this.gammaRndB = GammaRndFactory.instanceOf(b);
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
    public final double nextRandom(Random random) {
        double u1 = this.gammaRndA.nextRandom(random);
        double u2 = this.gammaRndB.nextRandom(random);
        double out = u1 / (u1 + u2);
        return Double.isFinite(out) ? out : 0;
    }

    @Override
    public final double nextBetaPrime(Random random) {
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
