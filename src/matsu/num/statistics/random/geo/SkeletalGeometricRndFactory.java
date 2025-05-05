/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random.geo;

import matsu.num.statistics.random.GeometricRnd;

/**
 * {@link matsu.num.statistics.random.GeometricRnd.Factory} の骨格実装
 * 
 * @author Matsuura Y.
 */
public abstract class SkeletalGeometricRndFactory implements GeometricRnd.Factory {

    /**
     * 唯一のコンストラクタ.
     */
    SkeletalGeometricRndFactory() {
        super();
    }

    @Override
    public final GeometricRnd instanceOf(double p) {
        if (!GeometricRnd.acceptsParameter(p)) {
            throw new IllegalArgumentException(String.format("パラメータ不正:p=%s", p));
        }

        return this.createInstanceOf(p);
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
     * @param p 成功確率
     * @return 成功確率がpの幾何分布乱数発生器インスタンス
     */
    abstract GeometricRnd createInstanceOf(double p);

    @Override
    public String toString() {
        return "GeometricRnd.Factory";
    }
}
