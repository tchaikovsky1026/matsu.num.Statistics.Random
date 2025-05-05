/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random.logi;

import matsu.num.statistics.random.LogisticRnd;

/**
 * {@link LogisticRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
public abstract class SkeletalLogisticRnd implements LogisticRnd {

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
