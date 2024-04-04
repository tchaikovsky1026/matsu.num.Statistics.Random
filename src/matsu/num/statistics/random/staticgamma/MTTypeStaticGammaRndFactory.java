/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.staticgamma;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * このパッケージに用意された {@link StaticGammaRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
public final class MTTypeStaticGammaRndFactory implements StaticGammaRnd.Factory {

    private final StaticGammaRnd instance;

    public MTTypeStaticGammaRndFactory(
            Exponentiation exponentiation, ExponentialRnd.Factory exponentialRndFactory,
            NormalRnd.Factory normalRndFactory) {
        super();

        /* StaticGammaRndは状態を持たないのでこの時点で生成している. */
        this.instance = new MTTypeStaticGammaRnd(
                exponentiation, exponentialRndFactory, normalRndFactory);
    }

    @Override
    public StaticGammaRnd instance() {
        return this.instance;
    }

    @Override
    public String toString() {
        return "StaticGammaRnd.Factory";
    }
}
