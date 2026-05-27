/*
 * Copyright © 2026 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.5.27
 */
package matsu.num.statistics.random;

import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;

/**
 * {@link BaseRandom#threadSeparatedRandom()} の実装クラス.
 * 
 * @author Matsuura Y.
 */
final class ThreadSeparatedRandom implements BaseRandom {

    /** このクラスの唯一のシングルトンインスタンス. */
    static final BaseRandom INSTANCE = new ThreadSeparatedRandom();

    private final RandomGenerator randomGenerator = new FastThreadLocalRandom();

    /** 非公開コンストラクタ. */
    private ThreadSeparatedRandom() {
        super();
    }

    @Override
    public boolean nextBoolean() {
        return randomGenerator.nextBoolean();
    }

    @Override
    public int nextInt() {
        return randomGenerator.nextInt();
    }

    @Override
    public int nextInt(int bound) {
        return randomGenerator.nextInt(bound);
    }

    @Override
    public long nextLong() {
        return randomGenerator.nextLong();
    }

    @Override
    public double nextDouble() {
        return randomGenerator.nextDouble();
    }

    @Override
    public double nextExponential() {
        return randomGenerator.nextExponential();
    }

    @Override
    public double nextGaussian() {
        return randomGenerator.nextGaussian();
    }

    @Override
    public String toString() {
        return "BaseRandom(thread-separated)";
    }

    /**
     * ThreadLocalRandom を適切にラップしたラッパー. <br>
     * このインスタンスは複数スレッドで共有されてよい.
     */
    private static final class FastThreadLocalRandom implements RandomGenerator {

        FastThreadLocalRandom() {
            super();
        }

        @Override
        public boolean nextBoolean() {
            return ThreadLocalRandom.current().nextBoolean();
        }

        @Override
        public void nextBytes(byte[] bytes) {
            ThreadLocalRandom.current().nextBytes(bytes);
        }

        @Override
        public double nextDouble() {
            return ThreadLocalRandom.current().nextDouble();
        }

        @Override
        public double nextDouble(double bound) {
            return ThreadLocalRandom.current().nextDouble(bound);
        }

        @Override
        public double nextDouble(double origin, double bound) {
            return ThreadLocalRandom.current().nextDouble(origin, bound);
        }

        @Override
        public float nextFloat() {
            return ThreadLocalRandom.current().nextFloat();
        }

        @Override
        public float nextFloat(float bound) {
            return ThreadLocalRandom.current().nextFloat(bound);
        }

        @Override
        public float nextFloat(float origin, float bound) {
            return ThreadLocalRandom.current().nextFloat(origin, bound);
        }

        @Override
        public int nextInt() {
            return ThreadLocalRandom.current().nextInt();
        }

        @Override
        public int nextInt(int bound) {
            return ThreadLocalRandom.current().nextInt(bound);
        }

        @Override
        public int nextInt(int origin, int bound) {
            return ThreadLocalRandom.current().nextInt(origin, bound);
        }

        @Override
        public long nextLong() {
            return ThreadLocalRandom.current().nextLong();
        }

        @Override
        public long nextLong(long bound) {
            return ThreadLocalRandom.current().nextLong(bound);
        }

        @Override
        public long nextLong(long origin, long bound) {
            return ThreadLocalRandom.current().nextLong(origin, bound);
        }
    }
}
