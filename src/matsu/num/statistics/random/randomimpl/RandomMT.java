/**
 * 2023.3.22
 */
package matsu.num.statistics.random.randomimpl;

import java.util.Random;

/**
 * メルセンヌツイスタ(MT19937)の実装.
 *
 * @author Matsuura Y.
 * @version 11.0
 * @deprecated このクラスはどこからも使われていないので非推奨に.
 */
@Deprecated
public final class RandomMT extends Random {

    private final static int UPPER_MASK = 0x80000000;
    private final static int LOWER_MASK = 0x7fffffff;

    private final static int W = 32;
    private final static int N = 624;
    private final static int M = 397;
    private final static int[] A = { 0x0, 0x9908B0DF };
    private final static int B = 0x9D2C5680;
    private final static int C = 0xEFC60000;
    private final static int S = 7;
    private final static int T = 15;
    private final static int U = 11;
    private final static int L = 18;
    private final static int F = 0x6C078965;

    //内部ベクトル
    private int[] mt;
    private int mti;

    /**
     * Random seed initialization.
     */
    public RandomMT() {
        super();
    }

    /**
     * Specified seed initialization. The seed value is casted to int.
     *
     * @param seed long &rarr; int
     */
    public RandomMT(long seed) {
        super(seed);
    }

    /**
     * New seed is set.
     * The seed value is casted to int. 
     *
     * @param seed long &rarr; int
     */
    @Override
    public synchronized void setSeed(long seed) {
        if (mt == null) {
            mt = new int[N];
        }
        mt[0] = (int) seed;
        for (int i = 1; i < N; i++) {
            mt[i] = (F * (mt[i - 1] ^ (mt[i - 1] >>> (W - 2))) + i);
        }
        mti = N;
    }

    @Override
    protected int next(int bits) {
        if (mti >= N) {
            twist();
        }
        int y = mt[mti];
        y ^= (y >>> U);
        y ^= (y << S) & B;
        y ^= (y << T) & C;
        y ^= (y >>> L);
        mti++;
        return (y >>> (W - bits));
    }

    private void twist() {
        int x;
        for (int i = 0; i < N - M; i++) {
            x = (mt[i] & UPPER_MASK) | (mt[i + 1] & LOWER_MASK);
            mt[i] = mt[i + M] ^ ((x >>> 1) ^ (A[x & 0x1]));
        }
        for (int i = N - M; i < N - 1; i++) {
            x = (mt[i] & UPPER_MASK) | (mt[i + 1] & LOWER_MASK);
            mt[i] = mt[i + M - N] ^ ((x >>> 1) ^ (A[x & 0x1]));
        }
        x = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
        mt[N - 1] = mt[M - 1] ^ ((x >>> 1) ^ (A[x & 0x1]));

        mti = 0;
    }

}
