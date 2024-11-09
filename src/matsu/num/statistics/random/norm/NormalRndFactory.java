/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.norm;

import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.base.ParameterlessFactory;

/**
 * {@link matsu.num.statistics.random.NormalRnd.Factory} の実装.
 * 
 * @author Matsuura Y.
 * @version 22.2
 */
public final class NormalRndFactory
        extends ParameterlessFactory<NormalRnd> implements NormalRnd.Factory {

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 乱数発生器を紐づけて, ファクトリを生成する.
     * 
     * @param rnd 乱数発生器
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    NormalRndFactory(NormalRnd rnd) {
        super(rnd, "NormalRnd.Factory");
    }
}
