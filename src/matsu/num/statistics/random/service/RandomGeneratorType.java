/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
 */
package matsu.num.statistics.random.service;

import java.util.Objects;
import java.util.function.Function;

/**
 * <p>
 * このモジュールが提供する乱数生成器のタイプ.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 21.0
 * @param <T> このタイプが返却する乱数発生器ファクトリの型
 */
public final class RandomGeneratorType<T> {

    private final String typeName;
    private final Class<T> factoryClass;
    private final Function<RandomGeneratorFactoryProvider, T> getter;

    RandomGeneratorType(String typeName, Class<T> factoryClass,
            Function<RandomGeneratorFactoryProvider, T> getter) {
        super();
        this.typeName = Objects.requireNonNull(typeName);
        this.factoryClass = Objects.requireNonNull(factoryClass);
        this.getter = Objects.requireNonNull(getter);
    }

    /**
     * キャストするためのメソッド. <br>
     * このパッケージ内部から呼ばれ, 必ず成功することが想定されている.
     * 
     * @param obj (キャスト可能な)オブジェクト
     * @return キャストしたオブジェクト
     */
    T cast(Object obj) {
        return this.factoryClass.cast(obj);
    }

    /**
     * プロバイダからファクトリを生成する.
     * 
     * @param provider プロバイダ
     * @return ファクトリ
     */
    T get(RandomGeneratorFactoryProvider provider) {
        return this.getter.apply(provider);
    }

    /**
     * このタイプの文字列表現を返す.
     * 
     * @return タイプの文字列表現
     */
    @Override
    public final String toString() {
        return this.typeName;
    }
}
