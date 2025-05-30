/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.8
 */
package matsu.num.statistics.random.service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import matsu.num.statistics.random.RndFactory;

/**
 * <p>
 * このモジュール内で実装されている乱数発生器のファクトリのプロバイダ.
 * </p>
 * 
 * <p>
 * このプロバイダインスタンスを生成するには,
 * {@link #byDefaultLib()} メソッドもしくは
 * {@link #by(CommonLib)} メソッドをコールする. <br>
 * {@link #byDefaultLib()} メソッドはデフォルトライブラリを使用してプロバイダインスタンスを生成する. <br>
 * {@link #by(CommonLib)} メソッドはユーザーが事前に準備した {@link CommonLib}
 * ライブラリを使用してプロバイダインスタンスを生成する. <br>
 * このプロバイダインスタンスの {@link #get(RandomGeneratorType)} メソッドを呼ぶことで,
 * 対応するファクトリを得ることができる.
 * </p>
 * 
 * @author Matsuura Y.
 */
public final class RandomGeneratorFactoryProvider {

    private static final RandomGeneratorFactoryProvider DEFAULT_INSTANCE =
            new RandomGeneratorFactoryProvider(CommonLib.defaultImplemented());

    private final CommonLib lib;
    private final Map<RandomGeneratorType<? extends RndFactory>, RndFactory> map;

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
     * @param type 乱数発生器のタイプ
     * @return 乱数発生器のファクトリ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public <R extends RndFactory> R get(RandomGeneratorType<R> type) {
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
     * このプロバイダの文字列表現を返す.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    /**
     * 与えられたライブラリを使用して処理を行う,
     * 乱数発生器プロバイダを返す.
     * 
     * @param lib ライブラリ
     * @return ライブラリを使用する乱数発生器プロバイダ
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static RandomGeneratorFactoryProvider by(CommonLib lib) {
        return new RandomGeneratorFactoryProvider(Objects.requireNonNull(lib));
    }

    /**
     * デフォルトライブラリを使用して処理を行う,
     * 乱数発生器プロバイダを返す.
     * 
     * @return デフォルトライブラリを使用する乱数発生器プロバイダ
     */
    public static RandomGeneratorFactoryProvider byDefaultLib() {
        return DEFAULT_INSTANCE;
    }

}
