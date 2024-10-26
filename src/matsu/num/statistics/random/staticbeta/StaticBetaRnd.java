/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.26
 */
package matsu.num.statistics.random.staticbeta;

/**
 * {@link matsu.num.statistics.random.StaticBetaRnd} をシールするための非公開インターフェース.
 * 
 * @author Matsuura Y.
 * @version 22.0
 */
public non-sealed interface StaticBetaRnd extends matsu.num.statistics.random.StaticBetaRnd{

    public static non-sealed interface Factory extends matsu.num.statistics.random.StaticBetaRnd.Factory{

    }
}
