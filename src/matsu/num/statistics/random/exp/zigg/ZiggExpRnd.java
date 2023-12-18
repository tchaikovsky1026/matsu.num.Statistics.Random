/**
 * 2023.3.24
 */
package matsu.num.statistics.random.exp.zigg;

import java.util.Objects;
import java.util.Random;

import matsu.num.commons.Exponentiation;
import matsu.num.statistics.random.ExponentialRnd;

/**
 * Ziggurat法により実装された, 標準指数分布に従う乱数発生器. 
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public final class ZiggExpRnd implements ExponentialRnd {

    private static final ExponentialRnd INSTANCE = new ZiggExpRnd();

    private static final int N = 128;
    private static final double R_N = 6.89831511661564d;
    private static final double V_N = 0.00797322953955351d;

    private final double[] xi;
    private final double[] fi;

    private ZiggExpRnd() {
        if (Objects.nonNull(INSTANCE)) {
            throw new AssertionError();
        }

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
    public double nextRandom(Random random) {

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

    private double func(double x) {
        return Exponentiation.exp(-x);
    }

    private double funcInv(double x) {
        return -1.0 * Exponentiation.log(x);
    }

    private double tail(Random random) {
        return R_N - Exponentiation.log(random.nextDouble());
    }

    /**
     * Ziggurat法により実装された標準指数分布乱数発生器インスタンスを返す.
     *
     * @return 標準指数分布乱数発生器インスタンス
     */
    public static ExponentialRnd instance() {
        return INSTANCE;
    }
}
