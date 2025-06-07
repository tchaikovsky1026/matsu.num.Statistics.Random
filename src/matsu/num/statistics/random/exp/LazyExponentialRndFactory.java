/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.7
 */
package matsu.num.statistics.random.exp;

import java.util.function.Supplier;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.base.LazyParameterlessRndFactory;

/**
 * {@link matsu.num.statistics.random.ExponentialRnd.Factory}
 * の遅延初期化の実装.
 * 
 * @author Matsuura Y.
 */
final class LazyExponentialRndFactory
        extends LazyParameterlessRndFactory<ExponentialRnd> implements ExponentialRnd.Factory {

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 乱数発生器のクリエイタを紐づけて, ファクトリを生成する.
     * 
     * @param rndCreator 乱数発生器のクリエイタ
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    LazyExponentialRndFactory(Supplier<ExponentialRnd> rndCreator) {
        super(rndCreator, "ExponentialRnd.Factory");
    }
}
