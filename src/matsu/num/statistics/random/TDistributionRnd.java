/*
 * 2024.2.23
 */
package matsu.num.statistics.random;

/**
 * <p>
 * Student-t分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * Student-t分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>&nu;</i> は自由度). <br>
 * P(<i>x</i>) &prop;
 * (1 + <i>x</i><sup><i>2</i></sup> / <i>&nu;</i>)
 * <sup>-(<i>&nu;</i> + 1) / 2</sup>
 * </p>
 * 
 * <p>
 * 扱える自由度 <i>&nu;</i> は, <br>
 * {@code 2.0E-2 <= nu <= 2.0E+28} <br>
 * である.
 * </p>
 *
 * @author Matsuura Y.
 * @version 18.2
 */
public interface TDistributionRnd extends FloatingRandomGenerator {

    /**
     * 扱うことができる自由度の最小値.
     */
    public static final double LOWER_LIMIT_DEGREES_OF_FREEDOM = 2E-2;

    /**
     * 扱うことができる自由度の最大値.
     */
    public static final double UPPER_LIMIT_DEGREES_OF_FREEDOM = 2E28;

    /**
     * <p>
     * このインスタンスが扱う自由度 <i>&nu;</i> の値を返す.
     * </p>
     *
     * @return 自由度 <i>&nu;</i>
     */
    public abstract double degreesOfFreedom();

    /**
     * {@link TDistributionRnd} のファクトリ.
     */
    public static interface Factory extends RandomGeneratorFactory {

        /**
         * <p>
         * 指定したパラメータが乱数発生器に適合するかを判定する.
         * </p>
         *
         * @param nu 自由度
         * @return パラメータが適合する場合はtrue
         */
        public abstract boolean acceptsParameter(double nu);

        /**
         * <p>
         * 指定した自由度のStudent-t分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameter(double)} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param nu 自由度
         * @return 自由度が <i>&nu;</i> のStudent-t分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract TDistributionRnd instanceOf(double nu);
    }
}
