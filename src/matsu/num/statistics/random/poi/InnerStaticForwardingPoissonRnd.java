/*
 * Copyright © 2026 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.6.7
 */
package matsu.num.statistics.random.poi;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.PoissonRnd;
import matsu.num.statistics.random.inner.InnerStaticPoissonRnd;

/**
 * Inner Static Poisson 乱数生成器に転送を行う乱数発生を扱う.
 *
 * @author Matsuura Y.
 */
public final class InnerStaticForwardingPoissonRnd extends SkeletalPoissonRnd {

    private final InnerStaticPoissonRnd staticPoissonRnd;

    /**
     * 非公開の唯一のコンストラクタ. <br>
     * 引数チェックがされていないので, 公開してはならない.
     */
    private InnerStaticForwardingPoissonRnd(
            double lambda,
            InnerStaticPoissonRnd.Factory staticPoissonRndFactory) {
        super(lambda);

        this.staticPoissonRnd = staticPoissonRndFactory.instance();
    }

    @Override
    public int nextRandom(BaseRandom random) {
        return staticPoissonRnd.next(this.lambda, random);
    }

    /**
     * {@link InnerStaticPoissonRnd} に転送を行う二項乱数発生器のファクトリ.
     * 
     * @param staticPoissonRndFactory inner static Poisson 乱数発生器のファクトリ
     * @return 乱数生成器ファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static PoissonRnd.Factory createFactory(InnerStaticPoissonRnd.Factory staticPoissonRndFactory) {

        return new Factory(
                Objects.requireNonNull(staticPoissonRndFactory));
    }

    private static final class Factory extends SkeletalPoissonRnd.Factory {

        private final InnerStaticPoissonRnd.Factory staticPoissonRndFactory;

        Factory(InnerStaticPoissonRnd.Factory staticPoissonRndFactory) {
            super();

            this.staticPoissonRndFactory = staticPoissonRndFactory;
        }

        @Override
        PoissonRnd createInstanceOf(double lambda) {
            return new InnerStaticForwardingPoissonRnd(
                    lambda, staticPoissonRndFactory);
        }
    }
}
