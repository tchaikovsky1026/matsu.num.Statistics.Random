/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.9
 */
package matsu.num.statistics.random.service;

import matsu.num.statistics.random.lib.Exponentiation;

/**
 * {@link CommonLib} のデフォルトライブラリとして使用される
 * {@link Exponentiation} .
 * 
 * @author Matsuura Y.
 */
final class ExponentiationDefault implements Exponentiation {

    ExponentiationDefault() {
        super();
    }

    @Override
    public String toString() {
        return "Exponentiation(Math)";
    }
}
