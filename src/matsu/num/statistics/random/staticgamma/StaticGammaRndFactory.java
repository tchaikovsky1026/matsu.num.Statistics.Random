/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.staticgamma;

import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.base.ParameterlessFactory;

/**
 * {@link matsu.num.statistics.random.StaticGammaRnd.Factory} の実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
final class StaticGammaRndFactory
        extends ParameterlessFactory<StaticGammaRnd> implements StaticGammaRnd.Factory {

    /**
     * 乱数生成器を紐づけて, ファクトリを生成する.
     * 
     * @param rnd 乱数生成器
     * @throws NullPointerException 乱数生成器がnullの場合
     */
    public StaticGammaRndFactory(StaticGammaRnd rnd) {
        super(rnd, "StaticGammaRnd.Factory");
    }
}