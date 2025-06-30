/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.mix;

import java.util.List;
import java.util.function.ToDoubleFunction;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.CategoricalRnd;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.cat.CategoricalFactoryForTesting;
import matsu.num.statistics.random.exp.ExponentialFactoryForTesting;

/**
 * {@link CategoricalBasedFloatingMixtureRnd} に関する
 * {@link TestedFloatingRandomGenerator} を提供する.
 * 
 * <p>
 * このモデルはシフトした指数分布の混合で扱う. <br>
 * シフトが[0, 1, 2, 3, 4],
 * 混合比が [5, 4, 3, 2, 1] とする.
 * </p>
 * 
 * @author Matsuura Y.
 */
final class TestedCategoricalBasedFloatingMixtureRnd implements TestedFloatingRandomGenerator {

    private final BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

    private final FloatingMixtureRnd mixtureRnd;

    /**
     * 唯一のコンストラクタ.
     */
    TestedCategoricalBasedFloatingMixtureRnd() {
        super();

        CategoricalRnd selector = CategoricalFactoryForTesting.FACTORY.instanceOf(
                new double[] { 5, 4, 3, 2, 1 });
        final ExponentialRnd exponentialRnd = ExponentialFactoryForTesting.FACTORY.instance();

        List<ToDoubleFunction<BaseRandom>> components = List.of(
                br -> exponentialRnd.nextRandom(br),
                br -> exponentialRnd.nextRandom(br) + 1d,
                br -> exponentialRnd.nextRandom(br) + 2d,
                br -> exponentialRnd.nextRandom(br) + 3d,
                br -> exponentialRnd.nextRandom(br) + 4d);

        this.mixtureRnd = CategoricalBasedFloatingMixtureRnd.createFrom(selector, components);
    }

    @Override
    public double newValue() {
        return mixtureRnd.nextRandom(baseRandom);
    }

    @Override
    public double cumulativeProbability(double arg) {

        double totalWeight = 15d;

        return (5 * componentCumulative(arg) +
                4 * componentCumulative(arg - 1) +
                3 * componentCumulative(arg - 2) +
                2 * componentCumulative(arg - 3) +
                1 * componentCumulative(arg - 4))
                / totalWeight;
    }

    private double componentCumulative(double arg) {
        if (arg < 0d) {
            return 0d;
        }
        return 1d - Math.exp(-arg);
    }
}
