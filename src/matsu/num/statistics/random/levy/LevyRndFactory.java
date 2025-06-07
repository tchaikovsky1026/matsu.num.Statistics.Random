/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.6
 */
package matsu.num.statistics.random.levy;

import matsu.num.statistics.random.LevyRnd;
import matsu.num.statistics.random.base.SimpleParameterlessRndFactory;

/**
 * {@link matsu.num.statistics.random.LevyRnd.Factory} の実装.
 * 
 * @author Matsuura Y.
 * @deprecated このクラスは使われていない
 */
@Deprecated
final class LevyRndFactory
        extends SimpleParameterlessRndFactory<LevyRnd> implements LevyRnd.Factory {

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 乱数発生器を紐づけて, ファクトリを生成する.
     * 
     * @param rnd 乱数発生器
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    LevyRndFactory(LevyRnd rnd) {
        super(rnd, "LevyRnd.Factory");
    }
}
