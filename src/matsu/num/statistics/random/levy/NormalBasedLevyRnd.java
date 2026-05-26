/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.5.27
 */
package matsu.num.statistics.random.levy;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.LevyRnd;

/**
 * 標準正規分布を用いた, 標準L&eacute;vy乱数発生器.
 * 
 * @author Matsuura Y.
 */
public final class NormalBasedLevyRnd extends SkeletalLevyRnd {

    private NormalBasedLevyRnd() {
        super();
    }

    @Override
    public double nextRandom(BaseRandom random) {
        double y = random.nextGaussian();
        return 1 / (y * y);
    }

    /**
     * {@link matsu.num.statistics.random.LevyRnd.Factory} を生成する.
     * 
     * @return Levy乱数のファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static LevyRnd.Factory createFactory() {
        return new LazyLevyRndFactory(
                () -> new NormalBasedLevyRnd());
    }
}
