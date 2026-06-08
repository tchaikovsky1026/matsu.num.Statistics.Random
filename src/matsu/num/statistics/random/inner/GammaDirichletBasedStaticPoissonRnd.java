/*
 * Copyright © 2026 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.6.8
 */
package matsu.num.statistics.random.inner;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.UnexpectedRandomGenerationException;
import matsu.num.statistics.random.base.LazyParameterlessRndFactory;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Gamma and Dirichlet 分布乱数を使用した, Poisson 分布に従う乱数発生を扱う. <br>
 * Dirichlet 分布乱数は内部的にガンマ乱数を使用する.
 * 
 * @author Matsuura Y.
 */
public final class GammaDirichletBasedStaticPoissonRnd implements InnerStaticPoissonRnd {

    /** Basic な方法を採用する場合の, 試行回数の最大値 */
    private static final double MAX_TRIAL_BY_BASIC_METHOD = 16d;

    private final Exponentiation exponentiation;
    private final StaticGammaRnd staticGammaRnd;
    private final InnerStaticBinomialRnd staticBinomialRnd;

    /**
     * 非公開コンストラクタ.
     * 
     * 引数チェックしていない.
     */
    private GammaDirichletBasedStaticPoissonRnd(
            Exponentiation exponentiation,
            StaticGammaRnd.Factory staticGammaRndFactory,
            InnerStaticBinomialRnd.Factory staticBinomialRndFactory) {
        super();
        this.exponentiation = exponentiation;
        this.staticGammaRnd = staticGammaRndFactory.instance();
        this.staticBinomialRnd = staticBinomialRndFactory.instance();
    }

    @Override
    public int next(final double lambda, BaseRandom random) {
        Objects.requireNonNull(random);
        if (!InnerStaticPoissonRnd.acceptsParameter(lambda)) {
            throw new IllegalArgumentException(
                    String.format(
                            "Illegal parameter: lambda = %s", lambda));
        }

        // 乱数生成異常を検知するためのiterationCount
        int iteCount = 0;
        outer: while (true) {

            /*
             * lambda が小さいとき, basic な方法で乱数生成: randBasic.
             * lambda が大きいときは以下.
             * 
             * m >= 1 なる整数 m を定める.
             * z: rand with sGamma(m)
             * z > lambda なら, return Bin(m-1,lambda/z)
             * z <= lambda なら, m + Poi(lambda-z)
             */

            double current_lambda = lambda;
            int shift = 0;
            // current_lambda, shift を更新しながら, 再帰的に区間減少を行う.
            while (true) {
                iteCount++;
                if (iteCount >= Integer.MAX_VALUE) {
                    // 乱数生成の異常
                    throw new UnexpectedRandomGenerationException();
                }

                if (current_lambda <= MAX_TRIAL_BY_BASIC_METHOD) {
                    int out = shift + randBasic(current_lambda, random);

                    // 確率的にまず到達しないが, オーバーフロー対策
                    if (out < 0) {
                        continue outer;
                    }

                    return out;
                }

                int m = ((int) current_lambda) >> 1; // m approx lambda/2
                double z = staticGammaRnd.nextRandom(random, m);
                // 極端な場合はやり直し
                if (z < 1E-200 || z > 1E+200) {
                    continue;
                }
                if (z > current_lambda) {
                    return shift + staticBinomialRnd.next(m - 1, current_lambda / z, random);
                }

                // z <= lambda
                shift += m;
                current_lambda -= z;

                // 確率的にまず到達しないが, オーバーフロー対策
                if (shift < 0) {
                    continue outer;
                }
            }
        }
    }

    /** 素朴な方法により Poisson 乱数を生成する. */
    private int randBasic(double lambda, BaseRandom random) {

        /*
         * sUni に従う U0,U1,... を発生.
         * U0*U1*...*Uk <= exp(-lambda) となる最小の k を返す.
         */

        double exp_mLambda = exponentiation.exp(-lambda);

        // 乱数生成異常を検知するためのiterationCount
        int iteCount = 0;
        outer: while (true) {

            int k = 0;
            double v = 1d;
            while (true) {
                iteCount++;
                if (iteCount >= Integer.MAX_VALUE) {
                    // 乱数生成の異常
                    throw new UnexpectedRandomGenerationException();
                }

                v *= random.nextDouble();
                if (v <= exp_mLambda) {
                    return k;
                }
                k++;

                // 確率的にまず到達しないが, オーバーフロー対策
                if (k < 0) {
                    continue outer;
                }
            }
        }
    }

    /** このインスタンスの文字列表現を返す. */
    @Override
    public String toString() {
        return "InnerStaticPoissonRnd";
    }

    /**
     * {@link GammaDirichletBasedStaticPoissonRnd} のファクトリを生成する.
     * 
     * @param exponentiation 指数関数の計算
     * @param staticGammaRndFactory staticガンマ乱数生成器のファクトリ
     * @param staticBinomialRndFactory static二項乱数生成器のファクトリ
     * @return このクラスのファクトリ
     * @throws NullPointerException 引数に null を含む場合
     */
    public static InnerStaticPoissonRnd.Factory createFactory(
            Exponentiation exponentiation,
            StaticGammaRnd.Factory staticGammaRndFactory,
            InnerStaticBinomialRnd.Factory staticBinomialRndFactory) {
        return new Factory(
                Objects.requireNonNull(exponentiation),
                Objects.requireNonNull(staticGammaRndFactory),
                Objects.requireNonNull(staticBinomialRndFactory));
    }

    /** 非公開ファクトリの実装. */
    private static final class Factory extends
            LazyParameterlessRndFactory<InnerStaticPoissonRnd>
            implements InnerStaticPoissonRnd.Factory {

        /**
         * 非公開コンストラクタ.
         * 
         * 引数チェックしていない.
         */
        Factory(Exponentiation exponentiation,
                StaticGammaRnd.Factory staticGammaRndFactory,
                InnerStaticBinomialRnd.Factory staticBinomialRndFactory) {
            super(
                    () -> new GammaDirichletBasedStaticPoissonRnd(
                            exponentiation, staticGammaRndFactory, staticBinomialRndFactory),
                    "InnerStaticPoissonRnd.Factory");
        }
    }
}
