/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.25
 */
package matsu.num.statistics.random.cauchy;

import matsu.num.statistics.random.base.ParameterlessFactory;

/**
 * {@link matsu.num.statistics.random.CauchyRnd.Factory} の実装.
 * 
 * @author Matsuura Y.
 * @version 22.0
 */
final class CauchyRndFactory
        extends ParameterlessFactory<CauchyRnd> implements CauchyRnd.Factory {

    /**
     * 乱数生成器を紐づけて, ファクトリを生成する.
     * 
     * @param rnd 乱数生成器
     * @throws NullPointerException 乱数生成器がnullの場合
     */
    CauchyRndFactory(CauchyRnd rnd) {
        super(rnd, "CauchyRnd.Factory");
    }
}
