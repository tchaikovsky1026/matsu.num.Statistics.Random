/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random.tdist;

import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.TDistributionRnd;

/**
 * {@link TDistributionRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @deprecated
 *                 このクラスは {@link CauchyRnd#asTDistributionRnd()}
 *                 を実装するために用意されたクラスであり,
 *                 そのメソッドが削除される version 26 以降で削除予定である.
 */
@Deprecated(forRemoval = true)
public abstract class PublicSkeletalTDistributionRnd implements TDistributionRnd {

    protected final double nu;

    /**
     * 唯一のコンストラクタ. <br>
     * 与えるパラメータについて, バリデーションは行われていないので,
     * このままモジュール外に公開してはいけない.
     * 
     * @param nu 自由度
     */
    protected PublicSkeletalTDistributionRnd(double nu) {
        super();

        assert TDistributionRnd.acceptsParameter(nu) : "パラメータ不正";

        this.nu = nu;
    }

    @Override
    public final double degreesOfFreedom() {
        return this.nu;
    }

    @Override
    public String toString() {
        return String.format(
                "TDistRnd(%s)", this.degreesOfFreedom());
    }
}
