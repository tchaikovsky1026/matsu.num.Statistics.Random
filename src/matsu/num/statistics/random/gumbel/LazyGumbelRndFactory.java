/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.7
 */
package matsu.num.statistics.random.gumbel;

import java.util.function.Supplier;

import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.base.LazyParameterlessRndFactory;

/**
 * {@link matsu.num.statistics.random.GumbelRnd.Factory}
 * の遅延初期化による実装.
 * 
 * @author Matsuura Y.
 */
final class LazyGumbelRndFactory
        extends LazyParameterlessRndFactory<GumbelRnd> implements GumbelRnd.Factory {

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 乱数発生器のクリエイタを紐づけて, ファクトリを生成する.
     * 
     * @param rndCreator 乱数発生器のクリエイタ
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    LazyGumbelRndFactory(Supplier<GumbelRnd> rndCreator) {
        super(rndCreator, "GumbelRnd.Factory");
    }
}
