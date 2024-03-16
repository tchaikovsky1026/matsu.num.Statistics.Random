/*
 * 2024.2.23
 */
package matsu.num.statistics.random;

/**
 * <p>
 * 標準Weibull分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準Weibull分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>k</i> は形状パラメータ).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup><i>k</i> - 1</sup> exp(-<i>x</i><sup><i>k</i></sup>)
 * &emsp; (<i>x</i> &ge; 0)
 * </li>
 * 
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * <p>
 * 扱える形状パラメータ <i>k</i> は, <br>
 * {@code 1.0E-2 <= k <= 1.0E+14} <br>
 * である.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public interface WeibullRnd extends FloatingRandomGenerator {

    /**
     * 扱うことができる形状パラメータの最小値.
     */
    public static final double LOWER_LIMIT_SHAPE_PARAMETER = 1E-2;

    /**
     * 扱うことができる形状パラメータの最大値.
     */
    public static final double UPPER_LIMIT_SHAPE_PARAMETER = 1E14;

    /**
     * <p>
     * このインスタンスが扱う形状パラメータ <i>k</i> の値を返す.
     * </p>
     *
     * @return 形状パラメータ <i>k</i>
     */
    public abstract double shapeParameter();

    /**
     * {@link WeibullRnd} のファクトリ.
     */
    public static interface Factory extends RandomGeneratorFactory {

        /**
         * <p>
         * 指定したパラメータが乱数発生器に適合するかを判定する.
         * </p>
         *
         * @param k 形状パラメータ
         * @return パラメータが適合する場合はtrue
         */
        public abstract boolean acceptsParameter(double k);

        /**
         * <p>
         * 指定した形状パラメータのWeibull分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameter(double)} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param k 形状パラメータ
         * @return 形状パラメータが <i>k</i> のWeibull分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract WeibullRnd instanceOf(double k);
    }
}
