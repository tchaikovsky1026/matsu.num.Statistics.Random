/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
 */
package matsu.num.statistics.random.service.functionaltype;

import matsu.num.statistics.random.RandomGeneratorFactory;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * <p>
 * {@link RandomGeneratorType} の機能追加インターフェース. <br>
 * ファクトリを生成する仕組みを追加するmix-in. <br>
 * コンストラクタが公開されていないので, パッケージ外からはこのクラスを使用することはできない.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 20.0
 * @param <T> このタイプが返却する乱数発生器ファクトリの型
 */
public non-sealed interface FunctionalType<T extends RandomGeneratorFactory>
        extends RandomGeneratorType<T> {

    /**
     * キャストするためのメソッド. <br>
     * このパッケージ内部から呼ばれ, 必ず成功することが想定されている.
     * 
     * @param obj (キャスト可能な)オブジェクト
     * @return キャストしたオブジェクト
     */
    public abstract T cast(Object obj);

    /**
     * プロバイダからファクトリを生成する.
     * 
     * @param provider プロバイダ
     * @return ファクトリ
     */
    public abstract T get(RandomGeneratorFactoryProvider provider);

}
