/**
 * 2024.1.8
 */
package matsu.num.statistics.random.fdist;

import matsu.num.statistics.random.FDistributionRnd;

/**
 * このパッケージに用意された {@linkplain FDistributionRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class FDistributionRndFactory {

    private FDistributionRndFactory() {
        //インスタンス化不可
        throw new AssertionError();
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
        return new BetaBasedFDistRnd(d1, d2);
    }
}
