/**
 * 2024.1.9
 */
package matsu.num.statistics.random.common;

/**
 * {@linkplain Exponentiation} のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 17.5
 */
public final class ExponentiationFactory {

    private static final Exponentiation EXP_MY_LIBRARY = new ExponentiationMyLibrary();

    @SuppressWarnings("unused")
    private static final Exponentiation EXP_MATH = new ExponentiationMath();

    private ExponentiationFactory() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * 指数関数, 対数関数を計算するインスタンスを返す.
     * 
     * @return 指数関数, 対数関数の計算
     */
    public static Exponentiation instance() {
        return EXP_MY_LIBRARY;
    }

}
