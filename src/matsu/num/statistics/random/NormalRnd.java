/*
 * 2023.3.22
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.norm.zigg.ZiggNormRnd;

/**
 * 標準正規分布に従う乱数発生器を扱う. <br>
 * 標準正規分布の確率密度関数 P(<i>x</i>) は,  <br>
 * P(<i>x</i>) &prop; exp(-<i>x</i><sup>2</sup>/2) <br>
 * である.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public interface NormalRnd extends FloatingRandomGenerator {

    /**
     * 標準正規分布乱数発生器インスタンスを返す.
     *
     * @return 標準正規分布乱数発生器インスタンス
     */
    public static NormalRnd instance() {
        return ZiggNormRnd.instance();
    }
}
