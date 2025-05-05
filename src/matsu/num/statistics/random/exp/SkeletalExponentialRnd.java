/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random.exp;

import matsu.num.statistics.random.ExponentialRnd;

/**
 * {@link ExponentialRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalExponentialRnd implements ExponentialRnd {

    /**
     * 唯一の外部に公開されないコンストラクタ.
     */
    SkeletalExponentialRnd() {
        super();
    }

    @Override
    public String toString() {
        return "ExponentialRnd";
    }
}
