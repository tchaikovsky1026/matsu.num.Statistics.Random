/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random.norm;

import matsu.num.statistics.random.NormalRnd;

/**
 * {@link NormalRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
public abstract class SkeletalNormalRnd implements NormalRnd {

    /**
     * 唯一のコンストラクタ.
     */
    SkeletalNormalRnd() {
        super();
    }

    @Override
    public String toString() {
        return "NormalRnd";
    }
}
