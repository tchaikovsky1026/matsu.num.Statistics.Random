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
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.util.GammaRndPower2Storage;

/**
 * 試行回数が (2<sup>N</sup> - 1) 型の二項乱数生成のヘルパ. <br>
 * パッケージ外に公開してはいけない.
 * 
 * @author Matsuura Y.
 */
final class Power2BinomialRndHelper {

    private final int naiveThrehold;
    private final NaiveBinomialRndHelper naiveBinomialRndHelper;

    private final GammaRndPower2Storage gammaRndPower2Storage;

    /**
     * 非公開のコンストラクタ.
     * 
     * <p>
     * 再帰呼び出しと素朴な計算を切り替えるための閾値を設定する. <br>
     * 閾値は2以上, かつ2の累乗でなければならない.
     * </p>
     * 
     * @param gammaRnds index-iが 2<sup>i</sup>の形状パラメータに対する標準ガンマ乱数発生器となる
     * 
     */
    Power2BinomialRndHelper(
            int naiveThrehold,
            NaiveBinomialRndHelper naiveBinomialRndHelper,
            GammaRndPower2Storage gammaRndPower2Storage) {

        assert naiveThrehold >= 2 : "2以上でない";
        assert Power2.isPowerOf2(naiveThrehold) : "2の累乗でない";

        this.naiveThrehold = naiveThrehold;
        this.naiveBinomialRndHelper = naiveBinomialRndHelper;
        this.gammaRndPower2Storage = gammaRndPower2Storage;
    }

    /**
     * Bin(n,p)に従う乱数を, 素朴な方法により生成する. <br>
     * (n = 2<sup>N</sup> - 1)と書けなければならない.
     */
    int next(int n, double p, BaseRandom random) {
        assert Power2.isPowerOf2(n + 1) : "2の累乗でない";

        if (n < this.naiveThrehold) {
            return this.naiveBinomialRndHelper.next(n, p, random);
        }

        int power2_N_minus_1 = (n + 1) >> 1;
        int N_minus_1 = Power2.floorLog2(power2_N_minus_1);

        GammaRnd gammaRnd = this.gammaRndPower2Storage.getAt(N_minus_1);
        double u1 = gammaRnd.nextRandom(random) + Double.MIN_NORMAL;
        double u2 = gammaRnd.nextRandom(random) + Double.MIN_NORMAL;
        double w = u1 + u2;
        double y = w * p;

        return u1 >= y
                ? this.next(power2_N_minus_1 - 1, y / u1, random)
                : power2_N_minus_1 + this.next(power2_N_minus_1 - 1, (y - u1) / u2, random);
    }
}
