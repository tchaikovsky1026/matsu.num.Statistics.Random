/*
 * 2023.3.22
 */
package matsu.num.statistics.random.weibull.gumbel;

import java.util.Random;

import matsu.num.commons.Exponentiation;
import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.WeibullRnd;

/**
 * 標準Gumbel分布を用いた標準Weibull分布乱数生成器の実装.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public final class ByGumWeiRnd implements WeibullRnd {

    /**
     * 形状パラメータの下限.
     */
    private static final double LOWER_LIMIT_OF_SHAPE_PARAMETER = 1E-2;

    /**
     * 形状パラメータの上限.
     */
    private static final double UPPER_LIMIT_OF_SHAPE_PARAMETER = 1E14;

    private final GumbelRnd gumRnd = GumbelRnd.instance();
    private final double k;
    private final double invK;

    private ByGumWeiRnd(double k) {
        if (!(LOWER_LIMIT_OF_SHAPE_PARAMETER <= k && k <= UPPER_LIMIT_OF_SHAPE_PARAMETER)) {
            throw new IllegalArgumentException(String.format(
                    "パラメータ不正:k=%.16G", k));
        }
        this.k = k;
        this.invK = 1 / k;
    }

    @Override
    public double shapeParameter() {
        return this.k;
    }

    @Override
    public double nextRandom(Random random) {
        return Exponentiation.exp(-this.gumRnd.nextRandom(random) * this.invK);
    }

    /**
     * 指定した形状パラメータのWeibull分布乱数発生器インスタンスを返す.
     *
     * @param k 形状パラメータ
     * @return 形状パラメータがkのWeibull分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    public static WeibullRnd instanceOf(double k) {
        return new ByGumWeiRnd(k);
    }

}
