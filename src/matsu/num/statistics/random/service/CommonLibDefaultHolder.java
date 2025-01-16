/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.service;

import matsu.num.statistics.random.lib.Exponentiation;

/**
 * {@link CommonLib} のデフォルト実装のホルダ.
 * 
 * @author Matsuura Y.
 */
final class CommonLibDefaultHolder {

    static final CommonLib DEFAULT_INSTANCE = new CommonLibImpl();

    private CommonLibDefaultHolder() {
        throw new AssertionError();
    }

    private static final class CommonLibImpl extends CommonLib {

        private final Exponentiation exponentiation;

        CommonLibImpl() {
            super();
            this.exponentiation = new ExponentiationMath();
        }

        @Override
        public Exponentiation exponentiation() {
            return this.exponentiation;
        }

        @Override
        public String toString() {
            return "CommonLib(default)";
        }

    }
}
