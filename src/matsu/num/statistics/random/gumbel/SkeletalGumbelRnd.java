/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random.gumbel;

import matsu.num.statistics.random.GumbelRnd;

/**
 * {@link GumbelRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalGumbelRnd implements GumbelRnd {

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
