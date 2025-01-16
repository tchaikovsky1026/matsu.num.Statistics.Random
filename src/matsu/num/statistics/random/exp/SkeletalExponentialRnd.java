/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.exp;

import matsu.num.statistics.random.ExponentialRnd;

/**
 * {@link ExponentialRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
public abstract non-sealed class SkeletalExponentialRnd implements ExponentialRnd {

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
