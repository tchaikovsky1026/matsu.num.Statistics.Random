/**
 * 2024.1.9
 */
package matsu.num.statistics.random.cauthy;

import matsu.num.statistics.random.CauthyRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.tdist.TDistributionRndFactory;

/**
 * t分布乱数を使用した, Cauthy分布乱数発生器.
 * 
 * @author Matsuura Y.
 * @version 17.4
 * @deprecated {@linkplain ZiggCauthyRnd} の方が推奨
 */
@Deprecated
final class TDistTypeCauthyRnd implements CauthyRnd {

    private final TDistributionRnd tDist = TDistributionRndFactory.instanceOf(1d);

    TDistTypeCauthyRnd() {
        super();
    }

    @Override
    public TDistributionRnd asTDistributionRnd() {
        return this.tDist;
    }

    @Override
    public double nextRandom(Random random) {
        return this.tDist.nextRandom(random);
    }

    @Override
    public String toString() {
        return "CauthyRnd";
    }

}
