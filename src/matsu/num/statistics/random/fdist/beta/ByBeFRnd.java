/**
 * 2023.3.22
 */
package matsu.num.statistics.random.fdist.beta;

import java.util.Random;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.FDistributionRnd;

/**
 * ベータプライム乱数器を利用した, F分布に従う乱数発生器.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public final class ByBeFRnd implements FDistributionRnd {

    private final BetaRnd betaRnd;
    private final double D2OverD1;

    /**
     * @throws IllegalArgumentException パラメータ異常でガンマ分布の生成に失敗
     */
    private ByBeFRnd(double d1, double d2) {
        this.betaRnd = BetaRnd.instanceOf(d1 * 0.5, d2 * 0.5);
        this.D2OverD1 = d2 / d1;
    }

    @Override
    public final double numeratorDegreesOfFreedom() {
        return this.betaRnd.shapeA() * 2;
    }

    @Override
    public final double denominatorDegreesOfFreedom() {
        return this.betaRnd.shapeB() * 2;
    }

    @Override
    public final double nextRandom(Random random) {
        double out = this.D2OverD1 * this.betaRnd.nextBetaPrime(random);
        return Double.isFinite(out) ? out : 0;
    }

    /**
     * 指定した自由度のF分布乱数発生器インスタンスを返す. 
     *
     * @param d1 分子自由度
     * @param d2 分母自由度
     * @return 自由度がd1,d2のF分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static FDistributionRnd instanceOf(double d1, double d2) {
        return new ByBeFRnd(d1, d2);
    }

}
