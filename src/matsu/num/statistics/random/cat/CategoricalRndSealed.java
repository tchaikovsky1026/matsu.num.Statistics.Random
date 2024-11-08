/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.cat;

import matsu.num.statistics.random.CategoricalRnd;

/**
 * {@link matsu.num.statistics.random.CategoricalRnd} をシールするための非公開インターフェース.
 *
 * @author Matsuura Y.
 * @version 22.1
 */
public non-sealed interface CategoricalRndSealed extends CategoricalRnd {

    public static non-sealed interface FactorySealed extends CategoricalRnd.Factory {

    }
}
