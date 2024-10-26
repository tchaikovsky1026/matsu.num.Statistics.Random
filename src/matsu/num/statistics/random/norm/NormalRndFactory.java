/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.25
 */
package matsu.num.statistics.random.norm;

import matsu.num.statistics.random.base.ParameterlessFactory;

/**
 * {@link NormalRnd.Factory} の実装.
 * 
 * @author Matsuura Y.
 * @version 22.0
 */
final class NormalRndFactory
        extends ParameterlessFactory<NormalRnd> implements NormalRnd.Factory {

    /**
     * 乱数生成器を紐づけて, ファクトリを生成する.
     * 
     * @param rnd 乱数生成器
     * @throws NullPointerException 乱数生成器がnullの場合
     */
    NormalRndFactory(NormalRnd rnd) {
        super(rnd, "NormalRnd.Factory");
    }
}
