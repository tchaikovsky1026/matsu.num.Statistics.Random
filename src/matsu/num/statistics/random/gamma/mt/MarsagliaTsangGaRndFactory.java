/**
 * 2023.3.22
 */
package matsu.num.statistics.random.gamma.mt;

import matsu.num.statistics.random.GammaRnd;

/**
 * Marsaglia-Tsangの方法に基づく, 標準ガンマ乱数発生器のファクトリ. <br>
 * 扱える形状パラメータkは, {@code 1.0E-2 <= k <= 1.0E+28} である.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public final class MarsagliaTsangGaRndFactory {

    static final double LOWER_LIMIT_OF_SHAPE_PARAMETER = 1E-2;
    static final double UPPER_LIMIT_OF_SHAPE_PARAMETER = 1E28;

    private MarsagliaTsangGaRndFactory() {
        throw new AssertionError();
    }

    /**
     * 指定した形状パラメータのガンマ分布乱数発生器インスタンスを返す.
     *
     * @param k 形状パラメータ
     * @return 形状パラメータがkのガンマ分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static GammaRnd instanceOf(double k) {
        if (!(LOWER_LIMIT_OF_SHAPE_PARAMETER <= k && k <= UPPER_LIMIT_OF_SHAPE_PARAMETER)) {
            throw new IllegalArgumentException(String.format(
                    "パラメータ不正:k=%.16G", k));
        }
        if (k < 1) {
            return GaRndAtUnder1.instanceOf(k);
        }
        if (k == 1) {
            return GaRndAt1.instance();
        }

        return GaRndAtOver1.instanceOf(k);
    }
}
