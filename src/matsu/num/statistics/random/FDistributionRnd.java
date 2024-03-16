/*
 * 2024.2.23
 */
package matsu.num.statistics.random;

/**
 * <p>
 * F分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * F分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>d</i><sub>1</sub>, <i>d</i><sub>2</sub> は自由度).
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * <i>x</i><sup>(<i>d</i><sub>1</sub> - 2) / 2</sup>
 * /
 * (1 + (<i>d</i><sub>1</sub> / <i>d</i><sub>2</sub>) <i>x</i>)
 * <sup>(<i>d</i><sub>1</sub> + <i>d</i><sub>2</sub>) / 2</sup>
 * &emsp; (<i>x</i> &ge; 0)
 * </li>
 * 
 * <li>P(<i>x</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 * 
 * <p>
 * 扱える自由度 <i>d</i><sub>1</sub>, <i>d</i><sub>2</sub> は, <br>
 * {@code 2.0E-2 <= (d1, d2) <= 2.0E+14} <br>
 * である.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public interface FDistributionRnd extends FloatingRandomGenerator {

    /**
     * 扱うことができる自由度の最小値.
     */
    public static final double LOWER_LIMIT_DEGREES_OF_FREEDOM = 2E-2;

    /**
     * 扱うことができる自由度の最大値.
     */
    public static final double UPPER_LIMIT_DEGREES_OF_FREEDOM = 2E14;

    /**
     * <p>
     * このインスタンスが扱う分子自由度 <i>d</i><sub>1</sub> の値を返す.
     * </p>
     *
     * @return 分子自由度 <i>d</i><sub>1</sub>
     */
    public abstract double numeratorDegreesOfFreedom();

    /**
     * <p>
     * このインスタンスが扱う分母自由度 <i>d</i><sub>2</sub> の値を返す.
     * </p>
     *
     * @return 分母自由度 <i>d</i><sub>2</sub>
     */
    public abstract double denominatorDegreesOfFreedom();

    /**
     * {@link FDistributionRnd} のファクトリ.
     */
    public static interface Factory extends RandomGeneratorFactory {

        /**
         * <p>
         * 指定したパラメータが乱数発生器に適合するかを判定する.
         * </p>
         *
         * @param d1 分子自由度
         * @param d2 分母自由度
         * @return パラメータが適合する場合はtrue
         */
        public abstract boolean acceptsParameters(double d1, double d2);

        /**
         * <p>
         * 指定した自由度のF分布乱数発生器インスタンスを返す.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsParameters(double, double)} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         *
         * @param d1 分子自由度
         * @param d2 分母自由度
         * @return 自由度が (<i>d</i><sub>1</sub>, <i>d</i><sub>2</sub>)
         *             のF分布乱数発生器インスタンス
         * @throws IllegalArgumentException パラメータがacceptされない場合
         */
        public abstract FDistributionRnd instanceOf(double d1, double d2);

    }
}