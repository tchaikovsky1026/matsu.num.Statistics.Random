/**
 * 2023.3.22
 */
package matsu.num.statistics.random.geo.inv;

import java.util.Random;

import matsu.num.commons.Exponentiation;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GeometricRnd;

/**
 * 逆関数法に基づく, 幾何分布に従う乱数発生器を扱う. 
 * 
 * @author Matsuura Y.
 * @version 11.0
 */
public final class InvBasedGeoRnd implements GeometricRnd {

    private static final double LOWER_LIMIT_OF_SUCCESS_PROBABILITY = 1E-7;

    private final double coeff;
    private final double p;

    private final ExponentialRnd expRnd = ExponentialRnd.instance();

    /**
     * @throws IllegalArgumentException パラメータ異常で幾何分布の生成に失敗.
     */
    private InvBasedGeoRnd(double p) {
        if (!(LOWER_LIMIT_OF_SUCCESS_PROBABILITY <= p && p <= 1)) {
            throw new IllegalArgumentException(String.format(
                    "パラメータ不正:p=%.16G", p));
        }
        this.p = p;
        this.coeff = -1 / Exponentiation.log1p(-p);
    }

    @Override
    public final double successPobability() {
        return p;
    }

    @Override
    public int nextRandom(Random random) {
        while (true) {
            double out = coeff * this.expRnd.nextRandom(random);
            if (out < Integer.MAX_VALUE) {
                return 1 + (int) out;
            }
        }
    }

    /**
     * 指定した成功確率の幾何分布乱数発生器インスタンスを返す.
     *
     * @param p 成功確率
     * @return 成功確率がpの幾何分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static GeometricRnd instanceOf(double p) {
        return new InvBasedGeoRnd(p);
    }
}
