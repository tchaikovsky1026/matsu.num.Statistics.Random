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

/**
 * {@link LogisticRndSealed} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.1
 */
abstract class SkeletalLogisticRnd implements LogisticRndSealed {

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
