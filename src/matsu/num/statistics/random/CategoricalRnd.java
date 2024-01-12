/*
 * 2024.1.9
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.cat.CategoricalRndFactory;

/**
 * <p>
 * カテゴリカル分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 取りうる値を0, 1, ... , <i>n</i> - 1 とし,
 * 値 <i>k</i> を取る確率を <i>p</i><sub><i>k</i></sub> とする
 * (&Sigma; <sub><i>k</i> = 0</sub><sup><i>n</i> - 1</sup>
 * <i>p</i><sub><i>k</i></sub> = 1). <br>
 * カテゴリカル分布の確率質量関数 P(<i>k</i>) は次のとおりである.
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>k</i>) =
 * <i>p</i><sub><i>k</i></sub>
 * &emsp; (0 &le; <i>k</i> &le; <i>n</i> - 1)
 * </li>
 * 
 * <li>P(<i>k</i>) = 0 &emsp; (otherwise)</li>
 * </ul>
 *
 * @author Matsuura Y.
 * @version 17.4
 */
public interface CategoricalRnd extends IntegerRandomGenerator {

    /**
     * <p>
     * このインスタンスが扱うカテゴリサイズ (<i>n</i>) の値を返す.
     * </p>
     * 
     * @return カテゴリサイズ (<i>n</i>)
     */
    public abstract int size();

    /**
     * <p>
     * 指定した値配列に比例するカテゴリ確率を持つ, カテゴリカル分布乱数発生器を生成する.
     * </p>
     *
     * @param probability 確率値の配列(定数倍の不定性は許される)
     * @return 値配列に比例するカテゴリカル分布乱数発生器インスタンス
     */
    public static CategoricalRnd instanceOf(double[] probability) {
        return CategoricalRndFactory.instanceOf(probability);
    }

    /**
     * <p>
     * 指定した値配列のexpに比例するカテゴリ確率を持つ, カテゴリカル分布乱数発生器を生成する.
     * </p>
     *
     * @param logProbability 確率値のlogの配列(定数オフセットの不定性は許される)
     * @return 値配列にのexp比例するカテゴリカル分布乱数発生器インスタンス
     */
    public static CategoricalRnd instanceOfExp(double[] logProbability) {
        return CategoricalRndFactory.instanceOfExp(logProbability);
    }
}
