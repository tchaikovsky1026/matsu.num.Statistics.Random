/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.6
 */
package matsu.num.statistics.random.logi;

import matsu.num.statistics.random.LogisticRnd;
import matsu.num.statistics.random.base.ParameterlessFactory;

/**
 * {@link matsu.num.statistics.random.LogisticRnd.Factory} の実装.
 * 
 * @author Matsuura Y.
 */
final class LogisticRndFactory
        extends ParameterlessFactory<LogisticRnd> implements LogisticRnd.Factory {

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 乱数発生器を紐づけて, ファクトリを生成する.
     * 
     * @param rnd 乱数発生器
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    LogisticRndFactory(LogisticRnd rnd) {
        super(rnd, "LogisticRnd.Factory");
    }
}
