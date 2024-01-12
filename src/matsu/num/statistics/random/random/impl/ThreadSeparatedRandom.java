/**
 * 2023.12.1
 */
package matsu.num.statistics.random.random.impl;

import java.util.concurrent.ThreadLocalRandom;

import matsu.num.statistics.random.Random;

/**
 * {@linkplain ThreadLocalRandom}の呼び出しをラップした{@linkplain Random}を提供する.
 * 
 * @author Matsuura Y.
 * @version 17.1
 */
public final class ThreadSeparatedRandom {

    /**
     * {@linkplain ThreadLocalRandom}の呼び出しをラップした{@linkplain Random}インスタンス. <br>
     * シングルトンである.
     */
    public static final Random INSTANCE = new RandomFromGetter(ThreadLocalRandom::current);

    private ThreadSeparatedRandom() {
        throw new AssertionError();
    }
}
