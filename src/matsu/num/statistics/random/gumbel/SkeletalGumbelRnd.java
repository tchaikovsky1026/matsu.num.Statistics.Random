/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.gumbel;

import matsu.num.statistics.random.GumbelRnd;

/**
 * {@link GumbelRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.2
 */
public abstract non-sealed class SkeletalGumbelRnd implements GumbelRnd {

    /**
     * 唯一の外部に公開されないコンストラクタ.
     */
    SkeletalGumbelRnd() {
        super();
    }

    @Override
    public String toString() {
        return "GumbelRnd";
    }
}
