/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random;

import org.junit.Ignore;

/**
 * 整数値をとる離散乱数発生器のテスティングフレームワークインターフェース.
 */
@Ignore
public interface IntegerRandomGeneratorTestingFramework {

    public static IntegerRandomGeneratorTestingFramework instanceOf(TestedIntegerRandomGenerator testedGenerator) {
        return new BetaBasedIntegerRndTesting(testedGenerator);
    }

    /**
     * 乱数のテストを行う. <br>
     * テストに合格しない場合はAssertionErrorをスローする.
     *
     * @throws AssertionError テストに合格しない場合
     */
    public abstract void test();

}
