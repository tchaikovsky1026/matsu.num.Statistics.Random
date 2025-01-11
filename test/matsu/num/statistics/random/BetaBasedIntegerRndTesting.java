/*
 * /*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random;

import java.util.Arrays;
import java.util.Objects;

import org.junit.Ignore;

/**
 * ベータ分布モデルによる検定に基づく, 離散分布乱数発生器のテスティングフレームワーク.
 * 
 * <p>
 * サンプルサイズ (9999) による経験累積分布を用いて乱数のテストを行う. <br>
 * 9999個の乱数を生成し, 1000, 2000, ... , 9000番目の値に対応する累積確率の解析値と,
 * 順位統計量が満たす確率分布を比較し, 逸脱があるかを確かめる.
 * </p>
 * 
 * <p>
 * 確率変数が取りうる階級値に対する累積確率を, <i>p</i><sub>1</sub>, ... とする. <br>
 * この累積確率を持つ確率分布から9999個を独立にサンプリングしたときの第
 * (1000<i>k</i>) 位統計量は確率変数であり,
 * <i>X</i><sub>1000<i>k</i></sub> と表す. <br>
 * さらに, <i>X</i><sub>1000<i>k</i></sub> は階級値であるので対応する累積確率に変換できる. <br>
 * その変換した累積確率を <i>P</i><sub>1000<i>k</i></sub> とする. <br>
 * <i>P</i><sub>1000<i>k</i></sub> = <i>p</i><sub><i>m</i></sub> である確率は,
 * 正則化不完全ベータ関数 <i>I</i>(<i>a</i>, <i>b</i>, <i>x</i>) を用いて, <br>
 * <i>I</i>(1000<i>k</i>, 10000 - 1000<i>k</i>, <i>p</i><sub><i>m</i></sub>)
 * - <i>I</i>(1000<i>k</i>, 10000 - 1000<i>k</i>, <i>p</i><sub><i>m -
 * 1</i></sub>) <br>
 * と表される. <br>
 * 有意水準を &alpha; とすると, 検定が棄却されないためには, <br>
 * p_1,...,p_mを取る確率 =
 * <i>I</i>(1000<i>k</i>, 10000 - 1000<i>k</i>, <i>p</i><sub><i>m</i></sub>)
 * &ge; &alpha; / 2 <br>
 * p_m,p_{m+1},...を取る確率 = 1 -
 * <i>I</i>(1000<i>k</i>, 10000 - 1000<i>k</i>, <i>p</i><sub><i>m - 1</i></sub>)
 * &ge; &alpha; / 2 <br>
 * であればよい.
 * </p>
 * 
 * <p>
 * 各値が99.999%信頼区間に含まれればテストクリアとする.
 * </p>
 */
@Ignore
final class BetaBasedIntegerRndTesting implements IntegerRandomGeneratorTestingFramework {

    //サンプルサイズ
    private static final int SAMPLE_SIZE = 9999;
    //検証するindex
    private static final int[] VERIFIED_INDEX = { 999, 1999, 2999, 3999, 4999, 5999, 6999, 7999, 8999 };
    //各indexの累積確率が入るべき範囲{min,max}:99.999%信頼区間
    private static final double[][] VALID_RANGE = {
            { 8.7244644447164200E-02, 1.1374197812435400E-01 },
            { 1.8270812715892800E-01, 2.1803193069061900E-01 },
            { 2.8001349208149000E-01, 3.2047989837277500E-01 },
            { 3.7849359325342900E-01, 4.2175310568456400E-01 },
            { 4.7792435046456100E-01, 5.2207564953542700E-01 },
            { 5.7824689431542400E-01, 6.2150640674656000E-01 },
            { 6.7952010162721400E-01, 7.1998650791850000E-01 },
            { 7.8196806930937100E-01, 8.1729187284106300E-01 },
            { 8.8625802187563800E-01, 9.1275535555282900E-01 }
    };

    private final TestedIntegerRandomGenerator testedGenerator;

    BetaBasedIntegerRndTesting(TestedIntegerRandomGenerator testedGenerator) {
        this.testedGenerator = Objects.requireNonNull(testedGenerator);
    }

    @Override
    public void test() {

        int[] samples = new int[SAMPLE_SIZE];
        for (int i = 0; i < SAMPLE_SIZE; i++) {
            samples[i] = testedGenerator.newValue();
        }
        Arrays.sort(samples);

        //検証indexにしたがって検証を行う
        for (int i = 0; i < VERIFIED_INDEX.length; i++) {
            int index = VERIFIED_INDEX[i];
            double min = VALID_RANGE[i][0];
            double max = VALID_RANGE[i][1];

            double analyticalProbability = testedGenerator.cumulativeProbability(samples[index]);
            double analyticalProbabilityOneBelow = testedGenerator.cumulativeProbabilityOneBelow(samples[index]);

            if (!(min <= analyticalProbability && analyticalProbabilityOneBelow <= max)) {
                throw new AssertionError(
                        String.format(
                                "乱数発生に異常あり:"
                                        + "index=%dの値は%d, その期待する累積確率は[%.5f,%.5f]であるが, "
                                        + "この区間は累積確率の許容値[%.5f,%.5f]に含まれていない.",
                                index, samples[index], analyticalProbabilityOneBelow, analyticalProbability,
                                min, max));
            }
        }

    }

}
