/**
 * 2023.12.1
 */
package matsu.num.statistics.random.random.impl;

import java.util.Objects;
import java.util.function.Supplier;

import matsu.num.statistics.random.Random;

/**
 * {@linkplain Random}の基本実装. <br>
 * {@code Supplier<java.util.Random>}をラップする.
 * 
 * <p>
 * このクラスのインスタンスでは, 毎回の乱数生成のたびに{@linkplain Supplier#get()}を行う. <br>
 * すなわち, 独自の{@linkplain java.util.Random}インスタンス管理機構を持つ仕組みに適合する.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 17.1
 */
public final class RandomFromGetter implements Random {

    private final Supplier<? extends java.util.Random> getter;

    /**
     * 与えたサプライヤをラップしたインスタンスを構築する.
     * 
     * @param getter {@linkplain java.util.Random}を呼び出すためのサプライヤ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public RandomFromGetter(Supplier<? extends java.util.Random> getter) {
        this.getter = Objects.requireNonNull(getter);
    }

    @Override
    public boolean nextBoolean() {
        return this.getter.get().nextBoolean();
    }

    @Override
    public int nextInt() {
        return this.getter.get().nextInt();
    }

    @Override
    public int nextInt(int bound) {
        return this.getter.get().nextInt(bound);
    }

    @Override
    public double nextDouble() {
        return this.getter.get().nextDouble();
    }

    @Override
    public String toString() {
        return "Random(getter)";
    }

}
