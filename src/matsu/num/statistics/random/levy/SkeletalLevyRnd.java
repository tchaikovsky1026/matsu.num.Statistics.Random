/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.27
 */
package matsu.num.statistics.random.levy;

import matsu.num.statistics.random.LevyRnd;

/**
 * {@link LevyRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
abstract class SkeletalLevyRnd implements LevyRnd {

    /**
     * 唯一のコンストラクタ.
     */
    protected SkeletalLevyRnd() {
        super();
    }

    @Override
    public String toString() {
        return "LevyRnd";
    }
}
