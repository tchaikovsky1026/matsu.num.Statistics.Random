/*
 * 2023.1.24
 */
package matsu.num.statistics.random;

import org.junit.Ignore;

/**
 * 連続分布乱数発生器のテスティングフレームワークインターフェース.
 *
 * @author Matsuura Y.
 * @version 9.0
 */
@Ignore
public interface FloatingRandomGeneratorTestingFramework {

    public static FloatingRandomGeneratorTestingFramework instanceOf(TestedFloatingRandomGenerator testedGenerator) {
        return new BetaBasedFloatingRndTesting(testedGenerator);
    }

    /**
     * 乱数のテストを行う. <br>
     * テストに合格しない場合はAssertionErrorをスローする.
     *
     * @throws AssertionError テストに合格しない場合
     */
    public abstract void test();

}
