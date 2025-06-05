/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.5
 */
package matsu.num.statistics.random.norm;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Ziggurat法により実装された標準正規分布に従う乱数発生器.
 *
 * @author Matsuura Y.
 */
public final class LongSubstitutedZiggNormalRnd extends SkeletalNormalRnd {

    private static final int N_BIT = 7;
    private static final int N = 1 << N_BIT;
    private static final double R_N = 3.44261985589665d;
    private static final double V_N = 0.00991256303533652d;

    // 符号に1個使うので, (64 - (N_BIT+1)) が使える
    private static final double LONG_TO_DOUBLE_COEFF = 1d / (1L << (64 - (N_BIT + 1)));

    /**
     * xi と (xi * LONG_TO_DOUBLE_COEFF) を扱う.
     */
    private final double[] xi_and_xiTimesCoef;
    private final double[] fi;
    private final ExponentialRnd expRnd;

    private final Exponentiation exponentiation;

    /**
     * 唯一のコンストラクタ.
     * 
     * @throws NullPointerException null
     */
    private LongSubstitutedZiggNormalRnd(Exponentiation exponentiation, ExponentialRnd.Factory exponentialRndFactory) {
        super();
        this.expRnd = exponentialRndFactory.instance();
        this.exponentiation = Objects.requireNonNull(exponentiation);

        xi_and_xiTimesCoef = new double[2 * N + 2];
        fi = new double[N + 1]; //配列サイズが2^n個にならないよう、無駄スペースあり

        setXi(N, V_N / func(R_N));
        setXi(N - 1, R_N);
        fi[N - 1] = func(getXi(N - 1));
        for (int i = N - 2; i >= 1; i--) {
            setXi(i, funcInv(fi[i + 1] + V_N / getXi(i + 1)));
            fi[i] = func(getXi(i));
        }
        setXi(0, 0.0);
        fi[0] = func(getXi(0));

        for (int i = 0; i <= N; i++) {
            calcAndSetXiTimesCoeff(i, getXi(i));
        }
    }

    @Override
    public double nextRandom(BaseRandom random) {
        while (true) {
            /*
             * 処理のメインとなる randomDouble を long で代用する.
             * u = LONG_TO_DOUBLE_COEFF * (long64 >>> (N_BIT+1))
             * がdouble値に相当する.
             */
            long long64 = random.nextLong();
            boolean bSign = (long64 & 1) == 1;
            int iArea = (int) ((long64 >>> 1) & (N - 1));

            double xiTimesCoeff_at_iAreaPlus1 = getXiTimesCoeff(iArea + 1);
            double xi_at_iArea = getXi(iArea);
            double x = xiTimesCoeff_at_iAreaPlus1 * (long64 >>> (N_BIT + 1));
            if (x < xi_at_iArea) {
                return bSign ? x : -x;
            }

            if (iArea == N - 1) {
                return bSign ? tail(random) : -tail(random);
            }
            if (random.nextDouble() * (fi[iArea] - fi[iArea + 1]) <= func(x) - fi[iArea + 1]) {
                return bSign ? x : -x;
            }
        }
    }

    /**
     * テールは指数分布による棄却法を用いる.
     */
    private double tail(BaseRandom random) {
        while (true) {
            double y = (1 / R_N) * this.expRnd.nextRandom(random);
            double ue = this.expRnd.nextRandom(random);
            if (ue > 0.5 * y * y) {
                return y + R_N;
            }
        }
    }

    /**
     * 確率密度関数.
     * 
     * @param x
     * @return
     */
    private double func(double x) {
        return exponentiation.exp(-x * x * 0.5);
    }

    /**
     * {@literal x >= 0}における確率密度関数の逆関数. <br>
     * sqrt(-2log(f))
     * 
     * @param f
     * @return
     */
    private double funcInv(double f) {
        return exponentiation.sqrt(-2.0 * Math.log(f));
    }

    private double getXi(int i) {
        return xi_and_xiTimesCoef[i << 1];
    }

    private double getXiTimesCoeff(int i) {
        return xi_and_xiTimesCoef[(i << 1) | 1];
    }

    private void setXi(int i, double xiValue) {
        xi_and_xiTimesCoef[i << 1] = xiValue;
    }

    private void calcAndSetXiTimesCoeff(int i, double xiValue) {
        xi_and_xiTimesCoef[(i << 1) | 1] = xiValue * LONG_TO_DOUBLE_COEFF;
    }

    /**
     * {@link matsu.num.statistics.random.NormalRnd.Factory} を生成する.
     * 
     * @param exponentiation 指数関数計算器
     * @param exponentialRndFactory 指数乱数発生器のファクトリ
     * @return 正規乱数のファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static NormalRnd.Factory createFactory(
            Exponentiation exponentiation, ExponentialRnd.Factory exponentialRndFactory) {
        return new NormalRndFactory(new LongSubstitutedZiggNormalRnd(exponentiation, exponentialRndFactory));
    }
}
