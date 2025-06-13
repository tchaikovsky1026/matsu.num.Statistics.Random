/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.13
 */
package matsu.num.statistics.random.cauchy;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Ziggurat法により実装された, 標準Cauchy分布に従う乱数発生器.
 * 
 * <p>
 * 1 / (1 + <i>x</i><sup><i>2</i></sup>)
 * に対してZigguratを適用する.
 * </p>
 * 
 * @author Matsuura Y.
 */
public final class ZiggCauchyRnd extends SkeletalCauchyRnd {

    private static final int N = 128;
    private static final double R_N = 158.474291831405d;
    private static final double V_N = 0.0126200085059016d;

    private final double[] xi;
    private final double[] fi;

    private final Exponentiation exponentiation;

    private ZiggCauchyRnd(Exponentiation exponentiation) {
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

    /**
     * 確率密度関数相当. <br>
     * 1/(1 + x^2)
     * 
     * @param x
     * @return
     */
    private double func(double x) {
        return 1 / (1 + x * x);
    }

    /**
     * {@literal x >= 0}における確率密度関数の逆関数. <br>
     * sqrt(-2log(f))
     * 
     * @param f
     * @return
     */
    private double funcInv(double f) {
        return exponentiation.sqrt(1d / f - 1);
    }

    @Override
    public double nextRandom(BaseRandom random) {
        while (true) {
            int int32 = random.nextInt();
            boolean bSign = (int32 & 1) == 1;
            int iArea = (int32 >>> 1) & (N - 1);

            double x = xi[iArea + 1] * random.nextDouble();
            if (x < xi[iArea]) {
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
     * tailは,
     * 1/x^2を提案分布(逆関数法)とする棄却法を用いる.
     */
    private double tail(BaseRandom random) {
        while (true) {
            double u = random.nextDouble();
            if (random.nextDouble() * (R_N + u) < R_N) {
                return exponentiation.sqrt(R_N / u);
            }
        }
    }

    /**
     * {@link matsu.num.statistics.random.CauchyRnd.Factory} を生成する.
     * 
     * @param exponentiation 指数関数計算器
     * @return Cauchy乱数のファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static CauchyRnd.Factory createFactory(Exponentiation exponentiation) {
        return new LazyCauchyRndFactory(
                () -> new ZiggCauchyRnd(exponentiation));
    }
}
