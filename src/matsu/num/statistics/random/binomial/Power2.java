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
     * 整数nに対する floor(log <sub>2</sub> n) を計算する. <br>
     * nは1以上でなければならない.
     */
    static int floorLog2(int n) {
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    /**
     * 与えた整数を2進展開し, 各要素を詰めた配列を返す. <br>
     * 例えば, n = 5 ならば [1, 4] <br>
     * nは0以上でなければならない.
     */
    static int[] expandBinary(int n) {
        assert n >= 0;

        int[] out = new int[Integer.bitCount(n)];

        int current_power2_a = 1;
        int cursor = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                out[cursor] = current_power2_a;
                cursor++;
            }
            current_power2_a <<= 1;
            n >>= 1;
        }

        return out;
    }
}
