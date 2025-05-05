/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random.levy;

import matsu.num.statistics.random.LevyRnd;

/**
 * {@link LevyRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalLevyRnd implements LevyRnd {

    /**
     * 唯一の外部に公開されないコンストラクタ.
     */
    SkeletalLevyRnd() {
        super();
    }

    @Override
    public String toString() {
        return "LevyRnd";
    }
}
