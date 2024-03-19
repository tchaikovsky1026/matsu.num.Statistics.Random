/**
 * 2024.3.16
 */
package matsu.num.statistics.random.chisq;

import java.util.Objects;

import matsu.num.statistics.random.ChiSquaredRnd;
import matsu.num.statistics.random.GammaRnd;

/**
 * このパッケージに用意された {@link ChiSquaredRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 19.0
 */
public final class GammaTypeChiSquaredRndFactory implements ChiSquaredRnd.Factory {

    private final GammaRnd.Factory gammaRndFactory;

    public GammaTypeChiSquaredRndFactory(GammaRnd.Factory gammaRndFactory) {
        this.gammaRndFactory = Objects.requireNonNull(gammaRndFactory);
    }

    @Override
    public boolean acceptsParameter(double k) {
        return ChiSquaredRnd.LOWER_LIMIT_DEGREES_OF_FREEDOM <= k
                && k <= ChiSquaredRnd.UPPER_LIMIT_DEGREES_OF_FREEDOM;
    }

    /**
     * 指定した自由度のカイ二乗分布乱数発生器インスタンスを返す.
     *
     * @param k 自由度
     * @return 自由度がkのカイ二乗分布乱数発生器インスタンス
     * @throws IllegalArgumentException パラメータが範囲外の場合
     */
    @Override
    public ChiSquaredRnd instanceOf(double k) {
        if (!this.acceptsParameter(k)) {
            throw new IllegalArgumentException(
                    String.format("パラメータ不正:k=%s", k));
        }

        return new GammaTypeChiSquaredRnd(k, this.gammaRndFactory);
    }

    @Override
    public String toString() {
        return "ChiSquaredRnd.Factory";
    }
}
