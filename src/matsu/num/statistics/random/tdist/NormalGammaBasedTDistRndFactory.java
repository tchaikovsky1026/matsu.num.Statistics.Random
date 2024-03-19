/**
 * 2024.3.16
 */
package matsu.num.statistics.random.tdist;

import java.util.Objects;

import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * 正規ガンマタイプのt分布に従う乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 19.0
 */
public final class NormalGammaBasedTDistRndFactory implements TDistributionRnd.Factory {

    private final Exponentiation exponentiation;
    private final NormalRnd.Factory normalRndFactory;
    private final GammaRnd.Factory gammaRndFactory;

    public NormalGammaBasedTDistRndFactory(
            Exponentiation exponentiation,
            NormalRnd.Factory normalRndFactory,
            GammaRnd.Factory gammaRndFactory) {
        super();
        this.exponentiation = Objects.requireNonNull(exponentiation);
        this.normalRndFactory = Objects.requireNonNull(normalRndFactory);
        this.gammaRndFactory = Objects.requireNonNull(gammaRndFactory);
    }

    @Override
    public boolean acceptsParameter(double nu) {
        return TDistributionRnd.LOWER_LIMIT_DEGREES_OF_FREEDOM <= nu
                && nu <= TDistributionRnd.UPPER_LIMIT_DEGREES_OF_FREEDOM;
    }

    @Override
    public TDistributionRnd instanceOf(double nu) {
        if (!this.acceptsParameter(nu)) {
            throw new IllegalArgumentException(String.format("パラメータ不正:nu=%s", nu));
        }
        return new NormalGammaBasedTDistRnd(nu, this.exponentiation, this.normalRndFactory, this.gammaRndFactory);
    }

    @Override
    public String toString() {
        return "TDistRnd.Factory";
    }
}
