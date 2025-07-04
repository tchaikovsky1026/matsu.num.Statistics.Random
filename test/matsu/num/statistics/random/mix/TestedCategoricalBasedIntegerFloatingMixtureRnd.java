/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.mix;

import java.util.List;
import java.util.function.ToIntFunction;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BoundIntRnd;
import matsu.num.statistics.random.GeometricRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;
import matsu.num.statistics.random.TestedIntegerRandomGenerator;
import matsu.num.statistics.random.cat.CategoricalFactoryForTesting;
import matsu.num.statistics.random.geo.GeometricFactoryForTesting;

/**
 * {@link SimpleFloatingMixtureRnd} に関する
 * {@link TestedFloatingRandomGenerator} を提供する.
 * 
 * <p>
 * このモデルは確率の異なるした幾何分布の混合で扱う. <br>
 * 確率が[0.1, 0.3, 0.5, 0.7, 0.9],
 * 混合比が [5, 4, 3, 2, 1] とする.
 * </p>
 * 
 * @author Matsuura Y.
 */
final class TestedCategoricalBasedIntegerFloatingMixtureRnd
        implements TestedIntegerRandomGenerator {

    private final BaseRandom baseRandom = BaseRandom.threadSeparatedRandom();

    private final IntegerMixtureRnd mixtureRnd;

    /**
     * 唯一のコンストラクタ.
     */
    TestedCategoricalBasedIntegerFloatingMixtureRnd() {
        super();

        BoundIntRnd selector = CategoricalFactoryForTesting.FACTORY.instanceOf(
                new double[] { 5, 4, 3, 2, 1 });
        GeometricRnd.Factory factory = GeometricFactoryForTesting.FACTORY;
        final GeometricRnd[] geometricRnd = {
                factory.instanceOf(0.1),
                factory.instanceOf(0.3),
                factory.instanceOf(0.5),
                factory.instanceOf(0.7),
                factory.instanceOf(0.9)
        };

        List<ToIntFunction<BaseRandom>> components = List.of(
                geometricRnd[0]::nextRandom,
                geometricRnd[1]::nextRandom,
                geometricRnd[2]::nextRandom,
                geometricRnd[3]::nextRandom,
                geometricRnd[4]::nextRandom);

        this.mixtureRnd = SimpleIntegerMixtureRnd.createFrom(selector, components);
    }

    @Override
    public int newValue() {
        return mixtureRnd.nextRandom(baseRandom);
    }

    @Override
    public double cumulativeProbability(int arg) {
        double totalWeight = 15d;

        return (5 * componentCumulative(arg, 0.1) +
                4 * componentCumulative(arg, 0.3) +
                3 * componentCumulative(arg, 0.5) +
                2 * componentCumulative(arg, 0.7) +
                1 * componentCumulative(arg, 0.9))
                / totalWeight;
    }

    private double componentCumulative(int arg, double p) {
        if (arg < 0d) {
            return 0d;
        }
        return 1 - Math.exp(arg * Math.log1p(-p));
    }
}
