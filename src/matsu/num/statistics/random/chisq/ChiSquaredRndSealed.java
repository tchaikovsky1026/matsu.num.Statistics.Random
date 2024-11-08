/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.chisq;

import matsu.num.statistics.random.ChiSquaredRnd;

/**
 * {@link matsu.num.statistics.random.ChiSquaredRnd} をシールするための非公開インターフェース.
 *
 * @author Matsuura Y.
 * @version 22.1
 */
public non-sealed interface ChiSquaredRndSealed extends ChiSquaredRnd {

    public static non-sealed interface FactorySealed extends ChiSquaredRnd.Factory {

    }
}
