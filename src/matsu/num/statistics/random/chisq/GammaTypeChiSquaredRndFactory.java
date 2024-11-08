/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random.chisq;

import java.util.Objects;

import matsu.num.statistics.random.GammaRnd;

/**
 * このパッケージに用意された {@link ChiSquaredRndSealed} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 22.1
 */
public final class GammaTypeChiSquaredRndFactory extends SkeletalChiSquaredRndFactory {

    private final GammaRnd.Factory gammaRndFactory;

    public GammaTypeChiSquaredRndFactory(GammaRnd.Factory gammaRndFactory) {
        this.gammaRndFactory = Objects.requireNonNull(gammaRndFactory);
    }

    @Override
    protected ChiSquaredRndSealed createInstanceOf(double k) {
        return new GammaTypeChiSquaredRnd(k, this.gammaRndFactory);
    }
}
