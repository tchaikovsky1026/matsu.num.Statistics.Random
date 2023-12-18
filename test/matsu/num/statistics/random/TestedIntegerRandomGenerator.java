/*
 * 2023.1.25
 */
package matsu.num.statistics.random;

/**
 * テストを行う整数値離散分布の累積分布関数発生器.
 *
 * @author Matsuura Y.
 * @version 9.0
 */
public interface TestedIntegerRandomGenerator {

    /**
     * 新しい値を生成する.
     *
     * @return 新しい値
     */
    public abstract int newValue();

    /**
     * 乱数発生器が従う(ことを期待する)確率分布の, 引数に対応する累積確率を計算する.
     *
     * @param arg 引数
     * @return 引数に対する, 確率分布の累積確率
     */
    public abstract double cumulativeProbability(int arg);

    /**
     * 乱数発生器が従う(ことを期待する)確率分布の, 引数の1つ下の階級に対応する累積確率を計算する.
     *
     * @param arg 引数
     * @return 引数の1つ下の階級に対する, 確率分布の累積確率
     */
    public abstract double cumulativeProbabilityOneBelow(int arg);
}
