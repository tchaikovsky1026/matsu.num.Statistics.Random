/**
 * 2024.3.16
 */
package matsu.num.statistics.random.weibull;

import java.util.Objects;

import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.WeibullRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * 標準Gumbelベースの標準Weibull分布乱数生成器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 19.0
 */
public final class GumbelBasedWeibullRndFactory implements WeibullRnd.Factory {

    private final Exponentiation exponentiation;
    private final GumbelRnd.Factory gumbelRndFactory;

    public GumbelBasedWeibullRndFactory(Exponentiation exponentiation, GumbelRnd.Factory gumbelRndFactory) {
        super();
        this.exponentiation = Objects.requireNonNull(exponentiation);
        this.gumbelRndFactory = Objects.requireNonNull(gumbelRndFactory);
    }

    @Override
    public boolean acceptsParameter(double k) {
        return WeibullRnd.LOWER_LIMIT_SHAPE_PARAMETER <= k
                && k <= WeibullRnd.UPPER_LIMIT_SHAPE_PARAMETER;
    }

    @Override
    public WeibullRnd instanceOf(double k) {
        if (!this.acceptsParameter(k)) {
            throw new IllegalArgumentException(
                    String.format(
                            "パラメータ不正:k=%s", k));
        }
        return new GumbelBasedWeibullRnd(k, this.exponentiation, this.gumbelRndFactory);
    }

    @Override
    public String toString() {
        return "WeibullRnd.Factory";
    }
}
