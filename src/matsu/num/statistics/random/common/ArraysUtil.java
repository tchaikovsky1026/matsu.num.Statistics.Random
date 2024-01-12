/**
 * 2024.1.9
 */
package matsu.num.statistics.random.common;

/**
 * 配列に対する数学処理を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.5
 */
public interface ArraysUtil {

    /**
     * 配列をベクトルと見たときの最大値ノルムを返す.
     *
     * <p>
     * <b>v</b> のサイズが0の場合, 0が返る.
     * </p>
     *
     * @param vector ベクトル <b>v</b>
     * @return 最大ノルム ||<b>v</b>||<sub>&infin;</sub>
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public abstract double normMax(double[] vector);
}
