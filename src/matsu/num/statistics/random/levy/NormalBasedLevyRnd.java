/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.7
 */
package matsu.num.statistics.random.levy;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.LevyRnd;
import matsu.num.statistics.random.NormalRnd;

/**
 * 標準正規分布を用いた, 標準L&eacute;vy乱数発生器.
 * 
 * @author Matsuura Y.
 */
public final class NormalBasedLevyRnd extends SkeletalLevyRnd {

    private final NormalRnd normalRnd;

    private NormalBasedLevyRnd(NormalRnd.Factory normalRndFactory) {
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

    /**
     * {@link matsu.num.statistics.random.LevyRnd.Factory} を生成する.
     * 
     * @param normalRndFactory 正規乱数発生器のファクトリ
     * @return Levy乱数のファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static LevyRnd.Factory createFactory(NormalRnd.Factory normalRndFactory) {
        return new LazyLevyRndFactory(
                () -> new NormalBasedLevyRnd(normalRndFactory));
    }
}
