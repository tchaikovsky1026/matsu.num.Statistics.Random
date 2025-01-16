/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.random;

import java.util.Random;

/**
 * SIMD-oriented 高速メルセンヌツイスタ(SFMT19937)の実装.
 *
 * @author Matsuura Y.
 * @deprecated このクラスはどこからも使われていないので非推奨に.
 */
@Deprecated
public final class RandomSFMT extends Random {

    private static final long serialVersionUID = -4123778974745537889L;

    //内部ベクトル
    private int[] sfmtValues;
    private int sfmtIndex;

    /**
     * Random seed initialization.
     */
    public RandomSFMT() {
        super();
    }

    /**
     * Specified seed initialization. The seed value is casted to int.
     *
     * @param seed long &rarr; int
     */
    public RandomSFMT(long seed) {
        super(seed);
    }

    /**
     * New seed is set. The seed value is casted to int.
     *
     * @param seed long &rarr; int
     */
    @Override
    public synchronized void setSeed(long seed) {

        if (sfmtValues == null) {
            sfmtValues = new int[624];
        }
        final int[] p = sfmtValues;

        p[0] = (int) seed;
        for (int i = 1; i < 624; i++) {
            p[i] = (0x6C078965 * (p[i - 1] ^ (p[i - 1] >>> 30)) + i);
        }
        sfmtIndex = 624;
        certifyPeriod();
    }

    @Override
    protected int next(int bits) {
        if (sfmtIndex >= 624) {
            generate();
            sfmtIndex = 0;
        }
        final int y = sfmtValues[sfmtIndex];
        sfmtIndex++;
        return y >>> (32 - bits);
    }

    private void generate() {

        final int n = 624;
        final int m = 136;

        final int bp = 488;
        final int cp = 616;
        final int dp = 620;
        final int bm = -136;
        final int cm = -8;
        final int dm = -4;

        final int[] p = sfmtValues;

        p[0] ^= (p[0] << 8)
                ^ ((p[bp] >>> 11) & 0xdfffffef)
                ^ (p[cp] >>> 8) ^ (p[cp + 1] << 24)
                ^ (p[dp] << 18);
        p[1] ^= (p[1] << 8) ^ (p[0] >>> 24)
                ^ ((p[bp + 1] >>> 11) & 0xddfecb7f)
                ^ (p[cp + 1] >>> 8) ^ (p[cp + 2] << 24)
                ^ p[dp + 1] << 18;
        p[2] ^= (p[2] << 8) ^ (p[1] >>> 24)
                ^ ((p[bp + 2] >>> 11) & 0xbffaffff)
                ^ ((p[cp + 2] >>> 8) ^ (p[cp + 3] << 24))
                ^ (p[dp + 2] << 18);
        p[3] ^= (p[3] << 8) ^ (p[2] >>> 24)
                ^ ((p[bp + 3] >>> 11) & 0xbffffff6)
                ^ (p[cp + 3] >>> 8)
                ^ (p[dp + 3] << 18);

        p[4] ^= (p[4] << 8)
                ^ ((p[bp + 4] >>> 11) & 0xdfffffef)
                ^ (p[cp + 4] >>> 8) ^ (p[cp + 5] << 24)
                ^ (p[dm + 4] << 18);
        p[5] ^= (p[5] << 8) ^ (p[4] >>> 24)
                ^ ((p[bp + 5] >>> 11) & 0xddfecb7f)
                ^ (p[cp + 5] >>> 8) ^ (p[cp + 6] << 24)
                ^ p[dm + 5] << 18;
        p[6] ^= (p[6] << 8) ^ (p[5] >>> 24)
                ^ ((p[bp + 6] >>> 11) & 0xbffaffff)
                ^ ((p[cp + 6] >>> 8) ^ (p[cp + 7] << 24))
                ^ (p[dm + 6] << 18);
        p[7] ^= (p[7] << 8) ^ (p[6] >>> 24)
                ^ ((p[bp + 7] >>> 11) & 0xbffffff6)
                ^ (p[cp + 7] >>> 8)
                ^ (p[dm + 7] << 18);

        for (int a = 8; a < m; a += 4) {
            p[a] ^= (p[a] << 8)
                    ^ ((p[a + bp] >>> 11) & 0xdfffffef)
                    ^ (p[a + cm] >>> 8) ^ (p[a + cm + 1] << 24)
                    ^ (p[a + dm] << 18);
            p[a + 1] ^= (p[a + 1] << 8) ^ (p[a] >>> 24)
                    ^ ((p[a + bp + 1] >>> 11) & 0xddfecb7f)
                    ^ (p[a + cm + 1] >>> 8) ^ (p[a + cm + 2] << 24)
                    ^ p[a + dm + 1] << 18;
            p[a + 2] ^= (p[a + 2] << 8) ^ (p[a + 1] >>> 24)
                    ^ ((p[a + bp + 2] >>> 11) & 0xbffaffff)
                    ^ ((p[a + cm + 2] >>> 8) ^ (p[a + cm + 3] << 24))
                    ^ (p[a + dm + 2] << 18);
            p[a + 3] ^= (p[a + 3] << 8) ^ (p[a + 2] >>> 24)
                    ^ ((p[a + bp + 3] >>> 11) & 0xbffffff6)
                    ^ (p[a + cm + 3] >>> 8)
                    ^ (p[a + dm + 3] << 18);
        }

        for (int a = m; a < n; a += 4) {
            p[a] ^= (p[a] << 8)
                    ^ ((p[a + bm] >>> 11) & 0xdfffffef)
                    ^ (p[a + cm] >>> 8) ^ (p[a + cm + 1] << 24)
                    ^ (p[a + dm] << 18);
            p[a + 1] ^= (p[a + 1] << 8) ^ (p[a] >>> 24)
                    ^ ((p[a + bm + 1] >>> 11) & 0xddfecb7f)
                    ^ (p[a + cm + 1] >>> 8) ^ (p[a + cm + 2] << 24)
                    ^ p[a + dm + 1] << 18;
            p[a + 2] ^= (p[a + 2] << 8) ^ (p[a + 1] >>> 24)
                    ^ ((p[a + bm + 2] >>> 11) & 0xbffaffff)
                    ^ ((p[a + cm + 2] >>> 8) ^ (p[a + cm + 3] << 24))
                    ^ (p[a + dm + 2] << 18);
            p[a + 3] ^= (p[a + 3] << 8) ^ (p[a + 2] >>> 24)
                    ^ ((p[a + bm + 3] >>> 11) & 0xbffffff6)
                    ^ (p[a + cm + 3] >>> 8)
                    ^ (p[a + dm + 3] << 18);
        }
    }

    private void certifyPeriod() {

        final int[] p = sfmtValues;
        final int[] parity = { 0x00000001, 0x00000000, 0x00000000, 0x13c9e684 };

        int inner = 0;
        for (int i = 0; i < 4; i++) {
            inner ^= p[i] & parity[i];
        }
        for (int i = 0x10; i > 0; i >>>= 1) {
            inner ^= inner >>> i;
        }
        inner &= 1;
        if ((inner & 1) == 1) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int work = 1;
            for (int j = 0; j < 32; j++) {
                if ((work & parity[i]) != 0) {
                    p[i] ^= work;
                    return;
                }
                work <<= 1;
            }
        }
    }

}
