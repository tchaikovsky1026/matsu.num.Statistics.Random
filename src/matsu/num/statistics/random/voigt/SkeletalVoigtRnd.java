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
 * {@link VoigtRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.0
 */
abstract class SkeletalVoigtRnd implements VoigtRnd {

    protected final double alpha;

    /**
     * 唯一のコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないことに注意.
     * 
     * @param alpha パラメータ
     */
    protected SkeletalVoigtRnd(double alpha) {
        super();

        assert matsu.num.statistics.random.VoigtRnd.acceptsParameter(alpha) : "パラメータ不正";

        this.alpha = alpha;
    }

    @Override
    public final double alpha() {
        return this.alpha;
    }

    @Override
    public String toString() {
        return String.format("VoigtRnd(%s)", this.alpha());
    }
}
