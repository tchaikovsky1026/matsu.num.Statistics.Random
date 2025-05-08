/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.5.6
 */
package matsu.num.statistics.random.cat;

import java.util.Arrays;
import java.util.Objects;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.CategoricalRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * テーブル法に基づく, カテゴリカル分布に従う乱数発生器の実装.
 *
 * @author Matsuura Y.
 */
public final class TableBasedCategoricalRnd extends SkeletalCategoricalRnd {

    private static final int N_MAX_BIT = 16;
    private static final int N_START_BIT = 10;

    private static final double THRESHOLD = 0.9;

    private final int nBitMask;

    //ヒストグラム変数テーブル
    private final int[] table;

    /**
     * tail部の累積確率(自身を含めない). <br>
     * すなわち[0] = 0, [cat] = 1 が確定. <br>
     * 配列サイズは cat + 1.
     */
    private final double[] tailCumulative;

    /**
     * 引数に正当な確率の配列を渡して, インスタンスを生成する. <br>
     * 呼び出し元で配列の防御的コピーをすること.
     * 
     * @param probability 正当な規格化された確率, 上書きされる
     */
    private TableBasedCategoricalRnd(double[] probability) {
        super(probability.length);

        //メインテーブルのサイズを決定し,代入
        int tableSize = tableSize(probability);
        this.nBitMask = tableSize - 1;
        this.table = new int[tableSize];
        this.setMainTable(probability);
        ProbabilityNormalization.normalize(probability);

        //tailの累積確率を計算
        this.tailCumulative = cumulative(probability);
    }

    @Override
    public final int nextRandom(BaseRandom random) {

        int out = this.table[random.nextInt() & this.nBitMask];
        if (out < this.catSize) {
            return out;
        }

        int lowerIndex = 0;
        int upperIndex = this.catSize;
        double u = random.nextDouble();
        while (upperIndex - lowerIndex > 1) {
            int presentIndex = (lowerIndex + upperIndex) >>> 1;
            if (u < this.tailCumulative[presentIndex]) {
                upperIndex = presentIndex;
            } else {
                lowerIndex = presentIndex;
            }
        }
        return lowerIndex;
    }

    /**
     * mainTableに値を代入しつつ, 確率値を書き換える.
     * 
     * @param probability 確率値
     */
    private void setMainTable(double[] probability) {

        //mainTableのサイズ決定
        //最大サイズとしてメインテーブルに入るそれぞれのカテゴリの数を計算し,確率値を更新する.
        final int tableSize = this.table.length;
        final double elementProbability = 1.0 / tableSize;

        int indexInTable = 0;
        for (int i = 0, len_i = this.catSize; i < len_i; i++) {
            double p = probability[i];
            int toIndexForFilled = Math.min(indexInTable + (int) (tableSize * p), tableSize);
            Arrays.fill(this.table, indexInTable, toIndexForFilled, i);
            probability[i] -= elementProbability * (toIndexForFilled - indexInTable);

            //probabilityが不正な値にならないように修正
            if (probability[i] <= 0d) {
                probability[i] = Double.MIN_VALUE;
            }

            indexInTable = toIndexForFilled;
        }
        //テーブルの残りをval = catで埋める.
        Arrays.fill(this.table, indexInTable, tableSize, this.catSize);
    }

    /**
     * 規格化済み確率質量関数を累積分布関数に書き換える. <br>
     * 戻り値の累積分布関数.lengthは, 引数.length + 1である.
     */
    private static double[] cumulative(double[] probability) {
        final int size = probability.length + 1;
        double[] cumulative = new double[size];

        //累積確率を計算
        //1以上が生じても無視する.
        for (int i = 0; i < size - 2; i++) {
            cumulative[i + 1] = cumulative[i] + probability[i];
        }

        cumulative[size - 1] = 1;
        return cumulative;
    }

    /**
     * 必要なテーブルサイズを求める.
     */
    private static int tableSize(double[] probability) {

        final int cat = probability.length;

        //mainTableのサイズ決定
        //最大サイズとしてメインテーブルに入るそれぞれのカテゴリの数を計算する.
        final int nMax = (1 << N_MAX_BIT);
        //nMaxでテーブル作成したと仮定したときの, 各カテゴリの要素数.
        final int[] catNumberAtMinElement = new int[cat];
        for (int i = 0; i < cat; i++) {
            catNumberAtMinElement[i] = (int) (probability[i] * nMax);
        }
        //nMaxでテーブルを作ったときに必要な有効要素数
        final int thresholdCatNumber = (int) (nMax * THRESHOLD);

        //有効要素数がthresholdを越えるようにテーブルサイズを決定する.
        int nBit = N_START_BIT;
        while (nBit < N_MAX_BIT) {
            int bitMask = 0xFFFFFFFF << (N_MAX_BIT - nBit);

            int validElementSize = 0;
            for (int j = cat - 1; j >= 0; j--) {
                validElementSize += catNumberAtMinElement[j] & bitMask;
            }
            if (validElementSize >= thresholdCatNumber) {
                break;
            }
            nBit++;
        }

        return 1 << nBit;
    }

    /**
     * テーブル法に基づく, カテゴリカル分布に従う乱数発生器のファクトリ.
     * 
     * @param exponentiation 指数関数の計算器
     * @return 乱数発生器ファクトリ
     * @throws NullPointerException 引数がnullの場合
     */
    public static CategoricalRnd.Factory createFactory(Exponentiation exponentiation) {
        return new Factory(Objects.requireNonNull(exponentiation));
    }

    private static final class Factory extends SkeletalCategoricalRnd.Factory {

        Factory(Exponentiation exponentiation) {
            super(exponentiation);
        }

        @Override
        CategoricalRnd createInstanceOf(double[] probability) {
            return new TableBasedCategoricalRnd(probability);
        }
    }
}
