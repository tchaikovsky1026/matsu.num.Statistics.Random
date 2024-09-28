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

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * このモジュール内で実装されている乱数発生器のファクトリのプロバイダ.
 * </p>
 * 
 * <p>
 * まずこのプロバイダを生成するには, 共通ライブラリ
 * ({@link CommonLib})
 * の準備が必要である. <br>
 * デフォルトのライブラリを使う場合は
 * {@link #byDefaultLib()} をコールすればよく,
 * ユーザーが準備し多ライブラリを使う場合は
 * {@link #by(CommonLib)} をコールする.
 * </p>
 * 
 * <p>
 * このプロバイダの {@link #get(RandomGeneratorType)} メソッドを呼ぶことで,
 * 対応するエグゼキュータを得ることができる. <br>
 * {@link RandomGeneratorType} は
 * {@link GeneratorTypes} クラス内に定数として提供されている.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 21.0
 */
public final class RandomGeneratorFactoryProvider {

    private static final RandomGeneratorFactoryProvider DEFAULT_INSTANCE =
            new RandomGeneratorFactoryProvider(CommonLib.defaultImplemented());

    private final CommonLib lib;
    private final Map<RandomGeneratorType<?>, Object> map;

    //ロック用オブジェクト
    private final Object lock = new Object();

    private RandomGeneratorFactoryProvider(CommonLib lib) {
        super();
        this.lib = lib;
        this.map = new ConcurrentHashMap<>();
    }

    /**
     * 型を与えて乱数発生器のファクトリを取得する.
     * 
     * @param <R> ファクトリの型パラメータ
     * @param type 乱数生成器のタイプ
     * @return 乱数発生器のファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public <R> R get(RandomGeneratorType<R> type) {
        Objects.requireNonNull(type);

        Object out = this.map.get(type);
        if (Objects.nonNull(out)) {
            return type.cast(out);
        }
        synchronized (this.lock) {
            out = this.map.get(type);
            if (Objects.nonNull(out)) {
                return type.cast(out);
            }
            R castedObj = type.get(this);
            this.map.put(type, castedObj);
            return castedObj;
        }
    }

    /**
     * このプロバイダに紐づけられているライブラリを返す.
     * 
     * @return このプロバイダが紐づくライブラリ
     */
    public CommonLib lib() {
        return this.lib;
    }

    /**
     * このプロバイダの文字列表現.
     * 
     * @return 文字列表現
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    /**
     * 与えられたライブラリを使用して処理を行う,
     * 乱数生成器プロバイダを返す.
     * 
     * @param lib ライブラリ
     * @return ライブラリを使用する乱数生成器プロバイダ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static RandomGeneratorFactoryProvider by(CommonLib lib) {
        return new RandomGeneratorFactoryProvider(Objects.requireNonNull(lib));
    }

    /**
     * デフォルトライブラリを使用して処理を行う,
     * 乱数生成器プロバイダを返す.
     * 
     * @return デフォルトライブラリを使用する乱数生成器プロバイダ
     */
    public static RandomGeneratorFactoryProvider byDefaultLib() {
        return DEFAULT_INSTANCE;
    }

}
