/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.7
 */
package matsu.num.statistics.random.base;

import java.util.Objects;
import java.util.function.Supplier;

import matsu.num.statistics.random.ParameterlessRndFactory;
import matsu.num.statistics.random.Rnd;

/**
 * <p>
 * {@link ParameterlessRndFactory} の骨格実装だが,
 * 乱数発生器の生成を遅延初期化する.
 * </p>
 * 
 * <p>
 * {@code ParameterlessRndFactory}
 * を継承したインターフェースを {@code implements} しこのクラスを継承した具象ファクトリクラスを作成するのが,
 * 通常の使い方である.
 * </p>
 * 
 * @author Matsuura Y.
 * @param <T> このファクトリが扱う乱数発生器の型
 */
public abstract class LazyParameterlessRndFactory<T extends Rnd>
        implements ParameterlessRndFactory<T> {

    private final String instanceName;
    private final Supplier<T> rndCreator;

    // 遅延初期化用ロックオブジェクト
    private final Object lock = new Object();
    private volatile T rnd;

    /**
     * <p>
     * 乱数発生器のクリエイタを渡して, ファクトリを生成する.
     * </p>
     * 
     * <p>
     * クリエイタは, {@link Supplier#get()} が呼ばれたときに,
     * 新しいインスタンスを生成するように実装する. <br>
     * (当然, {@code null} を返してはならない.)
     * </p>
     * 
     * <p>
     * このファクトリインスタンスの文字列表現を渡すと, {@link #toString()} でそれが返るようになる. <br>
     * {@code null} や空文字を与えた場合は適当に修正される.
     * </p>
     * 
     * @param rndCreator 乱数発生器のクリエイタ
     * @param instanceName このファクトリインスタンスの文字列表現
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    protected LazyParameterlessRndFactory(Supplier<T> rndCreator, String instanceName) {
        super();

        this.rndCreator = Objects.requireNonNull(rndCreator);
        this.instanceName = Objects.isNull(instanceName) || instanceName.isBlank()
                ? "AnonymousFactory"
                : instanceName;
    }

    @Override
    public final T instance() {
        T out = this.rnd;
        if (Objects.nonNull(out)) {
            return out;
        }
        synchronized (this.lock) {
            out = this.rnd;
            if (Objects.nonNull(out)) {
                return out;
            }

            return this.rnd = this.rndCreator.get();
        }
    }

    @Override
    public final String toString() {
        return this.instanceName;
    }
}
