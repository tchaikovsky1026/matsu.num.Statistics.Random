/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.27
 */
package matsu.num.statistics.random.gumbel;

import matsu.num.statistics.random.GumbelRnd;

/**
 * {@link GumbelRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
abstract class SkeletalGumbelRnd implements GumbelRnd {

    /**
     * 唯一のコンストラクタ.
     */
    protected SkeletalGumbelRnd() {
        super();
    }

    @Override
    public String toString() {
        return "GumbelRnd";
    }
}
