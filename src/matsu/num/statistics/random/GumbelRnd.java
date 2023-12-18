/*
 * 2023.3.22
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.gumbel.zigg.ZiggGumbelRnd;

/**
 * 標準Gumbel分布に従う乱数発生器を扱う. <br>
 * 標準Gumbel分布の確率密度関数 P(<i>x</i>) は,  <br>
 * P(<i>x</i>) &prop; exp(-<i>x</i> - exp(-<i>x</i>)) <br>
 * である. 
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public interface GumbelRnd extends FloatingRandomGenerator {

    /**
     * 標準ガンベル分布乱数発生器インスタンスを返す.
     *
     * @return 標準ガンベル分布乱数発生器インスタンス
     */
    public static GumbelRnd instance() {
        return ZiggGumbelRnd.instance();
    }
}
