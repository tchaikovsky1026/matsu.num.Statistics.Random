/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.22
 */
package matsu.num.statistics.random.binomial;

import matsu.num.statistics.random.BaseRandom;

/**
 * 素朴な方法による二項乱数生成ヘルパ. <br>
 * パッケージ外部に公開してはいけない.
 * 
 * @author Matsuura Y.
 */
final class NaiveBinomialRndHelper {

    /**
     * 唯一のコンストラクタ.
     */
    NaiveBinomialRndHelper() {
        super();
    }

    /**
     * Bin(n,p)に従う乱数を, 素朴な方法により生成する. <br>
     * nは0以上, pは0から1でなければならない.
     */
    int next(int n, double p, BaseRandom random) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (random.nextDouble() < p) {
                count++;
            }
        }
        return count;
    }
}
