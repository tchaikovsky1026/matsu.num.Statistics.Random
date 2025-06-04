/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.4
 */
package matsu.num.statistics.random.exp;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * long型で代替したZiggurat法により実装された, 標準指数分布に従う乱数発生器.
 * 
 * <p>
 * 確率密度関数である, exp(-x)に対してZigguratを適用する.
 * </p>
 *
 * @author Matsuura Y.
 */
public final class LongSubstitutedZiggExponentialRnd extends SkeletalExponentialRnd {

    private static final int N_BIT = 7;
    private static final int N = 1 << N_BIT;
    private static final double R_N = 6.89831511661564d;
    private static final double V_N = 0.00797322953955351d;

    private static final double LONG_TO_DOUBLE_COEFF = 1d / (1L << (64 - N_BIT));

    private final double[] xi;
    private final double[] fi;

    private final Exponentiation exponentiation;

    /**
     * 唯一のコンストラクタ.
     * 
     * @throws NullPointerException null
     */
    private LongSubstitutedZiggExponentialRnd(Exponentiation exponentiation) {
        super();
        this.exponentiation = Objects.requireNonNull(exponentiation);

        xi = new double[N + 1];
        fi = new double[N + 1]; //配列サイズが2^n個にならないよう、無駄スペースあり
        xi[N] = V_N / func(R_N);
        xi[N - 1] = R_N;
        fi[N - 1] = func(xi[N - 1]);
        for (int i = N - 2; i >= 1; i--) {
            xi[i] = funcInv(fi[i + 1] + V_N / xi[i + 1]);
            fi[i] = func(xi[i]);
        }
        xi[0] = 0.0;
        fi[0] = func(xi[0]);
    }

    @Override
    public double nextRandom(BaseRandom random) {

        double x0 = 0d;

        while (true) {
            /*
             * 処理のメインとなる randomDouble を long で代用する.
             * u がdouble値に相当する.
             */
            long long64 = random.nextLong();
            int iArea = (int) (long64 & (N - 1));
            double u = LONG_TO_DOUBLE_COEFF * (long64 >>> N_BIT);

            double x = xi[iArea + 1] * u;
            if (x < xi[iArea]) {
                return x + x0;
            }
            if (iArea == N - 1) {
                x0 += R_N;
                continue;
            }
            if (random.nextDouble() * (fi[iArea] - fi[iArea + 1]) <= func(x) - fi[iArea + 1]) {
                return x + x0;
            }
        }
    }

    /**
     * 確率密度関数.
     */
    private double func(double x) {
        return exponentiation.exp(-x);
    }

    /**
     * 確率密度関数の逆数.
     */
    private double funcInv(double f) {
        return -exponentiation.log(f);
    }

    /**
     * {@link matsu.num.statistics.random.ExponentialRnd.Factory} を生成する.
     * 
     * @param exponentiation 指数関数計算器
     * @return 指数乱数のファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static ExponentialRnd.Factory createFactory(Exponentiation exponentiation) {
        return new ExponentialRndFactory(new LongSubstitutedZiggExponentialRnd(exponentiation));
    }
}
