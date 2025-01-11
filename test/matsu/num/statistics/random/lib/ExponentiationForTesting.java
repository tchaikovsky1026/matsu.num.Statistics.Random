/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.lib;

import org.junit.Ignore;

/**
 * テストクラスで使用する {@link matsu.num.statistics.random.lib.Exponentiation}.
 */
@Ignore
public final class ExponentiationForTesting implements Exponentiation {

    public static final Exponentiation INSTANCE = new ExponentiationForTesting();

    private ExponentiationForTesting() {
        super();
    }

    @Override
    public double exp(double x) {
        return Math.exp(x);
    }

    @Override
    public double expm1(double x) {
        return Math.expm1(x);
    }

    @Override
    public double log(double x) {
        return Math.log(x);
    }

    @Override
    public double log1p(double x) {
        return Math.log1p(x);
    }

    @Override
    public double sqrt(double x) {
        return Math.sqrt(x);
    }

    @Override
    public String toString() {
        return "Exponentiation(Math)";
    }
}
