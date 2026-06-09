/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.6.8
 */
package matsu.num.statistics.random.yulesimon;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.UnexpectedRandomGenerationException;
import matsu.num.statistics.random.YuleSimonRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * <p>
 * 指数分布と幾何分布の合成による Yule-Simmon 乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * パラメータ r による Yule-Simon 乱数は, <br>
 * Y を標準指数乱数, p = exp(-Y/r),
 * Z を成功確率 p の幾何乱数 <br>
 * として, Zを返す.
 * </p>
 * 
 * @author Matsuura Y.
 */
public final class ExpGeometricBasedYuleSimonRnd extends SkeletalYuleSimonRnd {

    private final double invRho;

    private final Exponentiation exponentiation;

    /**
     * 唯一の非公開コンストラクタ.
     * 引数のバリデーションは行われていない.
     */
    private ExpGeometricBasedYuleSimonRnd(double rho, Exponentiation exponentiation) {
        super(rho);

        this.exponentiation = exponentiation;

        this.invRho = 1d / rho;
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

            double y_invRho = random.nextExponential() * invRho;
            double invC = y_invRho < 1
                    ? -exponentiation.log(-exponentiation.expm1(-y_invRho))
                    : -exponentiation.log1p(-exponentiation.exp(-y_invRho));

            // intMax以上の値をキャストした場合, out = int.MIN となる
            out = 1 + (int) (random.nextExponential() / invC);
        } while (out <= 0);
        return out;
    }

    /**
     * {@link matsu.num.statistics.random.YuleSimonRnd.Factory} を生成する.
     * 
     * @param exponentiation 指数関数の計算
     * @return Yule-Simon 乱数のファクトリ
     * @throws NullPointerException 引数がnullの場合
     */
    public static YuleSimonRnd.Factory createFactory(
            Exponentiation exponentiation) {
        return new Factory(
                Objects.requireNonNull(exponentiation));
    }

    /**
     * 非公開のファクトリクラス. <br>
     * staticメソッドから呼ばれる.
     */
    private static final class Factory extends SkeletalYuleSimonRnd.Factory {

        private final Exponentiation exponentiation;

        /**
         * 唯一の非公開コンストラクタ.
         * 引数のバリデーションは行われていない.
         */
        Factory(Exponentiation exponentiation) {
            super();
            this.exponentiation = exponentiation;
        }

        @Override
        YuleSimonRnd createInstanceOf(double rho) {
            return new ExpGeometricBasedYuleSimonRnd(rho, exponentiation);
        }
    }
}
