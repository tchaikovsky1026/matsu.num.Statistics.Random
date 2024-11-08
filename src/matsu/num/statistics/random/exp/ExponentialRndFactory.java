/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.exp;

import matsu.num.statistics.random.base.ParameterlessFactory;

/**
 * {@link ExponentialRndSealed.FactorySealed} の実装.
 * 
 * @author Matsuura Y.
 * @version 22.1
 */
final class ExponentialRndFactory
        extends ParameterlessFactory<ExponentialRndSealed> implements ExponentialRndSealed.FactorySealed {

    /**
     * 乱数発生器を紐づけて, ファクトリを生成する.
     * 
     * @param rnd 乱数発生器
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    ExponentialRndFactory(ExponentialRndSealed rnd) {
        super(rnd, "ExponentialRnd.Factory");
    }
}
