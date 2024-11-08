/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.logi;

import matsu.num.statistics.random.base.ParameterlessFactory;

/**
 * {@link LogisticRndSealed.FactorySealed} の実装.
 * 
 * @author Matsuura Y.
 * @version 22.1
 */
final class LogisticRndFactory
        extends ParameterlessFactory<LogisticRndSealed> implements LogisticRndSealed.FactorySealed {

    /**
     * 乱数発生器を紐づけて, ファクトリを生成する.
     * 
     * @param rnd 乱数発生器
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    LogisticRndFactory(LogisticRndSealed rnd) {
        super(rnd, "LogisticRnd.Factory");
    }
}
