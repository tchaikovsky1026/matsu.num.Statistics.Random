/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.cat;

import matsu.num.statistics.random.CategoricalRnd;

/**
 * {@link CategoricalRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
abstract class SkeletalCategoricalRnd implements CategoricalRnd {
    /**
     * カテゴリの数. <br>
     * カテゴリ数が n のとき, 0, 1, ... , n - 1 の値をとる乱数を生成する.
     */
    protected final int catSize;

    /**
     * 唯一のコンストラクタ. <br>
     * 与えるパラメータのバリデーションは行われていない.
     * 
     * @param catSize カテゴリサイズ
     */
    protected SkeletalCategoricalRnd(int catSize) {
        super();

        assert CategoricalRnd.acceptsSizeOf(new double[catSize]);

        this.catSize = catSize;
    }

    @Override
    public final int size() {
        return this.catSize;
    }

    @Override
    public String toString() {
        return String.format(
                "CategoricalRnd(size=%s)", this.size());
    }
}
