/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.gamma;

import matsu.num.statistics.random.GammaRnd;

/**
 * {@link matsu.num.statistics.random.GammaRnd.Factory} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
abstract class SkeletalGammRndFactory implements GammaRnd.Factory {

    /**
     * 唯一のコンストラクタ.
     */
    protected SkeletalGammRndFactory() {
        super();
    }

    @Override
    public final GammaRnd instanceOf(double k) {
        if (!GammaRnd.acceptsParameter(k)) {
            throw new IllegalArgumentException(String.format("パラメータ不正:k=%s", k));
        }

        return this.createInstanceOf(k);
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
     * @param k 形状パラメータ
     * @return 形状パラメータが <i>k</i> のガンマ分布乱数発生器インスタンス
     */
    protected abstract GammaRnd createInstanceOf(double k);

    @Override
    public String toString() {
        return "GammaRnd.Factory";
    }
}
