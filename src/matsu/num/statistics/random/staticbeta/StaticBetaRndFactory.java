/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.6
 */
package matsu.num.statistics.random.staticbeta;

import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.base.SimpleParameterlessRndFactory;

/**
 * {@link matsu.num.statistics.random.StaticBetaRnd.Factory} の実装.
 * 
 * @author Matsuura Y.
 */
final class StaticBetaRndFactory
        extends SimpleParameterlessRndFactory<StaticBetaRnd> implements StaticBetaRnd.Factory {

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 乱数発生器を紐づけて, ファクトリを生成する.
     * 
     * @param rnd 乱数発生器
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    StaticBetaRndFactory(StaticBetaRnd rnd) {
        super(rnd, "StaticBetaRnd.Factory");
    }
}
