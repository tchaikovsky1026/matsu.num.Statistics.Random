/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.exp;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * Ziggurat法による標準指数乱数のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
public final class ZiggExponentialRndFactory implements ExponentialRnd.Factory {

    private final ExponentialRnd expRnd;

    public ZiggExponentialRndFactory(Exponentiation exponentiation) {
        super();

        /* ExponentialRndは状態を持たないのでここで生成. */
        this.expRnd = new ZiggExponentialRnd(exponentiation);
    }

    @Override
    public ExponentialRnd instance() {
        return this.expRnd;
    }

    @Override
    public String toString() {
        return "ExponentialRnd.Factory";
    }

}
