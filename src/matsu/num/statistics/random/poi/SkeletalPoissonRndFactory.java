/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.poi;

/**
 * {@link PoissonRndSealed.FactorySealed} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 22.1
 */
abstract class SkeletalPoissonRndFactory implements PoissonRndSealed.FactorySealed {

    /**
     * 唯一のコンストラクタ.
     */
    protected SkeletalPoissonRndFactory() {
        super();
    }

    @Override
    public final PoissonRndSealed instanceOf(double lambda) {
        if (!matsu.num.statistics.random.PoissonRnd.acceptsParameter(lambda)) {
            throw new IllegalArgumentException(String.format("パラメータ不正:lambda=%s", lambda));
        }

        return this.createInstanceOf(lambda);
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
     * @param lambda パラメータ
     * @return パラメータが <i>&lambda;</i> のPoisson分布乱数発生器インスタンス
     */
    protected abstract PoissonRndSealed createInstanceOf(double lambda);

    @Override
    public String toString() {
        return "PoissonRnd.Factory";
    }
}
