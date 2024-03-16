/*
 * 2023.12.18
 */
package matsu.num.statistics.random;

/**
 * <p>
 * 連続確率分布に従う乱数の発生器.
 * </p>
 * 
 * <p>
 * このサブタイプのインスタンスはイミュータブルであり, (乱数であることを除いて)関数的に振る舞う. <br>
 * 乱数発生時に与えられる {@link BaseRandom} が許すならば,
 * 並行プロセスにおいて競合が発生しないことを保証する.
 * </p>
 *
 * @author Matsuura Y.
 * @version 17.3
 */
public interface FloatingRandomGenerator {

    /**
     * <p>
     * 与えられた基本乱数発生器を用いて, 所定の確率分布の乱数を生成する.
     * </p>
     *
     * @param random 基本乱数発生器
     * @return 所定の確率分布に従う乱数の値
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public abstract double nextRandom(BaseRandom random);
}
