/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.7
 */
package matsu.num.statistics.random.arcsin;

import java.util.Objects;

import matsu.num.statistics.random.ArcsineRnd;
import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * <p>
 * Ziggurat 法による逆正弦乱数発生器.
 * </p>
 * 
 * <p>
 * 逆正弦分布の確率密度関数が x = 1/2 で対称なので,
 * y を {@literal 0 <= y <= 1/2} で発生させ,
 * y または (1-y) を50 %で返すようにする. <br>
 * y = (1-w)<sup>2</sup> / 2 として確率密度関数を変換し, 定数倍すれば, <br>
 * f(w) = 1 / sqrt(2 - (1-w)<sup>2</sup>)
 * &emsp;
 * {@literal (0 < w < 1)} <br>
 * となる. <br>
 * このクラスでは, Ziggurat 法により w を発生させ y を計算,
 * x に直して出力する.
 * </p>
 * 
 * @author Matsuura Y.
 */
public final class ZigguratArcsineRnd extends SkeletalArcsineRnd {

    private static final double HALF_SQRT_2 = Math.sqrt(2) * 0.5;

    private static final int N = 128;
    private static final double V_N = 0.006214777251306432d;

    private final double[] wi;
    private final double[] fi;

    private final Exponentiation exponentiation;

    /**
     * 非公開の唯一のコンストラクタ.
     * 
     * @throws NullPointerException null
     */
    private ZigguratArcsineRnd(Exponentiation exponentiation) {
        super();

        this.exponentiation = Objects.requireNonNull(exponentiation);

        wi = new double[N + 1];
        fi = new double[N + 1]; //配列サイズが2^n個にならないよう、無駄スペースあり
        wi[N] = 1d;
        wi[N - 1] = 1d;
        fi[N - 1] = V_N;
        for (int i = N - 2; i >= 1; i--) {
            fi[i] = fi[i + 1] + V_N / wi[i + 1];
            wi[i] = invF(fi[i]);
        }
        wi[0] = 0d;
        fi[0] = 1d;
    }

    /**
     * f は0から1でなければならない.
     */
    private static double invF(double f) {
        if (f <= HALF_SQRT_2) {
            return 1d;
        }

        double out = 1d - Math.sqrt(2d - 1 / (f * f));
        return Double.isNaN(out) ? 1d : out;
    }

    /**
     * x は0から1でなければならない.
     */
    private double f(double w) {
        double u = 1 - w;
        return 1d / exponentiation.sqrt(2 - u * u);
    }

    @Override
    public double nextRandom(BaseRandom random) {

        /*
         * 通常の Ziggurat 法とは少し異なる.
         * 有限の台であるので, 領域No. (N - 1) についても, 他の領域と同様の条件分岐とする.
         * (ただし, その領域は採択率 100 %である.)
         */

        while (true) {
            int int32 = random.nextInt();
            boolean bSign = (int32 & 1) == 1;
            int iArea = (int32 >>> 1) & (N - 1);

            double w = wi[iArea + 1] * random.nextDouble();
            if (w <= wi[iArea] ||
                    random.nextDouble() * (fi[iArea] - fi[iArea + 1]) <= f(w) - fi[iArea + 1]) {

                double u = 1d - w;
                double y = 0.5 * u * u;
                return bSign
                        ? y
                        : 1 - y;
            }
        }
    }

    /**
     * {@link matsu.num.statistics.random.ArcsineRnd.Factory} を生成する.
     * 
     * @param exponentiation 指数関数の計算
     * @return 逆正弦乱数のファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static ArcsineRnd.Factory createFactory(Exponentiation exponentiation) {
        return new LazyArcsineRndFactory(
                () -> new ZigguratArcsineRnd(exponentiation));
    }
}
