/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.26
 */
package matsu.num.statistics.random.voigt;

/**
 * {@link matsu.num.statistics.random.VoigtRnd} をシールするための非公開インターフェース.
 * 
 * @author Matsuura Y.
 * @version 22.0
 */
public non-sealed interface VoigtRnd extends matsu.num.statistics.random.VoigtRnd {

    public static non-sealed interface Factory extends matsu.num.statistics.random.VoigtRnd.Factory {

    }
}
