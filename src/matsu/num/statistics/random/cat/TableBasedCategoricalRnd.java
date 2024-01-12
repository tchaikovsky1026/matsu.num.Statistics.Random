/**
 * 2024.1.9
 */
package matsu.num.statistics.random.cat;

import java.util.Arrays;

import matsu.num.statistics.random.CategoricalRnd;
import matsu.num.statistics.random.Random;
import matsu.num.statistics.random.common.ArraysUtil;
import matsu.num.statistics.random.common.ArraysUtilFactory;
import matsu.num.statistics.random.common.Exponentiation;
import matsu.num.statistics.random.common.ExponentiationFactory;

/**
 * テーブル法に基づく, カテゴリカル分布に従う乱数発生器の実装.
 *
 * @author Matsuura Y.
 * @version 17.5
 */
final class TableBasedCategoricalRnd implements CategoricalRnd {

    private static final int N_MAX_BIT = 16;
    private static final int N_START_BIT = 10;

    private static final double THRESHOLD = 0.9;

    /**
     * カテゴリの数. <br>
     * カテゴリ数が n のとき, 0, 1, ... , n - 1 の値をとる乱数を生成する.
     */
    private int catSize;

    private int nBitMask;

    //ヒストグラム変数テーブル
    private final int[] table;

    /**
     * tail部の累積確率(自身を含めない). <br>
     * すなわち[0] = 0, [cat] = 1 が確定. <br>
     * 配列サイズは cat + 1.
     */
    private final double[] tailCumulative;

    /**
     * @throws IllegalArgumentException 確率が不正値(負, +Inf, NaN)を含む場合,
     *             総和が0の場合, カテゴリサイズ(配列のlength)が0の場合
     */
    private TableBasedCategoricalRnd(double[] probability) {
        if (probability.length == 0) {
            throw new IllegalArgumentException("カテゴリサイズが0");
        }
        this.catSize = probability.length;

        for (int j = catSize - 1; j >= 0; j--) {
            double p = probability[j];
            if (!(Double.isFinite(p) && p >= 0)) {
                throw new IllegalArgumentException("確率の値に不正値(0以上の有限値ではない数)が含まれています");
            }
        }
        //確率値を規格化.
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
        return this.catSize;
    }

    @Override
    public final int nextRandom(Random random) {

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

    @Override
    public String toString() {
        return String.format(
                "CategoricalRnd(size=%s)", this.size());
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
     * 0以上の有限値を持つ配列を, 総和が1dになるように規格化する.
     */
    private static double[] normalize(double[] probability) {
        int cat = probability.length;
        double[] normalizedProbability = new double[probability.length];
        System.arraycopy(probability, 0, normalizedProbability, 0, normalizedProbability.length);

        //確率の規格化
        double sum = 0;
        for (int l = cat - 1; l >= 0; l--) {
            sum += normalizedProbability[l];
        }
        if (!(Double.isFinite(sum) && sum > 0)) {
            throw new IllegalArgumentException("カテゴリカル分布として有効な確率値列でない");
        }
        for (int i = 0; i < cat; i++) {
            normalizedProbability[i] /= sum;
        }

        return normalizedProbability;
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

    private static double[] toExp(double[] logProbability) {
        final Exponentiation exponentiation = ExponentiationFactory.instance();
        final ArraysUtil arraysUtil = ArraysUtilFactory.instance();

        if (logProbability.length == 0) {
            throw new IllegalArgumentException("カテゴリサイズが0");
        }

        final int size = logProbability.length;
        final double max = arraysUtil.normMax(logProbability);

        if (!(Double.isFinite(max))) {
            throw new IllegalArgumentException("不正値を含む");
        }

        double[] p = new double[size];
        for (int i = 0; i < size; i++) {
            p[i] = exponentiation.exp(logProbability[i] - max);
        }
        return p;
    }

    /**
     * 指定した値配列に比例するカテゴリ確率を持つ, カテゴリカル分布乱数発生器を生成する.
     *
     * @param probability 確率値の配列(定数倍の不定性は許される)
     * @return 値配列に比例するカテゴリカル分布乱数発生器インスタンス
     * @throws IllegalArgumentException 確率が不正値(負, +Inf, NaN)を含む場合,
     *             総和が0の場合, カテゴリサイズ(配列のlength)が0の場合
     */
    static CategoricalRnd instanceOf(double[] probability) {
        return new TableBasedCategoricalRnd(probability);
    }

    /**
     * 指定した値配列のexpに比例するカテゴリ確率を持つ, カテゴリカル分布乱数発生器を生成する.
     *
     * @param logProbability 確率値のlogの配列(定数オフセットの不定性は許される)
     * @return 値配列にのexp比例するカテゴリカル分布乱数発生器インスタンス
     * @throws IllegalArgumentException log確率が不正値(+Inf, NaN)を含む場合,
     *             カテゴリサイズ(配列のlength)が0の場合
     */
    static CategoricalRnd instanceOfExp(double[] logProbability) {
        return new TableBasedCategoricalRnd(toExp(logProbability));
    }
}
