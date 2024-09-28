/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.27
 */
package matsu.num.statistics.random.norm;

import matsu.num.statistics.random.NormalRnd;

/**
 * {@link NormalRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
abstract class SkeletalNormalRnd implements NormalRnd {

    /**
     * 唯一のコンストラクタ.
     */
    protected SkeletalNormalRnd() {
        super();
    }

    @Override
    public String toString() {
        return "NormalRnd";
    }
}
