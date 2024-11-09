/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.norm;

import matsu.num.statistics.random.NormalRnd;

/**
 * {@link NormalRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.2
 */
public abstract non-sealed class SkeletalNormalRnd implements NormalRnd {

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
