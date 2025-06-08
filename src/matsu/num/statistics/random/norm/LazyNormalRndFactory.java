/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.7
 */
package matsu.num.statistics.random.norm;

import java.util.function.Supplier;

import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.base.LazyParameterlessRndFactory;

/**
 * {@link matsu.num.statistics.random.NormalRnd.Factory}
 * の遅延初期化型の実装.
 * 
 * @author Matsuura Y.
 */
final class LazyNormalRndFactory
        extends LazyParameterlessRndFactory<NormalRnd> implements NormalRnd.Factory {

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 乱数発生器のクリエイタを紐づけて, ファクトリを生成する.
     * 
     * @param rndCreator 乱数発生器のクリエイタ
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    LazyNormalRndFactory(Supplier<NormalRnd> rndCreator) {
        super(rndCreator, "NormalRnd.Factory");
    }
}
