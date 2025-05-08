/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.5
 */
package matsu.num.statistics.random;

/**
 * <p>
 * 整数値を取る離散確率分布に従う乱数の発生器.
 * </p>
 * 
 * @implSpec
 *               このインターフェースをモジュール外で継承・実装してはいけない.
 *
 * @author Matsuura Y.
 */
interface IntegerRandomGenerator extends Rnd {

    /**
     * <p>
     * 与えられた基本乱数発生器を用いて, 所定の確率分布の乱数を生成する.
     * </p>
     *
     * @param random 基本乱数発生器
     * @return 所定の確率分布に従う乱数の値
     * @throws NullPointerException 引数がnullの場合
     */
    public abstract int nextRandom(BaseRandom random);
}
