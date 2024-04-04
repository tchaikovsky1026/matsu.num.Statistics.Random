/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.logi;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.LogisticRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Ziggurat法による標準ロジスティック分布乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
public final class ZiggLogiRndFactory implements LogisticRnd.Factory {

    private final LogisticRnd instance;

    public ZiggLogiRndFactory(Exponentiation exponentiation, ExponentialRnd.Factory exponentialRndFactory) {
        super();

        /* LogisticRndは状態を持たないのであらかじめ生成する */
        this.instance = new ZiggLogiRnd(
                exponentiation,
                exponentialRndFactory);
    }

    @Override
    public LogisticRnd instance() {
        return this.instance;
    }

    @Override
    public String toString() {
        return "LogisticRnd.Factory";
    }
}
