/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random;

import org.junit.Ignore;

/**
 * テストを行う浮動小数点数乱数発生器.
 *
 * @author Matsuura Y.
 * @version 9.0
 */
@Ignore
public interface TestedFloatingRandomGenerator {

    /**
     * 新しい値を生成する.
     *
     * @return 新しい値
     */
    public abstract double newValue();

    /**
     * 乱数発生器が従う(ことを期待する)確率分布の累積確率を計算する.
     *
     * @param arg 引数
     * @return 引数に対する, 確率分布の累積確率
     */
    public abstract double cumulativeProbability(double arg);
}
