/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.8
 */
package matsu.num.statistics.random;

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
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
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
     * 指定した値配列のサイズが {@link CategoricalRnd.Factory#instanceOf(double[])},
     * {@link CategoricalRnd.Factory#instanceOfExp(double[])}
     * の引数に適合するかを判定する.
     * </p>
     * 
     * <p>
     * 配列のサイズが0の場合に不適合と判断される. <br>
     * 配列の中身の値には依存しない.
     * </p>
     * 
     * @param probabilityValues 値配列
     * @return 値配列が適合する場合はtrue (配列のlengthが1以上の場合である)
     * @throws NullPointerException 引数がnullの場合
     */
    public static boolean acceptsSizeOf(double[] probabilityValues) {
        return probabilityValues.length >= 1;
    }

    /**
     * {@link CategoricalRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends RndFactory {

        /**
         * <p>
         * 指定した値配列に比例するカテゴリ確率を持つ, カテゴリカル分布乱数発生器を生成する.
         * </p>
         * 
         * <p>
         * パラメータの正当性は {@link #acceptsSizeOf(double[])} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         * 
         * <p>
         * 配列に負の数やNaN, 極端な値を含んでいても良いが, 適宜修正される. <br>
         * どのように修正されるかは規定していない.
         * </p>
         *
         * @param probability 確率値の配列(定数倍の不定性は許される)
         * @return 値配列に比例するカテゴリカル分布乱数発生器インスタンス
         * @throws IllegalArgumentException 値配列がacceptされない場合
         * @throws NullPointerException 引数がnullの場合
         */
        public abstract CategoricalRnd instanceOf(double[] probability);

        /**
         * <p>
         * 指定した値配列のexpに比例するカテゴリ確率を持つ, カテゴリカル分布乱数発生器を生成する.
         * </p>
         *
         * <p>
         * パラメータの正当性は {@link #acceptsSizeOf(double[])} により検証され,
         * 不適の場合は例外がスローされる.
         * </p>
         * 
         * <p>
         * 配列にNaN, 極端な値を含んでいても良いが, 適宜修正される. <br>
         * どのように修正されるかは規定していない.
         * </p>
         *
         * @param logProbability 確率値のlogの配列(定数オフセットの不定性は許される)
         * @return 値配列にのexp比例するカテゴリカル分布乱数発生器インスタンス
         * @throws IllegalArgumentException 値配列がacceptされない場合
         * @throws NullPointerException 引数がnullの場合
         */
        public abstract CategoricalRnd instanceOfExp(double[] logProbability);
    }
}
