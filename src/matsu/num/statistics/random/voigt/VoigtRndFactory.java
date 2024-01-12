/**
 * 2024.1.9
 */
package matsu.num.statistics.random.voigt;

import matsu.num.statistics.random.VoigtRnd;

/**
 * {@linkplain VoigtRnd}の生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public final class VoigtRndFactory {

    private VoigtRndFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * <p>
     * 指定したパラメータ <i>&alpha;</i> を持つVoigt分布乱数発生器を返す. <br>
     * 0 &le; <i>&alpha;</i> &le; 1
     * でなければならない.
     * </p>
     * 
     * @param alpha &alpha;
     * @return パラメータ &alpha; のVoigt分布乱数発生器
     * @throws IllegalArgumentException &alpha; が不正である場合
     */
    public static VoigtRnd instanceOf(double alpha) {
        return new StandardImplVoigtRnd(alpha);
    }
}
