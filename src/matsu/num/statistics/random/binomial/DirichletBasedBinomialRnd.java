/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.13
 */
package matsu.num.statistics.random.binomial;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BinomialRnd;
import matsu.num.statistics.random.GammaRnd;

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

    private final GammaRnd.Factory gammaRndFactory;

    /**
     * 形状パラメータが1, 2, 4, ... のガンマ乱数発生器. <br>
     * 最大は 2^20 = 1_048_576 (GAMMA_RND_BIT = 20)
     */
    private final GammaRnd[] gammaRnds_power2;

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

    /**
     * 指定したパラメータの二項分布乱数発生器インスタンスを構築する.
     */
    private DirichletBasedBinomialRnd(
            int n, double p,
            GammaRnd[] gammaRnds_power2, GammaRnd.Factory gammaRndFactory,
            NaiveBinomialRndHelper naiveBinomialRnd, Power2BinomialRndHelper power2BinomialRnd) {
        super(n, p);

        this.gammaRnds_power2 = gammaRnds_power2;
        this.gammaRndFactory = gammaRndFactory;
        this.naiveBinomialRnd = naiveBinomialRnd;
        this.power2BinomialRnd = power2BinomialRnd;

        this.m0 = n & M0_BIT_MASK;
        this.power2_a = Power2.expandBinary(n - this.m0);
        this.cumulative_power2_a_excludingSelf =
                calc_cumulative_power2_a_excludingSelf(this.power2_a);

    }

    /**
     * [0, 2^a1, 2^a1+2^a2, ...]
     * を計算する.
     */
    private static int[] calc_cumulative_power2_a_excludingSelf(int[] power2_a) {
        int[] out = new int[power2_a.length];
        int sum = 0;
        for (int i = 0; i < power2_a.length; i++) {
            out[i] = sum;
            sum += power2_a[i];
        }

        return out;
    }

    @Override
    public int nextRandom(BaseRandom random) {
        throw new AssertionError("TODO");
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

        /**
         * 形状パラメータが1, 2, 4, ... のガンマ乱数発生器.
         */
        private final GammaRnd[] gammaRnds_power2;

        private final NaiveBinomialRndHelper naiveBinomialRnd;
        private final Power2BinomialRndHelper power2BinomialRnd;

        Factory(GammaRnd.Factory gammaRndFactory) {
            super();

            this.gammaRndFactory = gammaRndFactory;

            this.gammaRnds_power2 = new GammaRnd[GAMMA_RND_BIT + 1];
            int k = 1;
            for (int i = 0; i < GAMMA_RND_BIT + 1; i++) {
                gammaRnds_power2[i] = gammaRndFactory.instanceOf(k);
                k = k * 2;
            }

            this.naiveBinomialRnd = new NaiveBinomialRndHelper();
            this.power2BinomialRnd = new Power2BinomialRndHelper(
                    THRESHOLD_M, naiveBinomialRnd, gammaRnds_power2);
        }

        @Override
        BinomialRnd createInstanceOf(int n, double p) {
            return new DirichletBasedBinomialRnd(
                    n, p,
                    gammaRnds_power2, gammaRndFactory,
                    naiveBinomialRnd, power2BinomialRnd);
        }
    }
}
