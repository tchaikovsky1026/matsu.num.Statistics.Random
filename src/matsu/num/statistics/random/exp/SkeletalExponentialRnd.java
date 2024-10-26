/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.25
 */
package matsu.num.statistics.random.exp;

/**
 * {@link ExponentialRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.0
 */
abstract class SkeletalExponentialRnd implements ExponentialRnd {

    /**
     * 唯一のコンストラクタ.
     */
    protected SkeletalExponentialRnd() {
        super();
    }

    @Override
    public String toString() {
        return "ExponentialRnd";
    }
}
