/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.8
 */
package matsu.num.statistics.random.service;

import matsu.num.statistics.random.lib.Exponentiation;

/**
 * {@link Math} ライブラリを用いた {@link Exponentiation}. <br>
 * {@link CommonLib} のデフォルトライブラリとして使用される.
 * 
 * @author Matsuura Y.
 */
final class ExponentiationMath implements Exponentiation {

    ExponentiationMath() {
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
