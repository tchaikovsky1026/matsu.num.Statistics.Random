/*
 * Copyright © 2026 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.6.6
 */
package matsu.num.statistics.random.inner;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.base.LazyParameterlessRndFactory;

/**
 * Dirichlet 分布乱数を使用した, 二項分布に従う乱数発生を扱う. <br>
 * Dirichlet 分布乱数は内部的にガンマ乱数を使用する.
 * 
 * @author Matsuura Y.
 */
public final class DirichletBasedStaticBinomialRnd implements InnerStaticBinomialRnd {

    /** 素朴な方法を採用する場合の, 試行回数の最大値 */
    private static final int MAX_TRIAL_BY_NAIVE_METHOD = 16;

    private final StaticGammaRnd staticGammaRnd;

    /**
     * 非公開コンストラクタ.
     * 
     * 引数チェックしていない.
     */
    private DirichletBasedStaticBinomialRnd(
            StaticGammaRnd.Factory staticGammaRndFactory) {
        super();
        this.staticGammaRnd = staticGammaRndFactory.instance();
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public int next(int n, double p, BaseRandom random) {
        Objects.requireNonNull(random);
        if (!InnerStaticBinomialRnd.acceptsParameters(n, p)) {
            throw new IllegalArgumentException(
                    String.format(
                            "Illegal parameter: n = %s, p = %s", n, p));
        }

        /*
         * n が小さいとき, naive な方法で乱数生成: randNaive.
         * n が大きいときは以下.
         * 
         * 0 <= m <= n-1 なる m を定める.
         * y: rand with Beta(m+1,n-m)
         * y>=p なら, return Bin(m,p/y)
         * y<p なら, return m + 1 + Bin(n-m-1,(p-y)/(1-y))
         */

        int shift = 0;
        // n, p, shift を更新しながら, 再帰的に区間減少を行う.
        while (true) {
            if (n <= MAX_TRIAL_BY_NAIVE_METHOD) {
                return shift + randNaive(n, p, random);
            }

            int m = n >> 1; // m approx n/2
            double z1 = staticGammaRnd.nextRandom(random, m + 1);
            double z2 = staticGammaRnd.nextRandom(random, n - m);
            double sum_z = z1 + z2;
            // 極端な場合はやり直し
            if (z1 < 1E-200 || z2 < 1E-200 || sum_z > 1E+200) {
                continue;
            }
            double zp = p * sum_z;

            if (z1 > zp) {
                n = m;
                p = zp / z1;
                continue;
            }
            double z1mp = (1 - p) * sum_z;
            if (z2 > z1mp) {
                shift += m + 1;
                n = n - m - 1;
                p = 1 - z1mp / z2;
                continue;
            }
        }
    }

    /** 素朴な方法により二項乱数を生成する. */
    private int randNaive(int n, double p, BaseRandom random) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (random.nextDouble() < p) {
                count++;
            }
        }
        return count;
    }

    /** このインスタンスの文字列表現を返す. */
    @Override
    public String toString() {
        return "InnerStaticBinomialRnd";
    }

    /**
     * {@link DirichletBasedStaticBinomialRnd} のファクトリを生成する.
     * 
     * @param staticGammaRndFactory staticガンマ乱数生成器のファクトリ
     * @return このクラスのファクトリ
     * @throws NullPointerException 引数に null を含む場合
     */
    public static InnerStaticBinomialRnd.Factory createFactory(
            StaticGammaRnd.Factory staticGammaRndFactory) {
        return new Factory(Objects.requireNonNull(staticGammaRndFactory));
    }

    /** 非公開ファクトリの実装. */
    private static final class Factory extends
            LazyParameterlessRndFactory<InnerStaticBinomialRnd>
            implements InnerStaticBinomialRnd.Factory {

        /**
         * 非公開コンストラクタ.
         * 
         * 引数チェックしていない.
         */
        Factory(StaticGammaRnd.Factory staticGammaRndFactory) {
            super(
                    () -> new DirichletBasedStaticBinomialRnd(staticGammaRndFactory),
                    "InnerStaticBinomialRnd.Factory");
        }
    }
}
