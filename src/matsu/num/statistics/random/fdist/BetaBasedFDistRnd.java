/**
 * 2024.1.8
 */
package matsu.num.statistics.random.fdist;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.FDistributionRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.beta.BetaRndFactory;

/**
 * ベータプライム乱数器を利用した, F分布に従う乱数発生器.
 * 
 * <p>
 * 扱える自由度 <i>d</i><sub>1</sub>, <i>d</i><sub>2</sub> は,
 * {@code 2.0E-2 <= (d1,d2) <= 2.0E+14} である.
 * </p>
 *
 * @author Matsuura Y.
 * @version 17.4
 */
final class BetaBasedFDistRnd implements FDistributionRnd {

    private final BetaRnd betaRnd;
    private final double D2OverD1;

    private final double d1;
    private final double d2;

    /**
     * 指定した自由度のF分布乱数発生器インスタンスを構築する.
     *
     * @param d1 分子自由度
     * @param d2 分母自由度
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    BetaBasedFDistRnd(double d1, double d2) {
        this.betaRnd = BetaRndFactory.instanceOf(d1 * 0.5, d2 * 0.5);
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
    public final double nextRandom(Random random) {
        double out = this.D2OverD1 * this.betaRnd.nextBetaPrime(random);
        return Double.isFinite(out) ? out : 0;
    }

    @Override
    public String toString() {
        return String.format(
                "FDistRnd(%s, %s)", this.numeratorDegreesOfFreedom(), this.denominatorDegreesOfFreedom());
    }
}
