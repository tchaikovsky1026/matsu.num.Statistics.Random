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
 * {@link matsu.num.statistics.random.LevyRnd} をシールするための非公開インターフェース.
 * 
 * @author Matsuura Y.
 * @version 22.0
 */
public non-sealed interface LevyRnd extends matsu.num.statistics.random.LevyRnd {

    public static non-sealed interface Factory extends matsu.num.statistics.random.LevyRnd.Factory {

    }
}
