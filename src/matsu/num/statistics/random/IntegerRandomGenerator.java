/*
 * 2023.1.25
 */
package matsu.num.statistics.random;

import java.util.Random;

/**
 * 整数値を取る離散確率分布に従う乱数の発生器.
 *
 * @author Matsuura Y.
 * @version 9.0
 */
public interface IntegerRandomGenerator {

    /**
    * 与えられた基本乱数発生器を用いて, 所定の確率分布の乱数を生成する.
    *
    * @param random 基本乱数発生器
    * @return 所定の確率分布に従う乱数の値
    * @throws NullPointerException 引数にnullが含まれる場合
    */
    public abstract int nextRandom(Random random);
}
