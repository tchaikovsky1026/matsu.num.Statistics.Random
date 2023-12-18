/**
 * 2023.3.22
 */
package matsu.num.statistics.random.gamma.mt;

import java.util.Random;

import matsu.num.commons.Exponentiation;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.NormalRnd;

/**
 * Marsaglia-Tsangの方法に基づく, ガンマ乱数のstaticな生成器. <br>
 * 扱える形状パラメータkは, {@code 1.0E-2 <= k <= 1.0E+28} である.
 * 
 * @author Matsuura Y.
 * @version 11.0
 */
public final class MarsagliaTsangStaticGaRnd {

    private static final double LOWER_LIMIT_OF_SHAPE_PARAMETER = 1E-2;
    private static final double UPPER_LIMIT_OF_SHAPE_PARAMETER = 1E28;

    private MarsagliaTsangStaticGaRnd() {
        throw new AssertionError();
    }

    /**
     * 形状パラメータを与えて, 標準ガンマ分布に従う乱数を発生させる.
     *
     * @param random 基本乱数発生器
     * @param k 形状パラメータ
     * @return 形状パラメータがkの標準ガンマ分布に従う乱数の値
     * @throws IllegalArgumentException パラメータが範囲外の場合
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static double nextRandom(Random random, double k) {
        if (!(LOWER_LIMIT_OF_SHAPE_PARAMETER <= k && k <= UPPER_LIMIT_OF_SHAPE_PARAMETER)) {
            throw new IllegalArgumentException(String.format(
                    "パラメータ不正:k=%.16G", k));
        }

        if (k == 1) {
            return ExponentialRnd.instance().nextRandom(random);
        }
        if (k > 1) {
            double d = k - (1.0 / 3.0);
            double c = (1.0 / 3.0) / Exponentiation.sqrt(d);
            return nextGammaOver1(random, d, c);
        }

        //k<1
        double d = k + (2.0 / 3.0);
        double c = (1.0 / 3.0) / Exponentiation.sqrt(d);
        return nextGammaOver1(random, d, c) * Exponentiation.exp(-ExponentialRnd.instance().nextRandom(random) / k);
    }

    private static double nextGammaOver1(Random random, double d, double c) {
        while (true) {
            double z = NormalRnd.instance().nextRandom(random);
            double v = 1 + c * z;
            double w = v * v * v;
            double y = d * w;
            if (y < 0.0) {
                continue;
            }
            double u = random.nextDouble();
            double z2 = z * z;
            if (u > z2 * z2 * 0.0331 || z2 * 0.5 + d * Exponentiation.log(w) + d - y > Exponentiation.log(1 - u)) {
                return y;
            }
        }
    }
}
