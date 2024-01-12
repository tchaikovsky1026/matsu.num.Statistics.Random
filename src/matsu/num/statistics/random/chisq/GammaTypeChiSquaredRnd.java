/**
 * 2024.1.8
 */
package matsu.num.statistics.random.chisq;

import matsu.num.statistics.random.ChiSquaredRnd;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.gamma.GammaRndFactory;

/**
 * ガンマ分布乱数器を利用した, カイ二乗分布に従う乱数発生器.
 * 
 * <p>
 * 自由度kのカイ二乗分布は, 形状パラメータk/2, スケールパラメータ2のガンマ分布である.
 * </p>
 *
 * @author Matsuura Y.
 * @version 17.4
 */
final class GammaTypeChiSquaredRnd implements ChiSquaredRnd {

    private final double k;

    private final GammaRnd gammaRnd;

    /**
     * <p>
     * 扱える自由度 <i>k</i> は, <br>
     * {@code 2.0E-2 <= k <= 2.0E+28} <br>
     * である.
     * </p>
     * 
     * @param k 自由度
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    GammaTypeChiSquaredRnd(double k) {
        super();
        
        this.gammaRnd = GammaRndFactory.instanceOf(k * 0.5);
        this.k = k;
    }

    @Override
    public final double degreesOfFreedom() {
        return this.k;
    }

    @Override
    public final double nextRandom(Random random) {
        return this.gammaRnd.nextRandom(random) * 2;
    }

    @Override
    public String toString() {
        return String.format(
                "ChiSquaredRnd(%s)", this.degreesOfFreedom());
    }

}
