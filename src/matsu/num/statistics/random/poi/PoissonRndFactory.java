/**
 * 2024.1.9
 */
package matsu.num.statistics.random.poi;

import matsu.num.statistics.random.PoissonRnd;

/**
 * このパッケージが扱う {@linkplain PoissonRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class PoissonRndFactory {

    private PoissonRndFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * <p>
     * 指定したパラメータのPoisson分布乱数発生器インスタンスを返す.
     * </p>
     * 
     * <p>
     * 扱えるパラメータ <i>&lambda;</i> は, <br>
     * {@code 0.0 <= lambda <= 1.0E6} <br>
     * である.
     * </p>
     *
     * @param lambda パラメータ
     * @return パラメータが <i>&lambda;</i> のPoisson分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static PoissonRnd instanceOf(double lambda) {
        return new GammaHomoProcessBasedPoissonRnd(lambda);
    }
}
