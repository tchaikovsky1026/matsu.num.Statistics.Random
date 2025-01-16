/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.gumbel;

import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.base.ParameterlessFactory;

/**
 * {@link matsu.num.statistics.random.GumbelRnd.Factory} の実装.
 * 
 * @author Matsuura Y.
 */
public final class GumbelRndFactory
        extends ParameterlessFactory<GumbelRnd> implements GumbelRnd.Factory {

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 乱数発生器を紐づけて, ファクトリを生成する. <br>
     * {@code null} を渡してはいけない.
     * 
     * @param rnd 乱数発生器
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    GumbelRndFactory(GumbelRnd rnd) {
        super(rnd, "GumbelRnd.Factory");
    }
}
