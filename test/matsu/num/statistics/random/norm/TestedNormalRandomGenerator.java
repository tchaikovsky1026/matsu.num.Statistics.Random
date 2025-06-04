/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.norm;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.TestedFloatingRandomGenerator;

/**
 * <p>
 * Normal乱数に関する, 変則型の {@link TestedFloatingRandomGenerator} を提供する. <br>
 * コンストラクタで所望の乱数発生器を与えることで動作する. <br>
 * 変則型については, 以下の説明を参照.
 * </p>
 * 
 * <p>
 * 正規分布は累積分布関数の計算が厄介であるので, カイ二乗分布に帰着させる. <br>
 * X,Yが標準正規分布に従うとき, {@literal  Z = X^2 + Y^2}は自由度2のカイ二乗分布に従う. <br>
 * 符号テストも行いたいので, <br>
 * {@literal Z = sig(XY) * (X^2 + Y^2)} <br>
 * とする. <br>
 * Zの累積分布関数は, <br>
 * (1/2)exp(-|z|/2) &emsp; (z < 0) <br>
 * 1 - (1/2)exp(-|z|/2) &emsp; (z > 0)
 * </p>
 * 
 * @author Matsuura Y.
 */
final class TestedNormalRandomGenerator implements TestedFloatingRandomGenerator {

    private final BaseRandom random = BaseRandom.threadSeparatedRandom();

    private final NormalRnd normalRnd;

    /**
     * テストする乱数発生器を与えてインスタンスを構築する.
     * 
     * @param normalRnd テストする乱数発生器
     * @throws NullPointerException 引数がnullの場合
     */
    public TestedNormalRandomGenerator(NormalRnd normalRnd) {
        super();
        this.normalRnd = normalRnd;
    }

    @Override
    public double newValue() {
        double x = normalRnd.nextRandom(random);
        double y = normalRnd.nextRandom(random);

        return Math.signum(x * y) * (x * x + y * y);
    }

    @Override
    public double cumulativeProbability(double arg) {
        if (arg < 0) {
            return 0.5 * Math.exp(-Math.abs(arg) * 0.5);
        }
        return 1 - 0.5 * Math.exp(-Math.abs(arg) * 0.5);
    }

}
