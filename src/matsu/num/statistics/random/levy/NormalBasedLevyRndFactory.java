/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.levy;

import matsu.num.statistics.random.LevyRnd;
import matsu.num.statistics.random.NormalRnd;

/**
 * 標準正規分布を用いた, 標準L&eacute;vy乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
public final class NormalBasedLevyRndFactory implements LevyRnd.Factory {

    private final LevyRnd instance;

    public NormalBasedLevyRndFactory(NormalRnd.Factory normalRndFactory) {
        super();

        /* LevyRndは内部に上体を持たないので, ここで生成. */
        this.instance = new NormalBasedLevyRnd(normalRndFactory);
    }

    @Override
    public LevyRnd instance() {
        return this.instance;
    }

    @Override
    public String toString() {
        return "LevyRnd.Factory";
    }
}
