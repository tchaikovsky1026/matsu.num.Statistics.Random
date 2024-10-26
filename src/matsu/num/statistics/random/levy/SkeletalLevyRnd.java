/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.25
 */
package matsu.num.statistics.random.levy;

/**
 * {@link LevyRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.0
 */
abstract class SkeletalLevyRnd implements LevyRnd {

    /**
     * 唯一のコンストラクタ.
     */
    protected SkeletalLevyRnd() {
        super();
    }

    @Override
    public String toString() {
        return "LevyRnd";
    }
}
