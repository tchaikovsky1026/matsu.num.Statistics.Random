/**
 * 2023.3.22
 */
package matsu.num.statistics.random.chisq.gamma;

import java.util.Random;

import matsu.num.statistics.random.ChiSquaredRnd;
import matsu.num.statistics.random.GammaRnd;

/**
 * ガンマ分布乱数器を利用した, カイ二乗分布に従う乱数発生器. 
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public final class ByGaChiSqRnd implements ChiSquaredRnd {

    private final GammaRnd gammaRnd;

    /**
     * @throws IllegalArgumentException パラメータ異常でガンマ分布の生成に失敗
     */
    private ByGaChiSqRnd(double k) {
        this.gammaRnd = GammaRnd.instanceOf(k * 0.5);
    }

    @Override
    public final double degreesOfFreedom() {
        return gammaRnd.shapeParameter() * 2;
    }

    @Override
    public final double nextRandom(Random random) {
        return gammaRnd.nextRandom(random) * 2;
    }

    /**
     * 指定した自由度のカイ二乗分布乱数発生器インスタンスを返す. 
     *
     * @param k 自由度
     * @return 自由度がkのカイ二乗分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static ChiSquaredRnd instanceOf(double k) {
        return new ByGaChiSqRnd(k);
    }

}
