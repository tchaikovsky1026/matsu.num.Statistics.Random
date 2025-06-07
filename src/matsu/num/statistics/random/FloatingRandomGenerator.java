/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.7
 */
package matsu.num.statistics.random;

/**
 * <p>
 * 連続確率分布に従う乱数の発生器を表し,
 * 浮動小数点数により値を表現する.
 * </p>
 *
 * <p>
 * <u><i>
 * 注意: <br>
 * このインターフェースは将来にわたって維持されるとは限らず,
 * モジュール外ではこのインターフェースを型として依存してはいけない. <br>
 * 公開されたサブインターフェースが存在する場合, 型はサブインターフェースにより扱われなければならない.
 * </i></u>
 * </p>
 * 
 * @implSpec
 *               このインターフェースをモジュール外で継承・実装してはいけない.
 *
 * @author Matsuura Y.
 */
public interface FloatingRandomGenerator extends Rnd {

    /**
     * <p>
     * 与えられた基本乱数発生器を用いて, 所定の確率分布の乱数を生成する.
     * </p>
     *
     * @param random 基本乱数発生器
     * @return 所定の確率分布に従う乱数の値
     * @throws NullPointerException 引数がnullの場合
     */
    public abstract double nextRandom(BaseRandom random);
}
