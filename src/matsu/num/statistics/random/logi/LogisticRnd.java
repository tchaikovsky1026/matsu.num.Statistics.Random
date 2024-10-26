/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.25
 */
package matsu.num.statistics.random.logi;

/**
 * {@link matsu.num.statistics.random.LogisticRnd} をシールするための非公開インターフェース.
 *
 * @author Matsuura Y.
 * @version 22.0
 */
public non-sealed interface LogisticRnd extends matsu.num.statistics.random.LogisticRnd {

    public static non-sealed interface Factory extends matsu.num.statistics.random.LogisticRnd.Factory{

    }
}
