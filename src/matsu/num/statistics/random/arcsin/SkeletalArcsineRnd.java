/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.31
 */
package matsu.num.statistics.random.arcsin;

import matsu.num.statistics.random.ArcsineRnd;

/**
 * {@link ArcsineRnd} の骨格実装を提供する.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalArcsineRnd implements ArcsineRnd {

    /**
     * 唯一のコンストラクタ.
     */
    SkeletalArcsineRnd() {
        super();
    }

    @Override
    public String toString() {
        return "ArcsineRnd";
    }
}
