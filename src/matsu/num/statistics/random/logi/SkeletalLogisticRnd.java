/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.logi;

import matsu.num.statistics.random.LogisticRnd;

/**
 * {@link LogisticRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.2
 */
public abstract non-sealed class SkeletalLogisticRnd implements LogisticRnd {

    /**
     * 唯一のコンストラクタ.
     */
    SkeletalLogisticRnd() {
        super();
    }

    @Override
    public String toString() {
        return "LogisticRnd";
    }
}
