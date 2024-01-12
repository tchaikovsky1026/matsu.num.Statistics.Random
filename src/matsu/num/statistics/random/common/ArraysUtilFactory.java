/**
 * 2024.1.9
 */
package matsu.num.statistics.random.common;

/**
 * {@linkplain ArraysUtil} のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 17.5
 */
public final class ArraysUtilFactory {

    private static final ArraysUtil ARRAYS_UTIL_MY_LIBRARY = new ArraysUtilMyLibrary();

    private ArraysUtilFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }
    
    /**
     * 配列に対する計算を行うインスタンスを返す.
     * 
     * @return 配列に対する計算
     */
    public static ArraysUtil instance() {
        return ARRAYS_UTIL_MY_LIBRARY;
    }

}
