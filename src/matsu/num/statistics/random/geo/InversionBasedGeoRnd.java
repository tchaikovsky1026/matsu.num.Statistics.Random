/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.6.8
 */
package matsu.num.statistics.random.geo;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GeometricRnd;
import matsu.num.statistics.random.UnexpectedRandomGenerationException;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * 逆関数法に基づく, 幾何分布に従う乱数発生器を扱う.
 * 
 * @author Matsuura Y.
 */
public final class InversionBasedGeoRnd extends SkeletalGeometricRnd {

    private final double coeff;

    private InversionBasedGeoRnd(
            double p, Exponentiation exponentiation) {
        super(p);

        this.coeff = -1 / exponentiation.log1p(-p);
    }

    @Override
    public int nextRandom(BaseRandom random) {

        int out;

        // 乱数生成異常を検知するためのiterationCount
        int iteCount = 0;
        do {
            iteCount++;
            if (iteCount >= Integer.MAX_VALUE) {
                // 乱数生成の異常
                throw new UnexpectedRandomGenerationException();
            }

            // intMax以上の値をキャストした場合, out = int.MIN となる
            out = 1 + (int) (coeff * random.nextExponential());
        } while (out <= 0);
        return out;
    }

    /**
     * 逆関数法に基づく {@link GeometricRnd} のファクトリインスタンスを生成する.
     * 
     * @param exponentiation 指数関数の計算
     * @return 乱数生成器ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static GeometricRnd.Factory createFactory(Exponentiation exponentiation) {

        return new Factory(Objects.requireNonNull(exponentiation));
    }

    /**
     * 逆関数法に基づく, 幾何分布に従う乱数発生器のファクトリ.
     */
    private static final class Factory extends SkeletalGeometricRnd.Factory {

        private final Exponentiation exponentiation;

        Factory(Exponentiation exponentiation) {
            this.exponentiation = exponentiation;
        }

        @Override
        GeometricRnd createInstanceOf(double p) {
            return new InversionBasedGeoRnd(p, this.exponentiation);
        }
    }
}
