/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.24
 */
package matsu.num.statistics.random.poi;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.PoissonRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * ガンマ分布乱数発生器を利用した, Poisson分布に従う乱数発生器を扱う.
 *
 * @author Matsuura Y.
 */
public final class GammaHomoProcessBasedPoissonRnd extends SkeletalPoissonRnd {

    private final GammaHomoProcessBasedPoissonRndHelper poissonRndHelper;

    /**
     * 非公開の唯一のコンストラクタ. <br>
     * 引数チェックがされていないので, 公開してはならない.
     */
    private GammaHomoProcessBasedPoissonRnd(
            double lambda,
            GammaHomoProcessBasedPoissonRndHelper poissonRndHelper) {
        super(lambda);

        this.poissonRndHelper = poissonRndHelper;
    }

    @Override
    public int nextRandom(BaseRandom random) {
        return poissonRndHelper.next(this.lambda, random);
    }

    /**
     * ガンマ分布乱数発生器を利用した {@link PoissonRnd} のファクトリインスタンスを生成する.
     * 
     * @param exponentiation 指数関数の計算
     * @param gammaRndFactory ガンマ乱数生成器のファクトリ
     * @return 乱数生成器ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static PoissonRnd.Factory createFactory(
            Exponentiation exponentiation, GammaRnd.Factory gammaRndFactory) {

        return new Factory(
                Objects.requireNonNull(exponentiation),
                Objects.requireNonNull(gammaRndFactory));
    }

    private static final class Factory extends SkeletalPoissonRnd.Factory {

        private final GammaHomoProcessBasedPoissonRndHelper poissonRndHelper;

        Factory(Exponentiation exponentiation, GammaRnd.Factory gammaRndFactory) {
            super();

            this.poissonRndHelper =
                    new GammaHomoProcessBasedPoissonRndHelper(exponentiation, gammaRndFactory);
        }

        @Override
        PoissonRnd createInstanceOf(double lambda) {
            return new GammaHomoProcessBasedPoissonRnd(
                    lambda,
                    this.poissonRndHelper);
        }
    }
}
