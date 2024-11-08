/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.geo;

import matsu.num.statistics.random.GeometricRnd;

/**
 * {@link matsu.num.statistics.random.GeometricRnd} をシールするための非公開インターフェース.
 *
 * @author Matsuura Y.
 * @version 22.1
 */
public non-sealed interface GeometricRndSealed extends GeometricRnd {

    public static non-sealed interface FactorySealed extends GeometricRnd.Factory {

    }
}
