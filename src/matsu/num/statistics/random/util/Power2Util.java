/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.22
 */
package matsu.num.statistics.random.util;

/**
 * 2のべき乗の整数に関する計算補助.
 * 
 * @author Matsuura Y.
 */
public final class Power2Util {

    private Power2Util() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * 整数が2の累乗かどうかを判定する. <br>
     * nは1以上でなければならない.
     * 
     * @param n n
     * @return 2の累乗ならtrue
     * @throws IllegalArgumentException nが1以上でない場合
     */
    public static boolean isPowerOf2(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("not n >= 1");
        }
        return (n & (n - 1)) == 0;
    }

    /**
     * 整数nに対する floor(log <sub>2</sub> n) を計算する. <br>
     * nは1以上でなければならない.
     * 
     * @param n n
     * @return floor(log <sub>2</sub> n)
     * @throws IllegalArgumentException nが1以上でない場合
     */
    public static int floorLog2(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("not n >= 1");
        }
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    /**
     * 与えた整数を2進展開し, 各要素を詰めた配列を返す. <br>
     * 例えば, n = 5 ならば [1, 4] <br>
     * nは0以上でなければならない.
     * 
     * @param n n
     * @return 配列
     * @throws IllegalArgumentException nが0以上でない場合
     */
    public static int[] expandBinary(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("not n >= 1");
        }

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
