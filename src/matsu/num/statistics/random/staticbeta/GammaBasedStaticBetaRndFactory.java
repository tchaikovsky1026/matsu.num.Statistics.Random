/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.staticbeta;

import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.StaticGammaRnd;

/**
 * このパッケージが扱う, {@link StaticBetaRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
public final class GammaBasedStaticBetaRndFactory implements StaticBetaRnd.Factory {

    private final StaticBetaRnd instance;

    public GammaBasedStaticBetaRndFactory(StaticGammaRnd.Factory staticGammaRndFactory) {

        /* StaticBetaRndは状態を持たないので, この時点で生成している. */
        this.instance = new GammaBasedStaticBetaRnd(staticGammaRndFactory);
    }

    /**
     * <p>
     * Staticベータ乱数発生器インスタンスを返す.
     * </p>
     *
     * @return Staticベータ乱数発生器インスタンス
     */
    @Override
    public StaticBetaRnd instance() {
        return this.instance;
    }

    @Override
    public String toString() {
        return "StaticBetaRnd.Factory";
    }
}
