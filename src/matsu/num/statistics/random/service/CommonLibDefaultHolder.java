/**
 * 2024.1.14
 */
package matsu.num.statistics.random.service;

/**
 * {@link CommonLib} のデフォルト実装のホルダ.
 * 
 * @author Matsuura Y.
 * @version 18.0
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
