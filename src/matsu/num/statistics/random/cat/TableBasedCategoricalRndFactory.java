/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.cat;

import java.util.Objects;

import matsu.num.statistics.random.CategoricalRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * テーブル法に基づく, カテゴリカル分布に従う乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 22.2
 */
public final class TableBasedCategoricalRndFactory extends SkeletalCategoricalRndFactory {

    public TableBasedCategoricalRndFactory(Exponentiation exponentiation) {
        super(Objects.requireNonNull(exponentiation));
    }

    @Override
    CategoricalRnd createInstanceOf(double[] probability) {
        return new TableBasedCategoricalRnd(probability);
    }
}
