/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.7
 */
package matsu.num.statistics.random.base;

import java.util.Objects;

import matsu.num.statistics.random.Rnd;
import matsu.num.statistics.random.accomp.ParameterlessRndFactory;

/**
 * <p>
 * {@link ParameterlessRndFactory} の骨格実装.
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
 * @deprecated このクラスは使われていない
 */
@Deprecated
public abstract class SimpleParameterlessRndFactory<T extends Rnd>
        implements ParameterlessRndFactory<T> {

    private final T rnd;
    private final String instanceName;

    /**
     * 乱数発生器を紐づけて, ファクトリを生成する.
     * 
     * <p>
     * このファクトリインスタンスの文字列表現を渡すと, {@link #toString()} でそれが返るようになる. <br>
     * {@code null} や空文字を与えた場合は適当に修正される.
     * </p>
     * 
     * @param rnd 乱数発生器
     * @param instanceName このファクトリインスタンスの文字列表現
     * @throws NullPointerException 乱数発生器がnullの場合
     */
    protected SimpleParameterlessRndFactory(T rnd, String instanceName) {
        super();

        this.rnd = Objects.requireNonNull(rnd);
        this.instanceName = Objects.isNull(instanceName) || instanceName.isBlank()
                ? "AnonymousFactory"
                : instanceName;
    }

    @Override
    public final T instance() {
        return this.rnd;
    }

    @Override
    public final String toString() {
        return this.instanceName;
    }
}
