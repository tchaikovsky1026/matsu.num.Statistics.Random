/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.voigt;

import java.util.Objects;

import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.VoigtRnd;

/**
 * VoigtRndのスタンダード実装のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
public final class StandardImplVoigtRndFactory implements VoigtRnd.Factory {

    private final NormalRnd.Factory normalRndFactory;
    private final CauchyRnd.Factory cauchyRndFactory;

    public StandardImplVoigtRndFactory(NormalRnd.Factory normalRndFactory, CauchyRnd.Factory cauchyRndFactory) {
        super();
        this.normalRndFactory = Objects.requireNonNull(normalRndFactory);
        this.cauchyRndFactory = Objects.requireNonNull(cauchyRndFactory);
    }

    @Override
    public boolean acceptsParameter(double alpha) {
        return VoigtRnd.LOWER_LIMIT_ALPHA <= alpha
                && alpha <= VoigtRnd.UPPER_LIMIT_ALPHA;
    }

    @Override
    public VoigtRnd instanceOf(double alpha) {
        if (!this.acceptsParameter(alpha)) {
            throw new IllegalArgumentException(String.format("alphaが不正:%s", alpha));
        }
        return new StandardImplVoigtRnd(alpha, this.normalRndFactory, this.cauchyRndFactory);
    }

    @Override
    public String toString() {
        return "VoigtRnd.Factory";
    }
}
