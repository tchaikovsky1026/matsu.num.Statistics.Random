/*
 * 2024.1.8
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.norm.NormalRndFactory;

/**
 * <p>
 * 標準正規分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準正規分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
 * P(<i>x</i>) &prop; exp(-<i>x</i><sup>2</sup> / 2)
 * </p>
 *
 * @author Matsuura Y.
 * @version 17.4
 */
public interface NormalRnd extends FloatingRandomGenerator {

    /**
     * <p>
     * 標準正規分布乱数発生器インスタンスを返す.
     * </p>
     *
     * @return 標準正規分布乱数発生器インスタンス
     */
    public static NormalRnd instance() {
        return NormalRndFactory.instance();
    }
}
