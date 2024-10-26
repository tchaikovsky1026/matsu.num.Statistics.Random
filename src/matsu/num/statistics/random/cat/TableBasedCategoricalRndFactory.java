/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.cat;

import java.util.Objects;

import matsu.num.statistics.random.lib.Exponentiation;

/**
 * テーブル法に基づく, カテゴリカル分布に従う乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
public final class TableBasedCategoricalRndFactory extends SkeletalCategoricalRndFactory {

    public TableBasedCategoricalRndFactory(Exponentiation exponentiation) {
        super(Objects.requireNonNull(exponentiation));
    }

    @Override
    protected CategoricalRnd createInstanceOf(double[] probability) {
        return new TableBasedCategoricalRnd(probability);
    }
}
