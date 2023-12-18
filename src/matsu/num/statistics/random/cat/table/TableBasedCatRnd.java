/**
 * 2023.10.12
 */
package matsu.num.statistics.random.cat.table;

import java.util.Arrays;
import java.util.Random;

import matsu.num.commons.ArraysUtil;
import matsu.num.commons.Exponentiation;
import matsu.num.statistics.random.CategoricalRnd;

/**
 * テーブル法に基づく, カテゴリカル分布に従う乱数発生器を扱う.
 *
 * @author Matsuura Y.
 * @version 16.1
 */
public final class TableBasedCatRnd implements CategoricalRnd {

    private static final int N_MAX_BIT = 16;
    private static final int N_START_BIT = 10;

    private static final double THRESHOLD = 0.9;

    private int nBitMask;

    //ヒストグラム変数テーブル
    private final int[] table;

    private int cat;
    //tail部の累積確率(自身を含めない)
    //すなわち[0] = 0, [cat] = 1 が確定.
    private final double[] tailCumulative;

    private TableBasedCatRnd(double[] probability) {
        //引数サイズチェック
        if (probability.length == 0) {
            throw new IllegalArgumentException("配列のサイズが0");
        }
        this.cat = probability.length;

        //引数チェックし(0 <= p[j] < infinity)確率値を規格化.
        for (int j = cat - 1; j >= 0; j--) {
            double p = probability[j];
            if (!(Double.isFinite(p) && p >= 0)) {
                throw new IllegalArgumentException("確率の値に不正値(0以上の有限値ではない数)が含まれています");
            }
        }
        double[] normalizedProbability = normalize(probability);

        //メインテーブルのサイズを決定し,代入
        int tableSize = tableSize(normalizedProbability);
        this.nBitMask = tableSize - 1;
        this.table = new int[tableSize];
        this.setMainTable(normalizedProbability);
        double[] subTableProbability = normalize(normalizedProbability);

        //tailの累積確率を計算
        this.tailCumulative = cumulative(subTableProbability);

    }

    @Override
    public int size() {
        return this.cat;
    }

    @Override
    public final int nextRandom(Random random) {

        int out = table[random.nextInt() & nBitMask];
        if (out < cat) {
            return out;
        }

        int lowerIndex = 0;
        int upperIndex = cat;
        double u = random.nextDouble();
        while (upperIndex - lowerIndex > 1) {
            int presentIndex = (lowerIndex + upperIndex) >>> 1;
            if (u < tailCumulative[presentIndex]) {
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
        for (int i = 0, len_i = this.cat; i < len_i; i++) {
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
        Arrays.fill(this.table, indexInTable, tableSize, this.cat);
    }

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

    private static double[] normalize(double[] probability) {
        int cat = probability.length;
        double[] normalizedProbability = new double[probability.length];
        System.arraycopy(probability, 0, normalizedProbability, 0, normalizedProbability.length);

        //確率の規格化
        double sum = 0;
        int l;
        for (l = cat - 1; l >= 3; l -= 4) {
            sum += (normalizedProbability[l] + normalizedProbability[l - 1])
                    + (normalizedProbability[l - 2] + normalizedProbability[l - 3]);
        }
        for (; l >= 0; l--) {
            sum += normalizedProbability[l];
        }
        if (!(Double.isFinite(sum) && sum > 0)) {
            throw new IllegalArgumentException("カテゴリカル分布として有効な確率値列でない(極端に大きいか, 全値が0の可能性があります)");
        }
        double invSum = 1 / sum;
        for (int i = 0; i < cat; i++) {
            normalizedProbability[i] *= invSum;
        }

        return normalizedProbability;
    }

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
            int j;
            for (j = cat - 1; j >= 3; j -= 4) {
                int p0 = catNumberAtMinElement[j] & bitMask;
                int p1 = catNumberAtMinElement[j - 1] & bitMask;
                int p2 = catNumberAtMinElement[j - 2] & bitMask;
                int p3 = catNumberAtMinElement[j - 3] & bitMask;
                validElementSize += (p0 + p1) + (p2 + p3);
            }
            for (; j >= 0; j--) {
                validElementSize += catNumberAtMinElement[j] & bitMask;
            }
            if (validElementSize >= thresholdCatNumber) {
                break;
            }
            nBit++;
        }

        return 1 << nBit;
    }

    private static double[] toExp(double[] logProbability) {

        if (logProbability.length == 0) {
            throw new IllegalArgumentException("size of double[] is zero. ");
        }

        final int size = logProbability.length;
        final double max = ArraysUtil.normMax(logProbability);

        if (!(Double.isFinite(max))) {
            throw new IllegalArgumentException("invalid value. ");
        }

        double[] p = new double[size];
        for (int i = 0; i < size; i++) {
            p[i] = Exponentiation.exp(logProbability[i] - max);
        }
        return p;
    }

    /**
     * 指定した値配列に比例するカテゴリ確率を持つ, カテゴリカル分布乱数発生器を生成する.
     *
     * @param probability 確率値の配列(定数倍の不定性は許される)
     * @return 値配列に比例するカテゴリカル分布乱数発生器インスタンス
     */
    public static CategoricalRnd instanceOf(double[] probability) {
        return new TableBasedCatRnd(probability);
    }

    /**
     * 指定した値配列のexpに比例するカテゴリ確率を持つ, カテゴリカル分布乱数発生器を生成する.
     *
     * @param logProbability 確率値のlogの配列(定数オフセットの不定性は許される)
     * @return 値配列にのexp比例するカテゴリカル分布乱数発生器インスタンス
     */
    public static CategoricalRnd instanceOfExp(double[] logProbability) {
        return new TableBasedCatRnd(toExp(logProbability));
    }
}
