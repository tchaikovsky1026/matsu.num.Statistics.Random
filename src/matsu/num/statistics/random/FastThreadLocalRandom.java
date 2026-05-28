/*
 * Copyright © 2026 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.5.28
 */
package matsu.num.statistics.random;

import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;

/**
 * 改善された ThreadLocalRandom.
 * 
 * @author Matsuura Y.
 */
final class FastThreadLocalRandom implements RandomGenerator {

    static final RandomGenerator INSTANCE = new FastThreadLocalRandom();

    private FastThreadLocalRandom() {
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
