/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.voigt;

import matsu.num.statistics.random.VoigtRnd;

/**
 * {@link matsu.num.statistics.random.VoigtRnd.Factory} クラスの骨格実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
abstract class SkeletalVoigtRndFactory implements VoigtRnd.Factory {

    /**
     * 唯一のコンストラクタ.
     */
    protected SkeletalVoigtRndFactory() {
        super();
    }

    @Override
    public final VoigtRnd instanceOf(double alpha) {
        if (!VoigtRnd.acceptsParameter(alpha)) {
            throw new IllegalArgumentException(String.format("alphaが不正:%s", alpha));
        }

        return this.createInstanceOf(alpha);
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
     * @param alpha パラメータ
     * @return パラメータ <i>&alpha;</i> のVoigt分布乱数発生器
     */
    protected abstract VoigtRnd createInstanceOf(double alpha);

    @Override
    public String toString() {
        return "VoigtRnd.Factory";
    }
}
