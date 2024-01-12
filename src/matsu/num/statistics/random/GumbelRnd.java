/*
 * 2024.1.8
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.gumbel.GumbelRndFactory;

/**
 * <p>
 * 標準Gumbel分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準Gumbel分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
 * P(<i>x</i>) &prop; exp(-<i>x</i> - exp(-<i>x</i>))
 * </p>
 *
 * @author Matsuura Y.
 * @version 17.4
 */
public interface GumbelRnd extends FloatingRandomGenerator {

    /**
     * <p>
     * 標準ガンベル分布乱数発生器インスタンスを返す.
     * </p>
     *
     * @return 標準ガンベル分布乱数発生器インスタンス
     */
    public static GumbelRnd instance() {
        return GumbelRndFactory.instance();
    }
}
