/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.logi;

import matsu.num.statistics.random.LogisticRnd;

/**
 * {@link LogisticRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
abstract class SkeletalLogisticRnd implements LogisticRnd {

    /**
     * 唯一のコンストラクタ.
     */
    protected SkeletalLogisticRnd() {
        super();
    }

    @Override
    public String toString() {
        return "LogisticRnd";
    }
}
