/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.exp;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Ziggurat法により実装された, 標準指数分布に従う乱数発生器.
 * 
 * <p>
 * 確率密度関数である, exp(-x)に対してZigguratを適用する.
 * </p>
 *
 * @author Matsuura Y.
 * @version 20.0
 */
final class ZiggExponentialRnd implements ExponentialRnd {

    private static final int N = 128;
    private static final double R_N = 6.89831511661564d;
    private static final double V_N = 0.00797322953955351d;

    private final double[] xi;
    private final double[] fi;

    private final Exponentiation exponentiation;

    ZiggExponentialRnd(Exponentiation exponentiation) {
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

        while (true) {
            int int32 = random.nextInt();
            int iArea = int32 & (N - 1);

            double x = xi[iArea + 1] * random.nextDouble();
            if (x < xi[iArea]) {
                return x;
            }
            if (iArea == N - 1) {
                return tail(random);
            }
            if (random.nextDouble() * (fi[iArea] - fi[iArea + 1]) <= func(x) - fi[iArea + 1]) {
                return x;
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
     * 確率五戸関数の逆数.
     */
    private double funcInv(double f) {
        return -exponentiation.log(f);
    }

    /**
     * tail部の乱数発生:逆関数法
     */
    private double tail(BaseRandom random) {
        return R_N - exponentiation.log(random.nextDouble());
    }

    @Override
    public String toString() {
        return "ExponentialRnd";
    }
}
