/**
 * 2023.3.22
 */
package matsu.num.statistics.random.gamma.mt;

import java.util.Objects;
import java.util.Random;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GammaRnd;

/**
 * 標準指数乱数によって実装された, 形状パラメータが1の乱数生成器.
 * 
 * @author Matsuura Y.
 * @version 11.0
 */
final class GaRndAt1 implements GammaRnd {

    private static final GammaRnd INSTANCE = new GaRndAt1();

    private final ExponentialRnd expRnd = ExponentialRnd.instance();

    private GaRndAt1() {
        if (Objects.nonNull(INSTANCE)) {
            throw new AssertionError();
        }
    }

    @Override
    public double shapeParameter() {
        return 1;
    }

    @Override
    public double nextRandom(Random random) {
        return expRnd.nextRandom(random);
    }

    /**
     * 
     * @return インスタンス
     */
    public static GammaRnd instance() {
        return INSTANCE;
    }

}
