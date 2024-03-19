/**
 * 2024.1.14
 */
package matsu.num.statistics.random.levy;

import matsu.num.statistics.random.LevyRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.BaseRandom;

/**
 * 標準正規分布を用いた, 標準L&eacute;vy乱数発生器.
 * 
 * @author Matsuura Y.
 * @version 18.0
 */
final class NormalBasedLevyRnd implements LevyRnd {

    private final NormalRnd normalRnd;

    NormalBasedLevyRnd(NormalRnd.Factory normalRndFactory) {
        super();
        this.normalRnd = normalRndFactory.instance();
    }

    @Override
    public double nextRandom(BaseRandom random) {
        while (true) {
            double y = this.normalRnd.nextRandom(random);
            double x = 1 / (y * y);
            if (Double.isFinite(x)) {
                return x;
            }
        }
    }

    @Override
    public String toString() {
        return "LevyRnd";
    }
}
