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

/**
 * 2のべき乗の整数に関する計算補助.
 * 
 * @author Matsuura Y.
 */
final class Power2 {

    private Power2() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * 整数が2の累乗かどうかを判定する. <br>
     * nは1以上でなければならない.
     */
    static boolean isPowerOf2(int n) {
        return (n & (n - 1)) == 0;
    }

    /**
     * 整数nに対する log <sub>2</sub> n を計算する. <br>
     * nは1以上でなければならない.
     */
    static int log2(int n) {
        return 31 - Integer.numberOfLeadingZeros(n);
    }
}
