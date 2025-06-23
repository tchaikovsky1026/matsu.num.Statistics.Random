/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.6
 */
package matsu.num.statistics.random.cauchy;

import matsu.num.statistics.random.CauchyRnd;

/**
 * {@link CauchyRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalCauchyRnd implements CauchyRnd {

    /**
     * 唯一の外部に公開されないコンストラクタ.
     */
    SkeletalCauchyRnd() {
        super();
    }

    @Override
    public String toString() {
        return "CauchyRnd";
    }
}
