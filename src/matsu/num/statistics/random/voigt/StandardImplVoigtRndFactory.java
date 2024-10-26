/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.10.26
 */
package matsu.num.statistics.random.voigt;

import java.util.Objects;

import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.NormalRnd;

/**
 * VoigtRndのスタンダード実装のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 22.0
 */
public final class StandardImplVoigtRndFactory extends SkeletalVoigtRndFactory {

    private final NormalRnd.Factory normalRndFactory;
    private final CauchyRnd.Factory cauchyRndFactory;

    public StandardImplVoigtRndFactory(NormalRnd.Factory normalRndFactory, CauchyRnd.Factory cauchyRndFactory) {
        super();
        this.normalRndFactory = Objects.requireNonNull(normalRndFactory);
        this.cauchyRndFactory = Objects.requireNonNull(cauchyRndFactory);
    }

    @Override
    protected VoigtRnd createInstanceOf(double alpha) {
        return new StandardImplVoigtRnd(alpha, this.normalRndFactory, this.cauchyRndFactory);
    }
}
