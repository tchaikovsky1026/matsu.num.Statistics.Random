/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.27
 */
package matsu.num.statistics.random.base;

import java.util.Objects;

/**
 * パラメータを持たない乱数発生器のファクトリを扱う.
 * 
 * <p>
 * 乱数発生器は {@link #instance()} メソッドにより取得する.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 21.0
 * @param <T> このファクトリが扱う乱数発生器の型
 */
public abstract class ParameterlessFactory<T> {

    private final T rnd;
    private final String instanceName;

    /**
     * 乱数生成器を紐づけて, ファクトリを生成する.
     * 
     * <p>
     * このファクトリインスタンスの文字列表現を渡すと, {@link #toString()} でそれが返るようになる. <br>
     * {@code null} や空文字を与えた場合は適当に修正される.
     * </p>
     * 
     * @param rnd 乱数生成器
     * @param instanceName このファクトリインスタンスの文字列表現
     * @throws NullPointerException 乱数生成器がnullの場合
     */
    protected ParameterlessFactory(T rnd, String instanceName) {
        super();

        this.rnd = Objects.requireNonNull(rnd);
        this.instanceName = Objects.isNull(instanceName) || instanceName.isBlank()
                ? "AnonymousFactory"
                : instanceName;
    }

    /**
     * {@code T} 型の乱数発生器インスタンスを返す.
     *
     * @return 乱数発生器インスタンス
     */
    public final T instance() {
        return this.rnd;
    }

    @Override
    public final String toString() {
        return this.instanceName;
    }
}
