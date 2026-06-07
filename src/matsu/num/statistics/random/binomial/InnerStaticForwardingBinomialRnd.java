/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.6.7
 */
package matsu.num.statistics.random.binomial;

import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BinomialRnd;
import matsu.num.statistics.random.inner.InnerStaticBinomialRnd;

/**
 * Inner Static 二項乱数生成器に転送を行う乱数発生を扱う.
 * 
 * @author Matsuura Y.
 */
public final class InnerStaticForwardingBinomialRnd extends SkeletalBinomialRnd {

    private final InnerStaticBinomialRnd staticBinomialRnd;

    /**
     * 指定したパラメータの二項分布乱数発生器インスタンスを構築する.
     */
    private InnerStaticForwardingBinomialRnd(
            int n, double p,
            InnerStaticBinomialRnd.Factory staticBinomialRndFactory) {
        super(n, p);
        this.staticBinomialRnd = staticBinomialRndFactory.instance();
    }

    @Override
    public int nextRandom(BaseRandom random) {
        return staticBinomialRnd.next(n, p, random);
    }

    /**
     * {@link InnerStaticBinomialRnd} に転送を行う二項乱数発生器のファクトリ.
     * 
     * @param innerStaticBinomialRndFactory inner static 二項乱数生成器ファクトリ
     * @return 二項乱数生成器ファクトリ
     * @throws NullPointerException 引数がnullの場合
     */
    public static BinomialRnd.Factory createFactory(InnerStaticBinomialRnd.Factory innerStaticBinomialRndFactory) {
        //ここでNPExの可能性
        return new Factory(
                Objects.requireNonNull(innerStaticBinomialRndFactory));
    }

    private static final class Factory extends SkeletalBinomialRnd.Factory {

        private final InnerStaticBinomialRnd.Factory staticBinomialRndFactory;

        Factory(InnerStaticBinomialRnd.Factory staticBinomialRndFactory) {
            super();
            this.staticBinomialRndFactory = staticBinomialRndFactory;
        }

        @Override
        BinomialRnd createInstanceOf(int n, double p) {
            return new InnerStaticForwardingBinomialRnd(
                    n, p,
                    staticBinomialRndFactory);
        }
    }
}
