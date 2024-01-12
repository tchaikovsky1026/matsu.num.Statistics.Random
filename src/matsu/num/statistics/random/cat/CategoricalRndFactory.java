/**
 * 2024.1.9
 */
package matsu.num.statistics.random.cat;

import matsu.num.statistics.random.CategoricalRnd;

/**
 * このパッケージが扱う {@linkplain CategoricalRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class CategoricalRndFactory {

    private CategoricalRndFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * <p>
     * 指定した値配列に比例するカテゴリ確率を持つ, カテゴリカル分布乱数発生器を生成する.
     * </p>
     *
     * @param probability 確率値の配列(定数倍の不定性は許される)
     * @return 値配列に比例するカテゴリカル分布乱数発生器インスタンス
     */
    public static CategoricalRnd instanceOf(double[] probability) {
        return TableBasedCategoricalRnd.instanceOf(probability);
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
        return TableBasedCategoricalRnd.instanceOfExp(logProbability);
    }
}
