/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.27
 */
package matsu.num.statistics.random.gumbel;

import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.base.ParameterlessFactory;

/**
 * {@link matsu.num.statistics.random.GumbelRnd.Factory} の実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
final class GumbelRndFactory
        extends ParameterlessFactory<GumbelRnd> implements GumbelRnd.Factory {

    /**
     * 乱数生成器を紐づけて, ファクトリを生成する. <br>
     * {@code null} を渡してはいけない.
     * 
     * @param rnd 乱数生成器
     * @throws NullPointerException 乱数生成器がnullの場合
     */
    GumbelRndFactory(GumbelRnd rnd) {
        super(rnd, "GumbelRnd.Factory");
    }
}
