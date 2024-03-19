/**
 * 2024.1.8
 */
package matsu.num.statistics.random.gamma;

import java.util.Objects;

import matsu.num.statistics.random.GammaRnd;

/**
 * {@link GammaRnd}向けのtoString表示に関するヘルパ.
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
final class GammaRndToString {

    private GammaRndToString() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * {@link GammaRnd}向けの文字列表現を提供する.
     * 
     * <p>
     * 概ね, 次のような表現であろう.
     * ただし, バージョン間の互換性は担保されていない. <br>
     * {@code GammaRnd(%shape)}
     * </p>
     * 
     * @param gammaRnd gammaRnd
     * @return GammaRnd向け文字列表現
     */
    public static String toString(GammaRnd gammaRnd) {
        if (Objects.isNull(gammaRnd)) {
            return "null";
        }
        return String.format(
                "GammaRnd(%s)", gammaRnd.shapeParameter());

    }
}
