/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.cat;

/**
 * 0以上の有限値を持つ配列を総和が1dになるように規格化するためのユーティリティクラス.
 * 
 * @author Matsuura Y.
 */
final class ProbabilityNormalization {

    private ProbabilityNormalization() {
        throw new AssertionError("インスタンス化不可");
    }

    /**
     * 0以上の有限値を持つ配列を, 総和が1dになるように規格化する
     * (配列の値を上書きする). <br>
     * 各値は0以上の有限値, 総和は正の有限値でなければならない.
     * 
     * <p>
     * 引数の値を書き換える処理であるので, マルチスレッドで配列が共有されるような使用はNGである.
     * </p>
     * 
     * <p>
     * 引数のバリデーションは行われていない. <br>
     * したがって, メソッドを外部パッケージや外部モジュールに公開してはいけない.
     * </p>
     * 
     * @param probability 規格化するための配列
     */
    static void normalize(double[] probability) {
        int cat = probability.length;

        //確率の規格化
        double sum = 0;
        for (int l = cat - 1; l >= 0; l--) {
            double p_l = probability[l];
            sum += p_l;

            assert p_l >= 0 : "有効な配列でない";
        }

        assert Double.isFinite(sum) && sum > 0 : "有効な配列でない";

        for (int i = 0; i < cat; i++) {
            probability[i] /= sum;
        }
    }
}
