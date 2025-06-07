/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.7
 */
package matsu.num.statistics.random.cauchy;

import java.util.function.Supplier;

import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.base.LazyParameterlessRndFactory;

/**
 * {@link matsu.num.statistics.random.CauchyRnd.Factory}
 * の遅延初期化の実装.
 * 
 * @author Matsuura Y.
 */
final class LazyCauchyRndFactory
        extends LazyParameterlessRndFactory<CauchyRnd> implements CauchyRnd.Factory {

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 乱数発生器のクリエイタを紐づけて, ファクトリを生成する.
     * 
     * @param rndCreator 乱数発生器のクリエイタ
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    LazyCauchyRndFactory(Supplier<CauchyRnd> rndCreator) {
        super(rndCreator, "CauchyRnd.Factory");
    }
}
