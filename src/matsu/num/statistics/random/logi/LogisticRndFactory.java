/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.logi;

import matsu.num.statistics.random.LogisticRnd;
import matsu.num.statistics.random.base.ParameterlessFactory;

/**
 * {@link matsu.num.statistics.random.LogisticRnd.Factory} の実装.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
final class LogisticRndFactory
        extends ParameterlessFactory<LogisticRnd> implements LogisticRnd.Factory {

    /**
     * 乱数生成器を紐づけて, ファクトリを生成する.
     * 
     * @param rnd 乱数生成器
     * @throws NullPointerException 乱数生成器がnullの場合
     */
    LogisticRndFactory(LogisticRnd rnd) {
        super(rnd, "LogisticRnd.Factory");
    }
}
