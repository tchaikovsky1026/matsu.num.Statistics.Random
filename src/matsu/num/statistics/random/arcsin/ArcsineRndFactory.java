/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.31
 */
package matsu.num.statistics.random.arcsin;

import matsu.num.statistics.random.ArcsineRnd;
import matsu.num.statistics.random.base.SkeletalParameterlessRndFactory;

/**
 * {@link ArcsineRnd.Factory} の実装を提供する.
 * 
 * @author Matsuura Y.
 */
final class ArcsineRndFactory
        extends SkeletalParameterlessRndFactory<ArcsineRnd>
        implements ArcsineRnd.Factory {

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 乱数発生器を紐づけて, ファクトリを生成する.
     * 
     * @param rnd 乱数発生器
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    ArcsineRndFactory(ArcsineRnd rnd) {
        super(rnd, "ArcsineRnd.Factory");
    }
}
