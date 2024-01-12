/**
 * 2024.1.9
 */
package matsu.num.statistics.random.levy;

import matsu.num.statistics.random.LevyRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.norm.NormalRndFactory;

/**
 * 標準正規分布を用いた, 標準L&eacute;vy乱数発生器.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
final class NormalBasedLevyRnd implements LevyRnd {

    private final NormalRnd normalRnd = NormalRndFactory.instance();

    NormalBasedLevyRnd() {
        super();
    }

    @Override
    public double nextRandom(Random random) {
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
