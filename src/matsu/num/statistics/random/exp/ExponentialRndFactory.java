/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.6
 */
package matsu.num.statistics.random.exp;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.base.SimpleParameterlessRndFactory;

/**
 * {@link matsu.num.statistics.random.ExponentialRnd.Factory} の実装.
 * 
 * @author Matsuura Y.
 * @deprecated このクラスは使われていない
 */
@Deprecated
final class ExponentialRndFactory
        extends SimpleParameterlessRndFactory<ExponentialRnd> implements ExponentialRnd.Factory {

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 乱数発生器を紐づけて, ファクトリを生成する.
     * 
     * @param rnd 乱数発生器
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    ExponentialRndFactory(ExponentialRnd rnd) {
        super(rnd, "ExponentialRnd.Factory");
    }
}
