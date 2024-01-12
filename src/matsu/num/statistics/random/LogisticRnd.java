/*
 * 2024.1.8
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.logi.LogisticRndFactory;

/**
 * <p>
 * 標準ロジスティック分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準ロジスティック分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
 * P(<i>x</i>) &prop;
 * exp(-<i>x</i>)
 * /
 * (1 + exp(-<i>x</i>))<sup>2</sup>
 * </p>
 *
 * @author Matsuura Y.
 * @version 17.4
 */
public interface LogisticRnd extends FloatingRandomGenerator {

    /**
     * <p>
     * 標準ロジスティック分布乱数発生器インスタンスを返す.
     * </p>
     * 
     * @return 標準ロジスティック分布乱数発生器インスタンス
     */
    public static LogisticRnd instance() {
        return LogisticRndFactory.instance();
    }
}
