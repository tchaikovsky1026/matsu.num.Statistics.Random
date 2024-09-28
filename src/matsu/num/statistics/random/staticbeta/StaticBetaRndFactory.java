/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.staticbeta;

import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.base.ParameterlessFactory;

/**
 * {@link matsu.num.statistics.random.StaticBetaRnd.Factory} の実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
final class StaticBetaRndFactory
        extends ParameterlessFactory<StaticBetaRnd> implements StaticBetaRnd.Factory {

    /**
     * 乱数生成器を紐づけて, ファクトリを生成する.
     * 
     * @param rnd 乱数生成器
     * @throws NullPointerException 乱数生成器がnullの場合
     */
    public StaticBetaRndFactory(StaticBetaRnd rnd) {
        super(rnd, "StaticBetaRnd.Factory");
    }
}
