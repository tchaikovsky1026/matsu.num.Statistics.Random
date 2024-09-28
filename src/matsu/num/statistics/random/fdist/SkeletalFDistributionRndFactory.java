/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.fdist;

import matsu.num.statistics.random.FDistributionRnd;

/**
 * {@link matsu.num.statistics.random.FDistributionRnd.Factory} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
abstract class SkeletalFDistributionRndFactory implements FDistributionRnd.Factory {

    /**
     * 唯一のコンストラクタ.
     */
    protected SkeletalFDistributionRndFactory() {
        super();
    }

    @Override
    public final FDistributionRnd instanceOf(double d1, double d2) {
        if (!FDistributionRnd.acceptsParameters(d1, d2)) {
            throw new IllegalArgumentException(
                    String.format(
                            "パラメータ不正:d1 = %s, d2 = %s", d1, d2));
        }

        return this.createInstanceOf(d1, d2);
    }

    /**
     * {@link #instanceOf(double, double)} で返すインスタンスを生成するための抽象メソッド.
     * 
     * <p>
     * このメソッドは {@link #instanceOf(double,double)} の内部で呼ばれるために用意されており,
     * 外部から呼ぶことは許されず, 継承先でアクセス修飾子を緩めてはいけない. <br>
     * 内部から呼ばれる場合, 引数は必ず正当である.
     * </p>
     * 
     * @param d1 分子自由度
     * @param d2 分母自由度
     * @return 自由度が (<i>d</i><sub>1</sub>, <i>d</i><sub>2</sub>)
     *             のF分布乱数発生器インスタンス
     */
    protected abstract FDistributionRnd createInstanceOf(double d1, double d2);

    @Override
    public String toString() {
        return "FDistRnd.Factory";
    }
}
