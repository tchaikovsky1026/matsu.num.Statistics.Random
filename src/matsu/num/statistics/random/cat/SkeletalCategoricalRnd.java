/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.3
 */
package matsu.num.statistics.random.cat;

import java.util.Objects;
import java.util.function.Function;

import matsu.num.statistics.random.CategoricalRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * {@link CategoricalRnd} の骨格実装.
 * 
 * @author Matsuura Y.
 */
abstract class SkeletalCategoricalRnd implements CategoricalRnd {
    /**
     * カテゴリの数. <br>
     * カテゴリ数が n のとき, 0, 1, ... , n - 1 の値をとる乱数を生成する.
     */
    final int catSize;

    /**
     * 唯一の外部に公開されないコンストラクタ. <br>
     * 与えるパラメータのバリデーションは行われていない.
     * 
     * @param catSize カテゴリサイズ
     */
    SkeletalCategoricalRnd(int catSize) {
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

    /**
     * {@link matsu.num.statistics.random.CategoricalRnd.Factory} の骨格実装.
     */
    static abstract class Factory implements CategoricalRnd.Factory {

        private static final Function<double[], IllegalArgumentException> exceptionGetter =
                values -> new IllegalArgumentException(String.format("パラメータ不正: size=%s", values.length));

        private final Exponentiation exponentiation;

        /**
         * 唯一の外部に公開されないコンストラクタ. <br>
         * 引数のnullチェックは行われていない.
         * 
         * @param exponentiation 指数関数の計算器
         */
        Factory(Exponentiation exponentiation) {
            super();

            assert Objects.nonNull(exponentiation) : "null";

            this.exponentiation = exponentiation;
        }

        @Override
        public final CategoricalRnd instanceOf(double[] probability) {
            if (!CategoricalRnd.acceptsSizeOf(probability)) {
                throw exceptionGetter.apply(probability);
            }

            double[] p = probability.clone();
            writeValidProbability(p);

            return this.createInstanceOf(p);
        }

        @Override
        public final CategoricalRnd instanceOfExp(double[] logProbability) {
            if (!CategoricalRnd.acceptsSizeOf(logProbability)) {
                throw exceptionGetter.apply(logProbability);
            }

            double[] p = logProbability.clone();
            toExp(p);
            canonicalize(p);
            writeValidProbability(p);

            return this.createInstanceOf(p);
        }

        private void writeValidProbability(double[] p) {
            canonicalize(p);
            ProbabilityNormalization.normalize(p);
        }

        /**
         * {@link #instanceOf(double[])}, {@link #instanceOfExp(double[])}
         * で返すインスタンスを生成するための抽象メソッド.
         * 
         * <p>
         * このメソッドは
         * {@link #instanceOf(double[])}, {@link #instanceOfExp(double[])}
         * の内部で呼ばれるために用意されており,
         * 外部から呼ぶことは許されず, 継承先でアクセス修飾子を緩めてはいけない. <br>
         * 内部から呼ばれる場合, 引数は必ず正当であり, 規格化
         * (各値は0以上であり総和が1)
         * されている. <br>
         * また, 引数の配列は再利用しても良い.
         * </p>
         *
         * @param probability 確率値の配列(各値は0以上であり総和が1)
         * @return 値配列に比例するカテゴリカル分布乱数発生器インスタンス
         */
        abstract CategoricalRnd createInstanceOf(double[] probability);

        @Override
        public String toString() {
            return "CategoricalRnd.Factory";
        }

        /**
         * probabilityを正当な値に書き換える.
         * 
         * @param probability
         */
        private void canonicalize(double[] probability) {
            final int size = probability.length;

            double max = max(probability);
            if (max == Double.POSITIVE_INFINITY) {
                //+inf値が含まれる場合, それ以外を全て0にする

                for (int i = 0; i < size; i++) {
                    probability[i] = probability[i] == Double.POSITIVE_INFINITY ? 1 : 0;
                }
            } else if (max > 1E+250) {
                //値が大きすぎる場合は調整する

                for (int i = 0; i < size; i++) {
                    probability[i] *= 1E-50;
                }
            } else if (max < 1E-200) {
                //値が小さすぎる場合は調整する
                //過剰に調整する

                for (int i = 0; i < size; i++) {
                    probability[i] *= 1E+200;
                    probability[i] *= 1E+200;
                }
            } else if (max < 1d) {
                for (int i = 0; i < size; i++) {
                    probability[i] *= 1E+250;
                }
            }

            //zero,負,NaNに対する処理
            //正則な値があれば前段で1E+50を超えるため, ここで代入されたとしても最終的に0になる
            for (int i = 0; i < size; i++) {
                if (Double.isNaN(probability[i])) {
                    probability[i] = Double.MIN_NORMAL;
                }
                probability[i] = Math.max(probability[i], Double.MIN_NORMAL);
            }
        }

        /**
         * 適宜スケールを調整してlogPをpにする. <br>
         * だだし, +infがある場合, +infが残る.
         * 
         * @param probability
         */
        private void toExp(double[] probability) {

            final int size = probability.length;

            double max = Math.min(max(probability), Double.MAX_VALUE);

            //正常値
            for (int i = 0; i < size; i++) {
                //NaNはそのまま戻る
                probability[i] = exponentiation.exp(probability[i] - max);
            }
        }

        /**
         * 配列の値の最大値を返す.
         * 
         * @param values 値たち
         * @return 最大値
         */
        private double max(double[] values) {
            if (values.length == 0) {
                throw new AssertionError("Bug; 空である");
            }
            double max = -Double.MAX_VALUE;
            for (double v : values) {
                //NaNは無視する
                if (Double.isNaN(v)) {
                    continue;
                }
                max = Math.max(max, v);
            }

            return max;
        }
    }
}
