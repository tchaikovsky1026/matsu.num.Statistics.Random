/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.24
 */
package matsu.num.statistics.random.util;

import matsu.num.statistics.random.GammaRnd;

/**
 * 2の累乗の階乗パラメータを持った標準ガンマ乱数のストレージ. <br>
 * n をサイズとして, <br>
 * [1, 2, 4, ..., 2<sup>n - 1</sup>] <br>
 * の形状パラメータを扱える. <br>
 * 0 &le; n &le; 30 でなければならない.
 * 
 * @author Matsuura Y.
 */
public final class GammaRndPower2Storage {

    private final GammaRnd[] gammaRnds;

    private GammaRndPower2Storage(int size, GammaRnd.Factory gammaRndFactory) {
        this.gammaRnds = new GammaRnd[size];

        int k = 1;
        for (int i = 0; i < size; i++) {
            this.gammaRnds[i] = gammaRndFactory.instanceOf(k);
            k *= 2;
        }
    }

    /**
     * 与えた index のガンマ乱数生成器,
     * すなわち 形状パラメータが 2^<sup>index</sup> のガンマ乱数生成器を返す.
     * 
     * @param index index
     * @return 乱数生成器
     * @throws IndexOutOfBoundsException index が不正の場合
     */
    public GammaRnd getAt(int index) {
        if (!(0 <= index && index < gammaRnds.length)) {
            throw new IndexOutOfBoundsException();
        }

        return this.gammaRnds[index];
    }

    /**
     * 与えたサイズのストレージを作成する.
     * 
     * @param size サイズ
     * @param gammaRndFactory ガンマ乱数生成器のファクトリ
     * @return ストレージ
     * @throws IllegalArgumentException sizeが負の場合
     * @throws NullPointerException 引数にullが含まれる場合
     */
    public static GammaRndPower2Storage create(int size, GammaRnd.Factory gammaRndFactory) {
        if (!(0 <= size && size < 31)) {
            throw new IllegalArgumentException("Illegal parameter: size = " + size);
        }

        return new GammaRndPower2Storage(size, gammaRndFactory);
    }
}
