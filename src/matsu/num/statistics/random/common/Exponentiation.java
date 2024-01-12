/**
 * 2024.1.9
 */
package matsu.num.statistics.random.common;

/**
 * 指数関数, 対数関数を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.5
 */
public interface Exponentiation {
    
    /**
     * exp(x) を返す.
     *
     * @param x x
     * @return exp(x)
     */
    public abstract double exp(double x);
    
    /**
     * [exp(x) - 1] を返す.
     *
     * @param x x
     * @return exp(x) - 1
     */
    public abstract double expm1(double x);
    
    /**
     * ln(x) を返す.
     *
     * @param x
     * @return ln(x)
     */
    public abstract double log(double x);

    /**
     * ln(1 + x) を返す.
     *
     * @param x x
     * @return ln(1 + x)
     */
    public abstract double log1p(double x);
    
    /**
     * 正の平方根を返す.
     *
     * @param x x
     * @return 平方根
     */
    public abstract double sqrt(double x);
}
