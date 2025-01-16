/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.tdist;

import matsu.num.statistics.random.TDistributionRnd;

/**
 * {@link matsu.num.statistics.random.TDistributionRnd.Factory} の骨格実装.
 * 
 * @author Matsuura Y.
 */
public abstract non-sealed class SkeletalTDistributionRndFactory implements TDistributionRnd.Factory {

    /**
     * 唯一の外部に公開されないコンストラクタ.
     */
    SkeletalTDistributionRndFactory() {
        super();
    }

    @Override
    public final TDistributionRnd instanceOf(double nu) {
        if (!TDistributionRnd.acceptsParameter(nu)) {
            throw new IllegalArgumentException(String.format("パラメータ不正:nu=%s", nu));
        }

        return this.createInstanceOf(nu);
    }

    /**
     * {@link #instanceOf(double)} で返すインスタンスを生成するための抽象メソッド.
     * 
     * <p>
     * このメソッドは {@link #instanceOf(double)} の内部で呼ばれるために用意されており,
     * 外部から呼ぶことは許されず, 継承先でアクセス修飾子を緩めてはいけない. <br>
     * 内部から呼ばれる場合, 引数は必ず正当である.
     * </p>
     * 
     * @param nu 自由度
     * @return 自由度が <i>&nu;</i> のStudent-t分布乱数発生器インスタンス
     */
    abstract TDistributionRnd createInstanceOf(double nu);

    @Override
    public String toString() {
        return "TDistRnd.Factory";
    }
}
