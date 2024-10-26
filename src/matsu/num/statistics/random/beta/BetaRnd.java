/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.24
 */
package matsu.num.statistics.random.beta;

/**
 * {@link matsu.num.statistics.random.BetaRnd} をシールするための非公開インターフェース.
 *
 * @author Matsuura Y.
 * @version 22.0
 */
public non-sealed interface BetaRnd extends matsu.num.statistics.random.BetaRnd {

    public static non-sealed interface Factory extends matsu.num.statistics.random.BetaRnd.Factory {

    }
}
