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
 * {@link matsu.num.statistics.random.ExponentialRnd} をシールするための非公開インターフェース.
 *
 * @author Matsuura Y.
 * @version 22.0
 */
public non-sealed interface ExponentialRnd extends matsu.num.statistics.random.ExponentialRnd {

    public static non-sealed interface Factory extends matsu.num.statistics.random.ExponentialRnd.Factory {

    }
}
