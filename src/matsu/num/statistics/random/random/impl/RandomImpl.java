/**
 * 2023.12.1
 */
package matsu.num.statistics.random.random.impl;

import java.util.Objects;

import matsu.num.statistics.random.Random;

/**
 * {@linkplain Random}の基本実装. <br>
 * {@linkplain java.util.Random}をラップする.
 * 
 * @author Matsuura Y.
 * @version 17.1
 */
public final class RandomImpl implements Random {

    private final java.util.Random random;

    /**
     * {@linkplain java.util.Random}をラップする.
     * 
     * @param random ラップされるインスタンス
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public RandomImpl(java.util.Random random) {
        this.random = Objects.requireNonNull(random);
    }

    @Override
    public boolean nextBoolean() {
        return this.random.nextBoolean();
    }

    @Override
    public int nextInt() {
        return this.random.nextInt();
    }

    @Override
    public int nextInt(int bound) {
        return this.random.nextInt(bound);
    }

    @Override
    public double nextDouble() {
        return this.random.nextDouble();
    }
    
    @Override
    public String toString() {
        return "Random(src)";
    }

}
