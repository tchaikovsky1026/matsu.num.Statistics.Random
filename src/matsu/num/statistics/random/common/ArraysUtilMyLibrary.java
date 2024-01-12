/**
 * 2024.1.9
 */
package matsu.num.statistics.random.common;

/**
 * {@code matsu.num.Commons} ライブラリを用いた {@linkplain ArraysUtil}.
 * 
 * @author Matsuura Y.
 * @version 17.5
 */
final class ArraysUtilMyLibrary implements ArraysUtil {

    ArraysUtilMyLibrary() {
        super();
    }

    @Override
    public double normMax(double[] vector) {
        return matsu.num.commons.ArraysUtil.normMax(vector);
    }
    
    @Override
    public String toString() {
        return "ArraysUtil(matsu.num.Commons)";
    }
}
