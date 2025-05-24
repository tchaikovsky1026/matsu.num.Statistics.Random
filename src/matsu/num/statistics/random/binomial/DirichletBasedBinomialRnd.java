/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.23
 */
package matsu.num.statistics.random.binomial;

import java.util.Arrays;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BinomialRnd;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.util.GammaRndPower2Storage;

/**
 * Dirichlet 分布乱数を使用した, 二項分布に従う乱数発生を扱う. <br>
 * Dirichlet 分布乱数は内部的にガンマ乱数を使用する.
 * 
 * @author Matsuura Y.
 */
public final class DirichletBasedBinomialRnd extends SkeletalBinomialRnd {

    /**
     * 網羅できるための, ガンマ乱数の形状パラメータのビット数. <br>
     * 1,2,4,...,2^20までで, 20.
     */
    private static final int GAMMA_RND_BIT = 20;

    /**
     * 二分法と素朴を切り替える閾値をMとしたときの, log_2 M
     */
    private static final int THRESHOLD_LOG2_M = 4;

    /**
     * 二分法と素朴を切り替える閾値: M, これは2の累乗の値.
     */
    private static final int THRESHOLD_M = 1 << THRESHOLD_LOG2_M;

    /**
     * mod M を計算するためのビットマスク. <br>
     * すなわち, M - 1
     */
    private static final int M0_BIT_MASK = THRESHOLD_M - 1;

    private final NaiveBinomialRndHelper naiveBinomialRnd;
    private final Power2BinomialRndHelper power2BinomialRnd;

    /*
     * n + 1 を2進展開したときの,
     * n + 1 = m0 + 2^a1 + 2^a2 + ...
     * 配列が1スタートなので注意.
     * 
     * cumulative配列は, 自身の前段までの和
     */
    private final int m0;
    private final int[] power2_a;
    private final int[] cumulative_power2_a_excludingSelf;
    private final GammaRnd[] gammaRnds_power2_a;
    private final ExtendedErlangRndHelper m0RndHelper;

    /**
     * 指定したパラメータの二項分布乱数発生器インスタンスを構築する.
     */
    private DirichletBasedBinomialRnd(
            int n, double p,
            GammaRndPower2Storage gammaRndPower2Storage, GammaRnd.Factory gammaRndFactory,
            NaiveBinomialRndHelper naiveBinomialRnd, Power2BinomialRndHelper power2BinomialRnd) {
        super(n, p);

        this.naiveBinomialRnd = naiveBinomialRnd;
        this.power2BinomialRnd = power2BinomialRnd;

        this.m0 = (n + 1) & M0_BIT_MASK;
        this.power2_a = Power2.expandBinary(n + 1 - this.m0);
        this.gammaRnds_power2_a = Arrays.stream(this.power2_a)
                .map(Power2::floorLog2)
                .mapToObj(a -> gammaRndPower2Storage.getAt(a))
                .toArray(GammaRnd[]::new);
        this.cumulative_power2_a_excludingSelf =
                calc_intCumulative_excludingSelf(this.power2_a);
        this.m0RndHelper = ExtendedErlangRndHelper.create(m0, gammaRndFactory);
    }

    /**
     * [0, x0, x0+x1, ...]
     * を計算する.
     */
    private static int[] calc_intCumulative_excludingSelf(int[] x) {
        int[] out = new int[x.length];
        int sum = 0;
        for (int i = 0; i < x.length; i++) {
            out[i] = sum;
            sum += x[i];
        }

        return out;
    }

    /**
     * [x0, x0+x1, ...]
     * を計算する.
     */
    private static double[] calc_doubleCumulative(double[] x) {
        double[] out = new double[x.length];
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i];
            out[i] = sum;
        }

        return out;
    }

    @Override
    public int nextRandom(BaseRandom random) {

        // Dirichlet分布が必要ない場合は省略する
        if (this.gammaRnds_power2_a.length == 0) {
            return this.naiveBinomialRnd.next(m0 - 1, p, random);
        }

        double[] u = new double[this.gammaRnds_power2_a.length + 1];
        u[0] = this.m0RndHelper.next(random) + Double.MIN_NORMAL;
        for (int k = 1; k < u.length; k++) {
            u[k] = this.gammaRnds_power2_a[k - 1].nextRandom(random) + Double.MIN_NORMAL;
        }

        double[] cumulativeU = calc_doubleCumulative(u);
        double y = cumulativeU[cumulativeU.length - 1] * p;

        int k;
        for (k = 0; k < cumulativeU.length; k++) {
            if (cumulativeU[k] >= y) {
                break;
            }
        }

        return k == 0
                ? this.naiveBinomialRnd.next(m0 - 1, y / u[0], random)
                : m0 + this.cumulative_power2_a_excludingSelf[k - 1]
                        + this.power2BinomialRnd.next(
                                this.power2_a[k - 1] - 1,
                                (y - cumulativeU[k - 1]) / u[k],
                                random);
    }

    /**
     * Dirichlet 分布乱数に基づく二項乱数発生器のファクトリ.
     * 
     * @param gammaRndFactory ガンマ乱数生成器ファクトリ
     * @return 二項乱数生成器ファクトリ
     * @throws NullPointerException 引数がnullの場合
     */
    public static BinomialRnd.Factory createFactory(GammaRnd.Factory gammaRndFactory) {
        //ここでNPExの可能性
        return new Factory(gammaRndFactory);
    }

    private static final class Factory extends SkeletalBinomialRnd.Factory {

        private final GammaRnd.Factory gammaRndFactory;

        private final GammaRndPower2Storage gammaRndPower2Storage;

        private final NaiveBinomialRndHelper naiveBinomialRnd;
        private final Power2BinomialRndHelper power2BinomialRnd;

        Factory(GammaRnd.Factory gammaRndFactory) {
            super();

            this.gammaRndFactory = gammaRndFactory;

            this.gammaRndPower2Storage = GammaRndPower2Storage.create(
                    GAMMA_RND_BIT + 1, gammaRndFactory);

            this.naiveBinomialRnd = new NaiveBinomialRndHelper();
            this.power2BinomialRnd = new Power2BinomialRndHelper(
                    THRESHOLD_M, naiveBinomialRnd, gammaRndPower2Storage);
        }

        @Override
        BinomialRnd createInstanceOf(int n, double p) {
            return new DirichletBasedBinomialRnd(
                    n, p,
                    gammaRndPower2Storage, gammaRndFactory,
                    naiveBinomialRnd, power2BinomialRnd);
        }
    }
}
