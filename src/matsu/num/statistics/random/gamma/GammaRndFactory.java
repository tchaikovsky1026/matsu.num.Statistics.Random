/**
 * 2024.1.8
 */
package matsu.num.statistics.random.gamma;

import matsu.num.statistics.random.GammaRnd;

/**
 * このパッケージに用意された {@linkplain GammaRnd} 実装のインスタンス生成を扱う. <br>
 * 扱える形状パラメータkは, {@code 1.0E-2 <= k <= 1.0E+28} である.
 *
 * @author Matsuura Y.
 * @version 17.4
 */
public final class GammaRndFactory {

    static final double LOWER_LIMIT_OF_SHAPE_PARAMETER = 1E-2;
    static final double UPPER_LIMIT_OF_SHAPE_PARAMETER = 1E28;

    private static final GammaRnd GAMMA_RND_AT_1 = new ExpBasedGammaRndAt1();

    private GammaRndFactory() {
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
            throw new IllegalArgumentException(
                    String.format(
                            "パラメータ不正:k=%s", k));
        }

        //MarsagliaTsangに基づく乱数生成器
        if (k < 1) {
            return new MTTypeGammaRndUnder1(k);
        }
        if (k == 1) {
            return GAMMA_RND_AT_1;
        }

        return new MTTypeGammaRndOver1(k);
    }
}
