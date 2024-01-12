/**
 * 2024.1.9
 */
package matsu.num.statistics.random.common;

/**
 * {@code matsu.num.Commons} ライブラリを用いた {@linkplain Exponentiation}.
 * 
 * @author Matsuura Y.
 * @version 17.5
 */
final class ExponentiationMyLibrary implements Exponentiation {

    ExponentiationMyLibrary() {
        super();
    }

    @Override
    public double exp(double x) {
        return matsu.num.commons.Exponentiation.exp(x);
    }

    @Override
    public double expm1(double x) {
        return matsu.num.commons.Exponentiation.expm1(x);
    }

    @Override
    public double log(double x) {
        return matsu.num.commons.Exponentiation.log(x);
    }

    @Override
    public double log1p(double x) {
        return matsu.num.commons.Exponentiation.log1p(x);
    }

    @Override
    public double sqrt(double x) {
        return matsu.num.commons.Exponentiation.sqrt(x);
    }

    @Override
    public String toString() {
        return "Exponentiation(matsu.num.Commons)";
    }
}
