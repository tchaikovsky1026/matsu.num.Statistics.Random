/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.8
 */
package matsu.num.statistics.random.service;

import java.util.Objects;
import java.util.function.Function;

import matsu.num.statistics.random.RndFactory;

/**
 * <p>
 * このモジュールが提供する乱数発生器のタイプ.
 * </p>
 * 
 * <p>
 * {@link RandomGeneratorType} のインスタンスは,
 * 各種乱数発生器のファクトリを取得するために
 * {@link RandomGeneratorFactoryProvider#get(RandomGeneratorType)}
 * に渡される. <br>
 * インスタンスはシングルトンとして用意されており, {@link GeneratorTypes} クラス内に
 * {@code static} 定数として用意されている.
 * </p>
 * 
 * <p>
 * <i>コンストラクタやファクトリメソッドは公開されておらず, 外部からインスタンス化することはできない.</i>
 * </p>
 * 
 * @author Matsuura Y.
 * @version 22.1
 * @param <T> このタイプが返却する乱数発生器ファクトリの型
 */
public final class RandomGeneratorType<T extends RndFactory> {

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
