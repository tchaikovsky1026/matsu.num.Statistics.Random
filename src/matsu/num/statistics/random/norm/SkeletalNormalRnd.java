/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.25
 */
package matsu.num.statistics.random.norm;

/**
 * {@link NormalRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.0
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
