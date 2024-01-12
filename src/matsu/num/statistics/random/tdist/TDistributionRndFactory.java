/**
 * 2024.1.9
 */
package matsu.num.statistics.random.tdist;

import matsu.num.statistics.random.TDistributionRnd;

/**
 * このパッケージが扱う {@linkplain TDistributionRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class TDistributionRndFactory {

    private TDistributionRndFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * <p>
     * 指定した自由度のStudent-t分布乱数発生器インスタンスを返す.
     * </p>
     * 
     * <p>
     * 扱える自由度 <i>&nu;</i> は, <br>
     * {@code 2.0E-2 <= nu <= 2.0E+28} <br>
     * である.
     * </p>
     *
     * @param nu 自由度
     * @return 自由度が <i>&nu;</i> のStudent-t分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static TDistributionRnd instanceOf(double nu) {
        return new NormalGammaBasedTDistRnd(nu);
    }
}
