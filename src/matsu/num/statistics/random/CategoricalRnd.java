/*
 * 2023.3.22
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.cat.table.TableBasedCatRnd;

/**
 * カテゴリカル分布に従う乱数発生器を扱う. <br>
 * 取りうる値を0,1,...,<i>n</i> - 1とし, 値<i>k</i>を取る確率を<i>p</i><sub><i>k</i></sub>とする
 * (&Sigma; <sub><i>k</i> = 0</sub><sup><i>n</i> - 1</sup>
 * <i>p</i><sub><i>k</i></sub> = 1). <br>
 * カテゴリカル分布の確率関数 P(<i>k</i>) は, <br>
 * P(<i>k</i>) =
 * <ul>
 * <li><i>p</i><sub><i>k</i></sub> (0 &le; <i>k</i> &le; <i>n</i> - 1)</li>
 * <li>0 (otherwise)</li>
 * </ul>
 * である.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public interface CategoricalRnd extends IntegerRandomGenerator {

    /**
     * このインスタンスが扱うカテゴリサイズ (<i>n</i>) の値を返す.
     * 
     * @return カテゴリサイズ
     */
    public abstract int size();

    /**
     * 指定した値配列に比例するカテゴリ確率を持つ, カテゴリカル分布乱数発生器を生成する.
     *
     * @param probability 確率値の配列(定数倍の不定性は許される)
     * @return 値配列に比例するカテゴリカル分布乱数発生器インスタンス
     */
    public static CategoricalRnd instanceOf(double[] probability) {
        return TableBasedCatRnd.instanceOf(probability);
    }

    /**
     * 指定した値配列のexpに比例するカテゴリ確率を持つ, カテゴリカル分布乱数発生器を生成する.
     *
     * @param logProbability 確率値のlogの配列(定数オフセットの不定性は許される)
     * @return 値配列にのexp比例するカテゴリカル分布乱数発生器インスタンス
     */
    public static CategoricalRnd instanceOfExp(double[] logProbability) {
        return TableBasedCatRnd.instanceOfExp(logProbability);
    }
}
