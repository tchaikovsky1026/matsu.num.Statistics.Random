/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.cauchy;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.tdist.SkeletalTDistributionRnd;

/**
 * {@link CauchyRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
abstract class SkeletalCauchyRnd implements CauchyRnd {

    private final TDistributionRnd tdistView;

    /**
     * 唯一のコンストラクタ.
     */
    protected SkeletalCauchyRnd() {
        super();

        this.tdistView = new TDistView();
    }

    @Override
    public final TDistributionRnd asTDistributionRnd() {
        return this.tdistView;
    }

    @Override
    public String toString() {
        return "CauchyRnd";
    }

    /**
     * Cauchy分布のt分布としてのビューを扱うクラス.
     */
    private final class TDistView extends SkeletalTDistributionRnd {

        /**
         * 唯一のコンストラクタ.
         */
        TDistView() {
            super(1d);
        }

        @Override
        public double nextRandom(BaseRandom random) {
            return SkeletalCauchyRnd.this.nextRandom(random);
        }
    }
}