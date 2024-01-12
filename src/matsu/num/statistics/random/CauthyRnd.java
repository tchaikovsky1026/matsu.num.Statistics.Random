/**
 * 2024.1.8
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.cauthy.CauthyRndFactory;

/**
 * <p>
 * 標準Cauthy分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準Cauthy分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
 * P(<i>x</i>) &prop;
 * 1 / (1 + <i>x</i><sup><i>2</i></sup>)
 * </p>
 * 
 * <p>
 * 標準Cauthy分布は自由度1のStudent-t分布に一致する.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public interface CauthyRnd extends FloatingRandomGenerator {

    /**
     * <p>
     * この標準Cauthy分布と同等な, Student-t分布としてのビューを返す.
     * </p>
     * 
     * <p>
     * 戻り値のStudent-t分布の自由度は1である. <br>
     * ビューの変更であるため,
     * {@linkplain CauthyRnd} としての
     * {@linkplain #nextRandom(Random)}
     * と
     * {@linkplain TDistributionRnd} としての
     * {@linkplain TDistributionRnd#nextRandom(Random)}
     * とは同一の処理を行う.
     * </p>
     * 
     * @return 標準Cauthy分布と同等な, 自由度1のStudent-t分布
     */
    public abstract TDistributionRnd asTDistributionRnd();

    /**
     * <p>
     * 標準Cauthy分布乱数発生器インスタンスを返す.
     * </p>
     *
     * @return 標準Cauthy分布乱数発生器インスタンス
     */
    public static CauthyRnd instance() {
        return CauthyRndFactory.instance();
    }
}
