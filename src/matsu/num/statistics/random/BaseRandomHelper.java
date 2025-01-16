/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * {@link BaseRandom} のヘルパ.
 * 
 * @author Matsuura Y.
 */
final class BaseRandomHelper {

    /**
     * {@link ThreadLocalRandom} の呼び出しをラップした {@link BaseRandom} インスタンス.
     * {@link ThreadLocalRandom} の外側では状態を持たないので使いまわし可能.
     */
    static final BaseRandom THREAD_SEPARATED_RANDOM = new RandomFromGetter(ThreadLocalRandom::current);

    private BaseRandomHelper() {
        //インスタンス化不可
        throw new AssertionError();
    }

    /**
     * {@link BaseRandom} の基本実装. <br>
     * {@link java.util.random.RandomGenerator} をラップする.
     */
    static final class RandomImpl implements BaseRandom {

        private final java.util.random.RandomGenerator random;

        /**
         * {@link java.util.Random}をラップする.
         * 
         * @param random ラップされるインスタンス
         * @throws NullPointerException 引数にnullが含まれる場合
         */
        RandomImpl(java.util.random.RandomGenerator random) {
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

    /**
     * {@link BaseRandom} の基本実装. <br>
     * {@code Supplier<java.util.random.RandomGenerator>} をラップする.
     * 
     * <p>
     * このクラスのインスタンスでは, 毎回の乱数生成のたびに{@link Supplier#get()}を行う. <br>
     * すなわち, 独自の{@link java.util.random.RandomGenerator}
     * インスタンス管理機構を持つ仕組みに適合する.
     * </p>
     */
    static final class RandomFromGetter implements BaseRandom {

        private final Supplier<? extends java.util.random.RandomGenerator> getter;

        /**
         * 与えたサプライヤをラップしたインスタンスを構築する.
         * 
         * @param getter {@link java.util.Random}を呼び出すためのサプライヤ
         * @throws NullPointerException 引数にnullが含まれる場合
         */
        RandomFromGetter(Supplier<? extends java.util.random.RandomGenerator> getter) {
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

}
