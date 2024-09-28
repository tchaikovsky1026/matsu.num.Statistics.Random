/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.27
 */
package matsu.num.statistics.random.levy;

import matsu.num.statistics.random.LevyRnd;
import matsu.num.statistics.random.base.ParameterlessFactory;

/**
 * {@link matsu.num.statistics.random.LevyRnd.Factory} の実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
final class LevyRndFactory
        extends ParameterlessFactory<LevyRnd> implements LevyRnd.Factory {

    /**
     * 乱数生成器を紐づけて, ファクトリを生成する.
     * 
     * @param rnd 乱数生成器
     * @throws NullPointerException 乱数生成器がnullの場合
     */
    LevyRndFactory(LevyRnd rnd) {
        super(rnd, "LevyRnd.Factory");
    }
}
