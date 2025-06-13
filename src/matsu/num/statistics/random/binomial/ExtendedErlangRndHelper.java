/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.22
 */
package matsu.num.statistics.random.binomial;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GammaRnd;

/**
 * アーラン分布 (形状パラメータを正の整数に限定したガンマ分布) による乱数生成ヘルパ. <br>
 * k = 0 まで拡張されており,
 * k = 0 の場合は常に0を返す.
 * 
 * @author Matsuura Y.
 */
abstract class ExtendedErlangRndHelper {

    final int k;

    /**
     * 唯一の非公開コンストラクタ.
     */
    private ExtendedErlangRndHelper(int k) {
        super();
        if (k < 0) {
            throw new IllegalArgumentException("Illegal parameter: k < 0");
        }
        this.k = k;
    }

    abstract double next(BaseRandom baseRandom);

    /**
     * {@link ExtendedErlangRndHelper} インスタンスを取得するための唯一のメソッド. <br>
     * IllegalArgumentException が発生する可能性がある.
     * 
     * @param factory ガンマ乱数生成器のファクトリ
     */
    static ExtendedErlangRndHelper create(int k, GammaRnd.Factory factory) {
        if (k == 0) {
            return ZeroErlang.INSTACNE;
        }

        // ここで IllegalArgumentException が発生する可能性がある
        return new GammaBasedErlang(k, factory);
    }

    private static final class ZeroErlang extends ExtendedErlangRndHelper {

        static final ExtendedErlangRndHelper INSTACNE = new ZeroErlang();

        ZeroErlang() {
            super(0);
        }

        @Override
        double next(BaseRandom baseRandom) {
            return 0d;
        }
    }

    private static final class GammaBasedErlang extends ExtendedErlangRndHelper {

        private final GammaRnd gammaRnd;

        GammaBasedErlang(int k, GammaRnd.Factory factory) {
            super(k);
            this.gammaRnd = factory.instanceOf(k);
        }

        @Override
        double next(BaseRandom baseRandom) {
            return this.gammaRnd.nextRandom(baseRandom);
        }
    }
}
