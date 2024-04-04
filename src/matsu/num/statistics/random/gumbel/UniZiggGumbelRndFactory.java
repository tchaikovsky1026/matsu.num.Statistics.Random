/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.gumbel;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * 単峰Zigg標準Gumbel分布に従う乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
public final class UniZiggGumbelRndFactory implements GumbelRnd.Factory {

    private final GumbelRnd instance;

    public UniZiggGumbelRndFactory(Exponentiation exponentiation, ExponentialRnd.Factory exponentialRndFactory) {

        /* GumbelRndは状態を持たないのであらかじめ生成する */
        this.instance = new UniZiggGumbelRnd(exponentiation, exponentialRndFactory);
    }

    @Override
    public GumbelRnd instance() {
        return this.instance;
    }

    @Override
    public String toString() {
        return "GumbelRnd.Factory";
    }
}
