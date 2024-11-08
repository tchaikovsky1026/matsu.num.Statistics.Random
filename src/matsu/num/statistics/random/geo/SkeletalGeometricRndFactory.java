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

/**
 * {@link GeometricRndSealed.FactorySealed} の骨格実装
 * 
 * @author Matsuura Y.
 * @version 22.1
 */
abstract class SkeletalGeometricRndFactory implements GeometricRndSealed.FactorySealed {

    /**
     * 唯一のコンストラクタ.
     */
    protected SkeletalGeometricRndFactory() {
        super();
    }

    @Override
    public final GeometricRndSealed instanceOf(double p) {
        if (!matsu.num.statistics.random.GeometricRnd.acceptsParameter(p)) {
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
    protected abstract GeometricRndSealed createInstanceOf(double p);

    @Override
    public String toString() {
        return "GeometricRnd.Factory";
    }
}
