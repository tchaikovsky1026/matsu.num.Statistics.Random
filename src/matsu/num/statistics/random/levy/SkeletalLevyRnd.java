/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.levy;

import matsu.num.statistics.random.LevyRnd;

/**
 * {@link LevyRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.2
 */
public abstract non-sealed class SkeletalLevyRnd implements LevyRnd {

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
