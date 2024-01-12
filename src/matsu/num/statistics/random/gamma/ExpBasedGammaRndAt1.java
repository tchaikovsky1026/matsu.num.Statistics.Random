/**
 * 2024.1.8
 */
package matsu.num.statistics.random.gamma;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.exp.ExponentialRndFactory;

/**
 * 標準指数乱数によって実装された, 形状パラメータが1の乱数生成器.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
final class ExpBasedGammaRndAt1 implements GammaRnd {

    private final ExponentialRnd expRnd = ExponentialRndFactory.instance();

    ExpBasedGammaRndAt1() {
        super();
    }

    @Override
    public double shapeParameter() {
        return 1d;
    }

    @Override
    public double nextRandom(Random random) {
        return expRnd.nextRandom(random);
    }

    @Override
    public String toString() {
        return "GammaRnd(Exponential)";
    }
}
