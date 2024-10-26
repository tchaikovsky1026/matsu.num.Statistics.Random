/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.25
 */
package matsu.num.statistics.random.gumbel;

/**
 * {@link GumbelRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.0
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
