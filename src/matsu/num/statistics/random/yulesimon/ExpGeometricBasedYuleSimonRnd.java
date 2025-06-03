/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.4
 */
package matsu.num.statistics.random.yulesimon;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;
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
    private final ExponentialRnd expRnd;

    /**
     * 唯一の非公開コンストラクタ.
     * 引数のバリデーションは行われていない.
     */
    private ExpGeometricBasedYuleSimonRnd(double rho,
            Exponentiation exponentiation, ExponentialRnd.Factory expRndFActory) {
        super(rho);

        this.exponentiation = exponentiation;
        this.expRnd = expRndFActory.instance();

        this.invRho = 1d / rho;
    }

    @Override
    public int nextRandom(BaseRandom random) {
        while (true) {
            double y = expRnd.nextRandom(random);
            double p = exponentiation.exp(-y * invRho);

            double w = expRnd.nextRandom(random) / (-exponentiation.log1p(-p));
            if (w < Integer.MAX_VALUE - 1) {
                return (int) w + 1;
            }
        }
    }

    /**
     * {@link matsu.num.statistics.random.YuleSimonRnd.Factory} を生成する.
     * 
     * @param exponentiation 指数関数の計算
     * @param exponentialRndFactory 指数乱数発生器のファクトリ
     * @return Yule-Simon 乱数のファクトリ
     * @throws NullPointerException 引数がnullの場合
     */
    public static YuleSimonRnd.Factory createFactory(
            Exponentiation exponentiation,
            ExponentialRnd.Factory exponentialRndFactory) {
        return new Factory(
                Objects.requireNonNull(exponentiation),
                Objects.requireNonNull(exponentialRndFactory));
    }

    /**
     * 非公開のファクトリクラス. <br>
     * staticメソッドから呼ばれる.
     */
    private static final class Factory extends SkeletalYuleSimonRnd.Factory {

        private final Exponentiation exponentiation;
        private final ExponentialRnd.Factory expRndFActory;

        /**
         * 唯一の非公開コンストラクタ.
         * 引数のバリデーションは行われていない.
         */
        Factory(Exponentiation exponentiation, ExponentialRnd.Factory expRndFActory) {
            super();
            this.exponentiation = exponentiation;
            this.expRndFActory = expRndFActory;
        }

        @Override
        YuleSimonRnd createInstanceOf(double rho) {
            return new ExpGeometricBasedYuleSimonRnd(rho, exponentiation, expRndFActory);
        }
    }
}
