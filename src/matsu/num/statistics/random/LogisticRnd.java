/*
 * 2023.3.22
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.logi.zigg.ZiggLogiRnd;

/**
 * 標準ロジスティック分布に従う乱数発生器を扱う. <br>
 * 標準ロジスティック分布の確率密度関数 P(<i>x</i>) は,  <br>
 * P(<i>x</i>) &prop; exp(-<i>x</i>)/(1 + exp(-<i>x</i>))<sup>2</sup> <br>
 * である. <br>
 * この乱数発生器はZiggurat法により実装されている.
 *
 * @author Matsuura Y.
 * @version 11.0
 */
public interface LogisticRnd extends FloatingRandomGenerator {

    /**
     * 標準ロジスティック分布乱数発生器インスタンスを返す.
     *
     * @return 標準ロジスティック分布乱数発生器インスタンス
     */
    public static LogisticRnd instance() {
        return ZiggLogiRnd.instance();
    }
}
