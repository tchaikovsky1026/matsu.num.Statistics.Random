/*
 * Copyright © 2026 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.6.8
 */
package matsu.num.statistics.random;

/**
 * 乱数生成時における, 予期せぬ例外であることを表現する.
 * 
 * @author Matsuura Y.
 */
public final class UnexpectedRandomGenerationException extends RuntimeException {

    /** デフォルトシリアルバージョン. */
    private static final long serialVersionUID = 1L;

    public UnexpectedRandomGenerationException() {
        super(
                "random value generation fails, (probably) invalid implementation of %s."
                        .formatted(BaseRandom.class.getSimpleName()));
    }
}
